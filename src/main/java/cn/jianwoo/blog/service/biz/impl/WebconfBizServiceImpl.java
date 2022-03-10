package cn.jianwoo.blog.service.biz.impl;

import cn.jianwoo.blog.builder.JwBuilder;
import cn.jianwoo.blog.cache.CacheStore;
import cn.jianwoo.blog.constants.CacaheKeyConstants;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.dao.base.WebconfTransDao;
import cn.jianwoo.blog.dao.biz.WebconfBizDao;
import cn.jianwoo.blog.entity.Webconf;
import cn.jianwoo.blog.entity.extension.WebconfExt;
import cn.jianwoo.blog.enums.BizEventOptTypeEnum;
import cn.jianwoo.blog.enums.BizEventTypeEnum;
import cn.jianwoo.blog.enums.ValueTypeEnum;
import cn.jianwoo.blog.event.BizEventLogEvent;
import cn.jianwoo.blog.exception.DaoException;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.exception.WebconfBizException;
import cn.jianwoo.blog.service.biz.WebconfBizService;
import cn.jianwoo.blog.service.bo.WebconfBO;
import cn.jianwoo.blog.service.bo.WebconfGroupBO;
import cn.jianwoo.blog.service.bo.WebconfResBO;
import cn.jianwoo.blog.util.DateUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class WebconfBizServiceImpl implements WebconfBizService {

    @Autowired
    private WebconfTransDao webconfTransDao;
    @Autowired
    private WebconfBizDao webconfBizDao;
    @Autowired
    private CacheStore<String, Object> cacheStore;
    @Autowired
    private ApplicationContext applicationContext;


    @Override
    public WebconfResBO queryConfig() {
        List<WebconfExt> webconfList = webconfBizDao.queryEffectiveWebconf();
        List<WebconfBO> list = new ArrayList<>();
        List<WebconfGroupBO> resList = new ArrayList<>();
        List<WebconfGroupBO> tabs = new ArrayList<>();
        WebconfResBO resBO = new WebconfResBO();
        webconfList.sort(Comparator.comparingInt(WebconfExt::getIndex));
        if (CollectionUtils.isNotEmpty(webconfList)) {
            webconfList.forEach(o -> {
                WebconfBO bo = new WebconfBO();
                BeanUtils.copyProperties(o, bo);
                if (ValueTypeEnum.STRING.getValue().equals(o.getValueType())) {
                    bo.setValue(o.getStringValue());
                } else if (ValueTypeEnum.FLOAT.getValue().equals(o.getValueType())) {
                    bo.setValue(formatFloat2Str(o.getFloatValue()));
                } else if (ValueTypeEnum.INTEGER.getValue().equals(o.getValueType())) {
                    bo.setValue(formatInt2Str(o.getIntValue()));
                } else if (ValueTypeEnum.BOOLEAN.getValue().equals(o.getValueType())) {
                    bo.setValue(formatBoolean2Str(o.getBooleanValue()));
                } else if (ValueTypeEnum.DATE.getValue().equals(o.getValueType())) {
                    bo.setValue(DateUtil.formatTimestamp(o.getDateValue()));
                }
                if (null == bo.getTabType()) {
                    bo.setTabType(Constants.BLANK);
                }
                list.add(bo);
            });
            Map<String, List<WebconfBO>> grpMap = list.stream().collect(Collectors.groupingBy(WebconfBO::getTabType));
            grpMap.forEach((k, v) -> {
                WebconfGroupBO grpBO = JwBuilder.of(WebconfGroupBO::new)
                        .with(WebconfGroupBO::setTabCode, k)
                        .with(WebconfGroupBO::setTabNameDsp, v.get(0).getTabTypeDsp())
                        .with(WebconfGroupBO::setList, v)
                        .with(WebconfGroupBO::setIndex, v.get(0).getIndex())
                        .build();
                resList.add(grpBO);
                tabs.add(JwBuilder.of(WebconfGroupBO::new).with(WebconfGroupBO::setIndex, grpBO.getIndex())
                        .with(WebconfGroupBO::setTabNameDsp, grpBO.getTabNameDsp()).build());
            });
        }
        resList.sort(Comparator.comparingInt(WebconfGroupBO::getIndex));
        tabs.sort(Comparator.comparingInt(WebconfGroupBO::getIndex));
        resBO.setData(resList);
        resBO.setTabList(tabs.stream().map(WebconfGroupBO::getTabNameDsp).collect(Collectors.toList()));

        return resBO;
    }

    private String format(Object v) {
        if (v == null) {
            return "";
        }
        return String.valueOf(v);

    }

    private String formatFloat2Str(BigDecimal v) {
        if (v == null) {
            return Constants.ZERO;
        }
        return v.setScale(4, BigDecimal.ROUND_HALF_UP).toString();

    }

    private String formatInt2Str(Integer v) {
        if (v == null) {
            return Constants.ZERO;
        }
        return v.toString();

    }


    private String formatBoolean2Str(Boolean v) {
        if (v == null) {
            return Constants.FALSE;
        }
        return v.toString().toUpperCase(Locale.ROOT);

    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doUpdateConfig(List<WebconfBO> configList) throws JwBlogException {
        log.info(">>Update webconf param: [{}]", JSON.toJSONString(configList));

        if (CollectionUtils.isNotEmpty(configList)) {
            for (WebconfBO o : configList) {
                try {
                    Webconf webconf = new Webconf();
                    BeanUtils.copyProperties(o, webconf);
                    if (ValueTypeEnum.STRING.getValue().equals(o.getValueType())) {
                        webconf.setStringValue(o.getValue());
                    } else if (ValueTypeEnum.FLOAT.getValue().equals(o.getValueType())) {
                        webconf.setFloatValue(formatStr2Float(o.getValue()));
                    } else if (ValueTypeEnum.INTEGER.getValue().equals(o.getValueType())) {
                        webconf.setIntValue(formatStr2Int(o.getValue()));
                    } else if (ValueTypeEnum.BOOLEAN.getValue().equals(o.getValueType())) {
                        webconf.setBooleanValue(Boolean.valueOf(o.getValue()));
                    } else if (ValueTypeEnum.DATE.getValue().equals(o.getValueType())) {
                        webconf.setDateValue(DateUtil.parseTimestamp(o.getValue()));
                    }
                    Webconf newWebconf = new Webconf();
                    newWebconf.setStringValue(webconf.getStringValue());
                    newWebconf.setFloatValue(webconf.getFloatValue());
                    newWebconf.setIntValue(webconf.getIntValue());
                    newWebconf.setBooleanValue(webconf.getBooleanValue());
                    newWebconf.setDateValue(webconf.getDateValue());
                    webconfBizDao.doUpdateWebconfByKey(newWebconf, webconf.getKey());

                    if (null == o.getValue()) {
                        o.setValue(Constants.BLANK);
                    }
                    String cacheKey = MessageFormat.format(CacaheKeyConstants.WEBCONF_KEY, webconf.getKey());
                    cacheStore.put(cacheKey, o.getValue());
                    registerBizEvent(webconf.getKey(), BizEventOptTypeEnum.CREATE);
                } catch (DaoException e) {
                    log.error("WebconfBizServiceImpl.doUpdateConfig exec failed, e:\n", e);
                    throw WebconfBizException.MODIFY_FAILED_EXCEPTION.format(o.getKey()).print();

                }
            }
        }

    }

    @Override
    public String queryWebconfByKey(String key) throws JwBlogException {
        if (StringUtils.isBlank(key)) return null;
        String cacheKey = MessageFormat.format(CacaheKeyConstants.WEBCONF_KEY, key);
        if (cacheStore.hasKey(cacheKey)) {
            //理论上永远不会为null，因为key存在，且value不能为null
            return (String) cacheStore.get(cacheKey).orElse(null);
        }
        Webconf webConf = webconfTransDao.queryWebconfByKey(key);
        String value = null;

        if (ValueTypeEnum.STRING.getValue().equals(webConf.getValueType())) {
            value = format(webConf.getStringValue());
        } else if (ValueTypeEnum.FLOAT.getValue().equals(webConf.getValueType())) {
            value = formatFloat2Str(webConf.getFloatValue());
        } else if (ValueTypeEnum.INTEGER.getValue().equals(webConf.getValueType())) {
            value = formatInt2Str(webConf.getIntValue());
        } else if (ValueTypeEnum.BOOLEAN.getValue().equals(webConf.getValueType())) {
            value = formatBoolean2Str(webConf.getBooleanValue());
        } else if (ValueTypeEnum.DATE.getValue().equals(webConf.getValueType())) {
            value = DateUtil.formatTimestamp(webConf.getDateValue());
        }
        cacheStore.put(cacheKey, value);
        return value;
    }

    @Override
    public Map<String, String> queryWebconfByType(String cfgType) throws JwBlogException {
        Map<String, String> confMap = new HashMap<>();
        if (StringUtils.isBlank(cfgType)) return confMap;
        String cacheKey = MessageFormat.format(CacaheKeyConstants.WEBCONF_TYPE, cfgType);
        if (cacheStore.hasKey(cacheKey)) {
            //理论上永远不会为null，因为key存在，且value不能为null
            return (Map<String, String>) cacheStore.get(cacheKey).orElse(confMap);
        }
        List<Webconf> webconfList = webconfTransDao.queryWebconfsByType(cfgType);
        for (Webconf webConf : webconfList) {
            String value = null;
            if (ValueTypeEnum.STRING.getValue().equals(webConf.getValueType())) {
                value = format(webConf.getStringValue());
            } else if (ValueTypeEnum.FLOAT.getValue().equals(webConf.getValueType())) {
                value = formatFloat2Str(webConf.getFloatValue());
            } else if (ValueTypeEnum.INTEGER.getValue().equals(webConf.getValueType())) {
                value = formatInt2Str(webConf.getIntValue());
            } else if (ValueTypeEnum.BOOLEAN.getValue().equals(webConf.getValueType())) {
                value = formatBoolean2Str(webConf.getBooleanValue());
            } else if (ValueTypeEnum.DATE.getValue().equals(webConf.getValueType())) {
                value = DateUtil.formatTimestamp(webConf.getDateValue());
            }
            confMap.put(webConf.getKey(), value);
        }


        cacheStore.put(cacheKey, confMap);
        return confMap;
    }

    private BigDecimal formatStr2Float(String v) {
        if (StringUtils.isBlank(v)) {
            return null;
        }
        return new BigDecimal(v);

    }


    private Integer formatStr2Int(String v) {
        if (StringUtils.isBlank(v)) {
            return null;
        }
        return Integer.parseInt(v);

    }

    private void registerBizEvent(String key, BizEventOptTypeEnum optTypeEnum) {
        BizEventLogEvent event = new BizEventLogEvent(this, SecurityContextHolder.getContext());
        event.setBizEventTypeEnum(BizEventTypeEnum.WEBCONF);
        event.setBizEventOptTypeEnum(optTypeEnum);
        event.setDesc(key);
        applicationContext.publishEvent(event);
    }
}
