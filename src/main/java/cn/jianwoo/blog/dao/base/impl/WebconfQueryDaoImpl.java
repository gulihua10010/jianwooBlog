package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.constants.ExceptionConstants;
import cn.jianwoo.blog.dao.base.WebconfQueryDao;
import cn.jianwoo.blog.dao.base.mapper.WebconfMapper;
import cn.jianwoo.blog.entity.Webconf;
import cn.jianwoo.blog.entity.example.WebconfExample;
import cn.jianwoo.blog.exception.DaoException;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.exception.ValidationException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("webconfQueryDao")
public class WebconfQueryDaoImpl implements WebconfQueryDao {
    @Autowired
    WebconfMapper webconfMapper;

    @Override
    public Webconf queryWebconfByPrimaryKey(String key) throws DaoException {
        Webconf record = webconfMapper.selectByPrimaryKey(key);
        if (null == record) {
            throw DaoException.DAO_SELECTONE_IS_NULL.print();
        }
        return record;
    }


    @Override
    public List<Webconf> queryEffectiveWebconf() {
        WebconfExample example = new WebconfExample();
        example.createCriteria().andValidEqualTo(true);
        return webconfMapper.selectByExample(example);
    }


    @Override
    public Webconf queryWebconfByKey(String key) throws JwBlogException {
        WebconfExample example = new WebconfExample();
        example.createCriteria().andValidEqualTo(true).andKeyEqualTo(key);
        List<Webconf> webconfList = webconfMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(webconfList)) {
            throw new ValidationException(ExceptionConstants.VALIDATION_FAILED_NULL,
                    ExceptionConstants.VALIDATION_FAILED_INVALID_DESC_CN);
        }
        return webconfList.get(0);
    }

    @Override
    public List<Webconf> queryWebconfsByType(String cfgType) throws JwBlogException {
        WebconfExample example = new WebconfExample();
        example.createCriteria().andCfgTypeEqualTo(cfgType);
        List<Webconf> webconfList = webconfMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(webconfList)) {
            throw new ValidationException(ExceptionConstants.VALIDATION_FAILED_NULL,
                    ExceptionConstants.VALIDATION_FAILED_INVALID_DESC_CN);
        }
        return webconfList;
    }
}