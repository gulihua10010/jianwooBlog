package cn.jianwoo.blog.service.biz.impl;

import cn.jianwoo.blog.config.page.WebConfDataConfig;
import cn.jianwoo.blog.dao.base.WebconfTransDao;
import cn.jianwoo.blog.dao.biz.WebconfBizDao;
import cn.jianwoo.blog.entity.Webconf;
import cn.jianwoo.blog.exception.DaoException;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.exception.WebconfBizException;
import cn.jianwoo.blog.service.biz.WebconfBizService;
import cn.jianwoo.blog.service.bo.WebconfBO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class WebconfBizServiceImpl implements WebconfBizService {

    @Autowired
    private WebconfTransDao webconfTransDao;
    @Autowired
    private WebconfBizDao webconfBizDao;

    @Override
    public Map<String, Object> queryConfigWithMap() {
        List<Webconf> configs = webconfTransDao.queryAllWebconf();
        Map<String, Object> conf = new HashMap<>();
        for (Webconf webconf : configs) {
            if (StringUtils.isNotBlank(webconf.getStringValue())) {
                conf.put(webconf.getKey(), webconf.getStringValue());
            } else if (webconf.getNumValue() != null) {
                conf.put(webconf.getKey(), webconf.getNumValue());
            } else if (webconf.getBooleanValue() != null) {
                conf.put(webconf.getKey(), webconf.getBooleanValue());
            }
        }
        log.info("query data : {}", conf);
        return conf;
    }


    @Override
    public void doUpdateConfigWithMap(Map<String, Object> confs) throws JwBlogException {
        for (Map.Entry<String, Object> conf : confs.entrySet()) {
            if (conf.getValue() == null) {
                continue;
            }
            Webconf oldWebconf = webconfTransDao.queryWebconfByKey(conf.getKey());
            if (null == oldWebconf) {
                continue;
            }
            if (conf.getValue() instanceof String) {
                oldWebconf.setStringValue((String) conf.getValue());
            } else if (conf.getValue() instanceof BigDecimal) {
                oldWebconf.setNumValue((BigDecimal) conf.getValue());
            } else if (conf.getValue() instanceof Boolean) {
                oldWebconf.setBooleanValue((Boolean) conf.getValue());
            }
            oldWebconf.setUpdateDate(new Date());
            log.info("update data : {}", oldWebconf);

            try {
                webconfTransDao.doUpdateByPrimaryKeySelective(oldWebconf);
            } catch (DaoException e) {
                throw WebconfBizException.MODIFY_FAILED_EXCEPTION.format(conf.getKey()).print();
            }
        }
    }


    @Override
    public WebconfBO convertWebconfMaptoBO(Map<String, Object> conf) {
        WebconfBO bo = new WebconfBO();
        bo.setTitle((String) conf.get(WebConfDataConfig.TITLE));
        bo.setAuthor((String) conf.get(WebConfDataConfig.AUTHOR));
        bo.setIsComment((Boolean) conf.get(WebConfDataConfig.IS_COMMENT));
        bo.setIsCaptchaOn((Boolean) conf.get(WebConfDataConfig.IS_LOGIN_NEED_CAPTCHA));
        bo.setDescription((String) conf.get(WebConfDataConfig.DESCRIPTION));
        bo.setDomain((String) conf.get(WebConfDataConfig.DOMAIN));
        bo.setFootHtml((String) conf.get(WebConfDataConfig.FOOT_HTML));
        bo.setHomeImg((String) conf.get(WebConfDataConfig.TOP_IMG));
        bo.setKeywords((String) conf.get(WebConfDataConfig.KEYWORDS));
        bo.setLogoImg((String) conf.get(WebConfDataConfig.LOGO));
        bo.setNumPerPage(10);
        if (conf.get(WebConfDataConfig.NUM_PER_PAGE) != null
                && StringUtils.isNotBlank(String.valueOf(conf.get(WebConfDataConfig.NUM_PER_PAGE)))) {
            bo.setNumPerPage(Integer.parseInt((String) conf.get(WebConfDataConfig.NUM_PER_PAGE)));
        }
        bo.setRecord((String) conf.get(WebConfDataConfig.RECORD));
        return bo;
    }


    @Override
    public WebconfBO queryConfigWithBO() {
        return convertWebconfMaptoBO(queryConfigWithMap());
    }


    @Override
    public void doUpdateConfigWithBO(WebconfBO bo) throws JwBlogException {
        doUpdateConfigWithMap(convertBOtoWebconfMap(bo));
    }


    @Override
    public Map<String, Object> convertBOtoWebconfMap(WebconfBO bo) {
        Map<String, Object> conf = new HashMap<>();
        conf.put(WebConfDataConfig.TITLE, bo.getTitle());
        conf.put(WebConfDataConfig.AUTHOR, bo.getAuthor());
        conf.put(WebConfDataConfig.IS_COMMENT, bo.getIsComment());
        conf.put(WebConfDataConfig.DESCRIPTION, bo.getDescription());
        conf.put(WebConfDataConfig.DOMAIN, bo.getDomain());
        conf.put(WebConfDataConfig.FOOT_HTML, bo.getFootHtml());
        conf.put(WebConfDataConfig.TOP_IMG, bo.getHomeImg());
        conf.put(WebConfDataConfig.KEYWORDS, bo.getKeywords());
        conf.put(WebConfDataConfig.LOGO, bo.getLogoImg());
        conf.put(WebConfDataConfig.NUM_PER_PAGE, bo.getNumPerPage());
        conf.put(WebConfDataConfig.RECORD, bo.getRecord());
        conf.put(WebConfDataConfig.IS_LOGIN_NEED_CAPTCHA, bo.getIsCaptchaOn());
        return conf;
    }
}
