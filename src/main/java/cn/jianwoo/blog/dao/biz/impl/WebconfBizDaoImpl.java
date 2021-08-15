package cn.jianwoo.blog.dao.biz.impl;

import cn.jianwoo.blog.constants.ExceptionConstants;
import cn.jianwoo.blog.dao.biz.WebconfBizDao;
import cn.jianwoo.blog.dao.biz.mapper.WebconfBizMapper;
import cn.jianwoo.blog.entity.Webconf;
import cn.jianwoo.blog.entity.extension.WebconfExt;
import cn.jianwoo.blog.exception.DaoException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author GuLihua
 * @Description
 * @date 2020-11-20 10:25
 */
@Service
public class WebconfBizDaoImpl implements WebconfBizDao {
    @Autowired
    WebconfBizMapper webconfBizMapper;

    @Override
    public List<Webconf> queryAllWebconf() {
        return webconfBizMapper.selectAllWebconf();
    }

    @Override
    public List<WebconfExt> queryEffectiveWebconf() {
        return webconfBizMapper.selectEffectiveWebconf();
    }

    @Override
    public void doUpdateWebconfByKey(Webconf webconf, String key) throws DaoException {
        if (StringUtils.isBlank(key)) {
            throw new DaoException(ExceptionConstants.DAO_PARAM_IS_EMPTY, String.format("param key cannot be empty."));
        }
        int updRlt = webconfBizMapper.updateWebconfByKey(webconf, key);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }
}
