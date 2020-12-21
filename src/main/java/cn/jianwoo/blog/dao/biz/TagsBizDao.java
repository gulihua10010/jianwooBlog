package cn.jianwoo.blog.dao.biz;

import cn.jianwoo.blog.entity.extension.ArticleTagsExt;
import java.util.List;

public interface TagsBizDao {
    /**
     * 通过文章oid获取标签
     *
     * @param artOid 文章主键 [OID]
     * @return
     * @author gulihua
     */
    List<ArticleTagsExt> queryTagsByArtOid(Long artOid);

    /**
     * 获取所有标签数量
     *
     * @return
     * @author gulihua
     */
    Integer countAllTags();
}