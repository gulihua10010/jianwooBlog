package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.dao.base.WebconfQueryDao;
import cn.jianwoo.blog.dao.base.mapper.WebconfMapper;
import cn.jianwoo.blog.entity.Webconf;
import cn.jianwoo.blog.entity.example.WebconfExample;
import cn.jianwoo.blog.exception.DaoException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("webconfQueryDao")
public class WebconfQueryDaoImpl implements WebconfQueryDao {
    @Autowired
    WebconfMapper webconfMapper;

    @Override
    public Webconf queryWebconfByPrimaryKey(Long oid) throws DaoException {
        Webconf record = webconfMapper.selectByPrimaryKey(oid);
        if (null == record) {
            throw DaoException.DAO_SELECTONE_IS_NULL.print();
        }
        return record;
    }


    @Override
    public List<Webconf> queryAllWebconf() {
        WebconfExample example = new WebconfExample();
        return webconfMapper.selectByExample(example);
    }


    @Override
    public Webconf queryWebconfByKey(String key) {
        WebconfExample example = new WebconfExample();
        example.createCriteria().andKeyEqualTo(key);
        List<Webconf> webconfList = webconfMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(webconfList)) {
            return webconfList.get(0);
        }
        return null;
    }
}