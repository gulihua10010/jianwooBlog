package cn.jianwoo.blog.dao.biz.impl;

import cn.jianwoo.blog.dao.biz.MenuBizDao;
import cn.jianwoo.blog.dao.biz.mapper.MenuBizMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author GuLihua
 * @Description
 * @date 2020-08-04 18:17
 */
@Service
public class MenuBizDaoImpl implements MenuBizDao {
    @Autowired
    private MenuBizMapper menuBizMapper;

    @Override
    public int countMenu(String type) {
        return menuBizMapper.countMenu(type);
    }


    @Override
    public int queryMaxIndexMenuWithSameLevel(Map<String, Object> params) {
        return menuBizMapper.selectMaxIndexMenuWithSameLevel(params);
    }
}
