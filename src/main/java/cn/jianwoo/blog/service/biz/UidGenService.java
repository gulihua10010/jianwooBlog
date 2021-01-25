package cn.jianwoo.blog.service.biz;

/**
 * @author GuLihua
 * @Description
 * @date 2021-01-23 17:16
 */
public interface UidGenService {

    /**
     * 获取文章的id
     *
     * @param
     * @return uid
     * @author gulihua
     */
    public long getUid();

    /**
     * 解析文章的id
     *
     * @param uid uid
     * @return 解析之后的uid 格式:<br/>
     * "{\"UID\":\"%d\",\"timestamp\":\"%s\",\"workerId\":\"%d\",\"sequence\":\"%d\"}"
     * @author gulihua
     */
    public String parseUid(Long uid);
}
