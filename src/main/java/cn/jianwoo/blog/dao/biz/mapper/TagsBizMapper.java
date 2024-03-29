package cn.jianwoo.blog.dao.biz.mapper;

import cn.jianwoo.blog.entity.extension.ArticleTagsExt;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TagsBizMapper {

    /**
     * 通过文章oid获取标签
     *
     * @param artOid 文章主键 OID
     * @return
     * @author gulihua
     */
    List<ArticleTagsExt> selectTagsByArtOid(Long artOid);

    /**
     * 获取所有标签数量
     *
     * @return
     * @author gulihua
     */
    Integer countAllTags();

    /**
     * 获取所有标签
     * @param isContainPrivate 是否包含私密文章
     * @return
     * @author gulihua
     */
    List<ArticleTagsExt> selectAllTags(@Param("isContainPrivate") Boolean isContainPrivate);

}