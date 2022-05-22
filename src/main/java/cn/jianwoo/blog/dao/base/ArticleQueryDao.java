package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.Article;
import cn.jianwoo.blog.entity.ArticleWithBLOBs;
import cn.jianwoo.blog.exception.DaoException;

import java.util.List;

public interface ArticleQueryDao {


    /**
     * 根据主键查询<br>
     * 已废弃<br>
     *
     * @param oid 主键
     * @return
     * @author gulihua
     * @see {@link ArticleQueryDao.queryArticleByOidWithBLOBs}
     */
    @Deprecated
    ArticleWithBLOBs queryArticleByPrimaryKey(Long oid) throws DaoException;


    /**
     * 通过状态 查询文章
     *
     * @param status {@link cn.jianwoo.blog.enums.ArticleStatusEnum}
     * @return
     * @author gulihua
     */
    List<Article> queryArticleByStatus(String status);

    /**
     * 通过状态和访问类型 查询文章
     *
     * @param statusList     状态集合{@link cn.jianwoo.blog.enums.ArticleStatusEnum}
     * @param accessTypeList 访问类型集合{@link cn.jianwoo.blog.enums.ArticleAccessEnum}
     * @return
     * @author gulihua
     */
    List<Article> queryArticleByStatusAndAccessType(List<String> statusList, List<String> accessTypeList);


    /**
     * 通过类别id 查询文章 *
     *
     * @param typeId Menu主键
     * @return
     * @author gulihua
     */
    List<Article> queryArticleByType(Integer typeId);


    /**
     * 通过oid 查询有效的文章(STATUS({@link cn.jianwoo.blog.enums.ArticleStatusEnum}) != 99) <br>
     * 不含文章内容<br>
     *
     * @param oid 文章主键
     * @return
     * @author gulihua
     */
    Article queryArticleByOid(Long oid) throws DaoException;


    /**
     * 通过oid 查询有效的文章(STATUS({@link cn.jianwoo.blog.enums.ArticleStatusEnum}) != 99) <br>
     * 含文章内容<br>
     *
     * @param oid 主键
     * @return
     * @author gulihua
     */
    ArticleWithBLOBs queryArticleByOidWithBLOBs(Long oid) throws DaoException;
}

