package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.dao.base.TempArticleQueryDao;
import cn.jianwoo.blog.dao.base.mapper.TempArticleMapper;
import cn.jianwoo.blog.entity.TempArticle;
import cn.jianwoo.blog.entity.example.TempArticleExample;
import cn.jianwoo.blog.enums.TempArticleStatusEnum;
import cn.jianwoo.blog.exception.DaoException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("tempArticleQueryDao")
public class TempArticleQueryDaoImpl implements TempArticleQueryDao {
    @Autowired
    TempArticleMapper tempArticleMapper;

    @Override
    public TempArticle queryTempArticleByPrimaryKey(Long oid) throws DaoException {
        TempArticle record = tempArticleMapper.selectByPrimaryKey(oid);
        if (null == record) {
            throw DaoException.DAO_SELECTONE_IS_NULL.print();
        }
        return record;
    }

    @Override
    public TempArticle queryLastestTempArticle(Long oldOid, String pageType) throws DaoException {
        TempArticleExample example = new TempArticleExample();
        TempArticleExample.Criteria criteria = example.createCriteria().andStatusEqualTo(TempArticleStatusEnum.TEMP.getValue())
                .andPageTypeEqualTo(pageType);

        if (oldOid != null) {
            criteria.andOldOidEqualTo(oldOid);
        }
        example.setOrderByClause("UPDATE_TIME DESC");
        example.setRows(1);
        example.setStart(0);
        List<TempArticle> list = tempArticleMapper.selectByExampleWithBLOBs(example);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }
}