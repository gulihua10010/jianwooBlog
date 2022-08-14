package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.dao.base.MenuQueryDao;
import cn.jianwoo.blog.dao.base.mapper.MenuMapper;
import cn.jianwoo.blog.entity.Menu;
import cn.jianwoo.blog.entity.example.MenuExample;
import cn.jianwoo.blog.enums.MenuTypeEnum;
import cn.jianwoo.blog.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("menuQueryDao")
public class MenuQueryDaoImpl implements MenuQueryDao {
    @Autowired
    MenuMapper menuMapper;

    @Override
    public Menu queryMenuByPrimaryKey(Long oid) throws DaoException {
        Menu record = menuMapper.selectByPrimaryKey(oid);
        if (null == record) {
            throw DaoException.DAO_SELECTONE_IS_NULL.print();
        }
        return record;
    }


    @Override
    public List<Menu> queryMenuByType(String type) {
        MenuExample example = new MenuExample();
        example.createCriteria().andTypeEqualTo(type);
        return menuMapper.selectByExample(example);
    }

    @Override
    public List<Menu> queryMenuByTypeAndCategory(String type, Boolean category) {
        MenuExample example = new MenuExample();
        example.createCriteria().andTypeEqualTo(type).andFlagCategoryEqualTo(category);
        return menuMapper.selectByExample(example);
    }

    @Override
    public List<Menu> queryEffectiveMenuByType(String type) {
        MenuExample example = new MenuExample();
        example.createCriteria().andTypeEqualTo(type).andValidEqualTo(true);
        return menuMapper.selectByExample(example);
    }

    @Override
    public List<Menu> queryEffectiveMenuByTypeAndCategory(String type, Boolean category) {
        MenuExample example = new MenuExample();
        example.createCriteria().andTypeEqualTo(type).andValidEqualTo(true).andFlagCategoryEqualTo(category);
        return menuMapper.selectByExample(example);
    }


    @Override
    public List<Menu> queryMenuByParentIdAndType(Long parentOid, String type) {
        MenuExample example = new MenuExample();
        example.createCriteria().andParentOidEqualTo(parentOid).andTypeEqualTo(type);
        return menuMapper.selectByExample(example);
    }

    @Override
    public List<Menu> querySubCategoryByParentId(Long parentOid) {
        MenuExample example = new MenuExample();
        example.createCriteria().andParentOidEqualTo(parentOid).andFlagCategoryEqualTo(true).andValidEqualTo(true)
                .andTypeEqualTo(MenuTypeEnum.FRONTEND.getValue());
        return menuMapper.selectByExample(example);
    }
}