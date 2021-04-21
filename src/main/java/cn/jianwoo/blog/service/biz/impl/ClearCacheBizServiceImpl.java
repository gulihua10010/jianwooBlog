package cn.jianwoo.blog.service.biz.impl;

import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.biz.ClearCacheBizService;
import cn.jianwoo.blog.service.bo.CacheBO;
import cn.jianwoo.blog.util.FileUtil;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @author GuLihua
 * @Description
 * @date 2020-09-30 15:04
 */
@Service
public class ClearCacheBizServiceImpl implements ClearCacheBizService {
    private static final Logger logger = LoggerFactory.getLogger(ClearCacheBizServiceImpl.class);

    @Value("${dir.temp.path}")
    private String temp;

    @Value("${dir.log.path}")
    private String log;

    @Value("${dir.cache.path}")
    private String cache;

    @Override
    public void clearCache(boolean isClearCache, boolean isClearLog, boolean isClearTemp) throws JwBlogException {
        // 当temp目录下有任务执行时，会生成lock文件
        if (isClearTemp) {
            File lockFile = new File(temp + File.separator + "lock");
            if (lockFile.exists()) {
                throw new JwBlogException("临时目录删除失败，目录下有任务在执行!");
            }
            FileUtil.deleteAllFilesIn(new File(temp));
            logger.info("Delete Temp folder [{}] successfully!", temp);
        }

        if (isClearCache) {
            FileUtil.deleteAllFilesIn(new File(cache));
            logger.info("Delete Cache folder [{}] successfully!", cache);
        }
        if (isClearLog) {
            FileUtil.deleteAllFilesIn(new File(log));
            logger.info("Delete Log folder [{}] successfully!", log);
        }

    }

    @Override
    public CacheBO queryCacheInfo() {
        CacheBO bo = new CacheBO();
        File tempFile = new File(temp);
        File cacheFile = new File(cache);
        File logFile = new File(log);
        if (tempFile.exists() && tempFile.isDirectory()) {
            bo.setTempSize(FileUtils.sizeOfDirectory(tempFile));
        }
        if (cacheFile.exists() && cacheFile.isDirectory()) {
            bo.setCacheSize(FileUtils.sizeOfDirectory(cacheFile));
        }
        if (logFile.exists() && logFile.isDirectory()) {
            bo.setLogSize(FileUtils.sizeOfDirectory(logFile));
        }
        return bo;
    }
}
