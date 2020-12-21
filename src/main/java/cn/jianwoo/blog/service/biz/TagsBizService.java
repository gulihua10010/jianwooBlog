package cn.jianwoo.blog.service.biz;

import cn.jianwoo.blog.entity.Tags;
import cn.jianwoo.blog.exception.JwBlogException;
import java.util.List;

public interface TagsBizService {
    /**
     * 添加标签
     *
     * @param name 标签名
     * @return
     * @author gulihua
     */
    void doAddTags(String name) throws JwBlogException;


    /**
     * 根据文章oid获取标签
     *
     * @param artOid 文章主键
     * @return
     * @author gulihua
     */
    List<Tags> queryTagsByArtOid(Long artOid);


    /**
     * 根据oid获取标签名
     *
     * @param oid 标签主键
     * @return
     * @author gulihua
     */
    String queryTagNameByOid(Long oid) throws JwBlogException;

    /**
     * 删除标签
     *
     * @param oid 标签主键 OID
     * @return
     * @author gulihua
     */
    void doRemoveTags(Long oid) throws JwBlogException;

    /**
     * 获取所有标签数量
     *
     * @return
     * @author gulihua
     */
    Integer countAllTags();

}
