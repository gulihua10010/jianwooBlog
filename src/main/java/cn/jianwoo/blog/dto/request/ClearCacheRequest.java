package cn.jianwoo.blog.dto.request;

import cn.jianwoo.blog.base.BaseRequestDto;

/**
 * @author GuLihua
 * @Description
 * @date 2020-09-30 14:58
 */
public class ClearCacheRequest extends BaseRequestDto {
    private static final long serialVersionUID = -7558874574721985156L;
    /**
     * 临时目录
     */
    private boolean temp;
    /**
     * 日志目录
     */
    private boolean log;
    /**
     * 缓存目录
     */
    private boolean cache;


    public boolean isTemp() {
        return temp;
    }


    public void setTemp(boolean temp) {
        this.temp = temp;
    }


    public boolean isLog() {
        return log;
    }


    public void setLog(boolean log) {
        this.log = log;
    }


    public boolean isCache() {
        return cache;
    }


    public void setCache(boolean cache) {
        this.cache = cache;
    }
}
