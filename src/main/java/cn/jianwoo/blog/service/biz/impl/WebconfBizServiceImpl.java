package cn.jianwoo.blog.service.biz.impl;

import cn.jianwoo.blog.dao.base.WebconfTransDao;
import cn.jianwoo.blog.dao.biz.WebconfBizDao;
import cn.jianwoo.blog.entity.Webconf;
import cn.jianwoo.blog.enums.ValueTypeEnum;
import cn.jianwoo.blog.exception.DaoException;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.exception.WebconfBizException;
import cn.jianwoo.blog.service.biz.WebconfBizService;
import cn.jianwoo.blog.service.bo.WebconfBO;
import cn.jianwoo.blog.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class WebconfBizServiceImpl implements WebconfBizService {

    @Autowired
    private WebconfTransDao webconfTransDao;
    @Autowired
    private WebconfBizDao webconfBizDao;


    @Override
    public List<WebconfBO> queryConfig() {
        List<Webconf> webconfList = webconfTransDao.queryEffectiveWebconf();
        List<WebconfBO> list = new ArrayList<>();
        webconfList.sort(Comparator.comparingInt(Webconf::getIndex));
        if (CollectionUtils.isNotEmpty(webconfList)) {
            webconfList.forEach(o -> {
                WebconfBO bo = new WebconfBO();
                BeanUtils.copyProperties(o, bo);
                if (ValueTypeEnum.STRING.getValue().equals(o.getValueType())) {
                    bo.setValue(o.getStringValue());
                } else if (ValueTypeEnum.NUMBER.getValue().equals(o.getValueType())) {
                    bo.setValue(format(o.getNumValue()));
                } else if (ValueTypeEnum.BOOLEAN.getValue().equals(o.getValueType())) {
                    bo.setValue(format(o.getBooleanValue()));
                } else if (ValueTypeEnum.DATE.getValue().equals(o.getValueType())) {
                    bo.setValue(DateUtil.formatTimestamp(o.getDateValue()));
                }
                list.add(bo);
            });
        }
        return list;

    }
    private String format(Object v)
    {
        if (v==null)
        {
            return "";
        }
        return String.valueOf(v);

    }


    @Override
    public void doUpdateConfig(List<WebconfBO> configList) throws JwBlogException {

        if (CollectionUtils.isNotEmpty(configList)) {
            for (WebconfBO o : configList) {
                try {
                    Webconf webconf = new Webconf();
                    BeanUtils.copyProperties(o, webconf);
                    if (ValueTypeEnum.STRING.getValue().equals(o.getValueType())) {
                        webconf.setStringValue(o.getValue());
                    } else if (ValueTypeEnum.NUMBER.getValue().equals(o.getValueType())) {
                        webconf.setNumValue(formatNumber(o.getValue()));
                    } else if (ValueTypeEnum.BOOLEAN.getValue().equals(o.getValueType())) {
                        webconf.setBooleanValue(Boolean.valueOf(o.getValue()));
                    } else if (ValueTypeEnum.DATE.getValue().equals(o.getValueType())) {
                        webconf.setDateValue(DateUtil.parseTimestamp(o.getValue()));
                    }
                    Webconf oldWebconf = webconfTransDao.queryWebconfByKey(webconf.getKey());
                    if (oldWebconf != null) {
                        oldWebconf.setStringValue(webconf.getStringValue());
                        oldWebconf.setNumValue(webconf.getNumValue());
                        oldWebconf.setBooleanValue(webconf.getBooleanValue());
                        oldWebconf.setDateValue(webconf.getDateValue());
                        oldWebconf.setUpdateDate(new Date());
                        webconfTransDao.doUpdateByPrimaryKey(oldWebconf);

                    }
                } catch (DaoException e) {
                    log.error("WebconfBizServiceImpl.doUpdateConfig exec failed, e:\n", e);
                    throw WebconfBizException.MODIFY_FAILED_EXCEPTION.format(o.getKey()).print();

                } catch (JwBlogException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private BigDecimal formatNumber(String v)
    {
        if (StringUtils.isBlank(v))
        {
            return null;
        }
        return new BigDecimal(v);

    }


}
