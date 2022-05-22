package cn.jianwoo.blog.service.base;

import cn.jianwoo.blog.entity.Article;
import cn.jianwoo.blog.entity.ArticleWithBLOBs;
import cn.jianwoo.blog.exception.JwBlogException;

/**
 * @author gulihua
 * @Description
 * @date 2022-03-13 19:59
 */
public interface ArticleBaseService {


    /**
     * 通过oid 查询有效的文章(STATUS({@link cn.jianwoo.blog.enums.ArticleStatusEnum}) != 99) <br>
     * 不含文章内容<br>
     *
     * @param artOid 文章主键
     * @return
     * @author gulihua
     */
    Article queryArticleByOid(Long artOid) throws JwBlogException;


    /**
     * 通过oid 查询有效的文章(STATUS({@link cn.jianwoo.blog.enums.ArticleStatusEnum}) != 99) <br>
     * 含文章内容<br>
     *
     * @param artOid 主键
     * @return
     * @author gulihua
     */
    ArticleWithBLOBs queryArticleByOidWithBLOBs(Long artOid) throws JwBlogException;
}
