package cn.jianwoo.blog.service.base.impl;

import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.constants.ExceptionConstants;
import cn.jianwoo.blog.constants.WebConfDataConfig;
import cn.jianwoo.blog.dao.base.FileUploadTransDao;
import cn.jianwoo.blog.entity.FileUpload;
import cn.jianwoo.blog.exception.DaoException;
import cn.jianwoo.blog.exception.FileUploadBizException;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.base.FileUploadService;
import cn.jianwoo.blog.service.biz.WebconfBizService;
import cn.jianwoo.blog.service.bo.FileUploadBO;
import cn.jianwoo.blog.util.DateUtil;
import cn.jianwoo.blog.util.FileUtil;
import cn.jianwoo.blog.util.JwUtil;
import cn.jianwoo.blog.util.QiniuUploadUtil;
import com.alibaba.fastjson.JSONObject;
import it.sauronsoftware.jave.AudioInfo;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;
import it.sauronsoftware.jave.VideoInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Date;

/**
 * @author GuLihua
 * @Description
 * @date 2020-08-18 14:06
 */
@Service
@Slf4j
public class FileUploadServiceImpl implements FileUploadService {
    @Value("${file.upload.path}")
    private String uploadPath;
    /**
     * 上传目录
     */
    private String cdnContext;
    @Autowired
    private FileUploadTransDao fileUploadTransDao;
    @Autowired
    private WebconfBizService webconfBizService;
    @Autowired
    private QiniuUploadUtil qiniuUploadUtil;
    public static final String[] mediaExt = {"mp3", "wma", "wav", "cda", "ape", "ogm", "flv", "wmv", "mpg", "webm", "ogv", "asx", "mpeg", "mp4", "avi", "amv", "rmvb", "mov", "mtv", "wmv", "3gp", "amv"};


    public static String getMD5(File file) {
        FileInputStream fileInputStream = null;
        try {
            MessageDigest MD5 = MessageDigest.getInstance("MD5");
            fileInputStream = new FileInputStream(file);
            byte[] buffer = new byte[8192];
            int length;
            while ((length = fileInputStream.read(buffer)) != -1) {
                MD5.update(buffer, 0, length);
            }
            return new String(Hex.encodeHex(MD5.digest()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                log.error("FileUploadServiceImpl.getMD5 exec failed.\n", e);

            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public FileUploadBO doUpload(MultipartFile multipartFile, String url, boolean isReName) throws JwBlogException {
        if (StringUtils.isBlank(uploadPath)) {
            throw new JwBlogException(ExceptionConstants.FILE_PATH_INVAILD, "The upload path is invalid");
        }
        File file = new File(uploadPath);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                throw new JwBlogException(ExceptionConstants.DIR_CREATE_FAILED, "Upload directory creation failed");
            }
        }
        // 原始文件名
        String oldName = multipartFile.getOriginalFilename();
        // 文件类型
        String fileType = getFileExt(oldName);
        String uuid = JwUtil.randomUUIDWithoutDash();
        String ts = DateUtil.getNowTimestamp();
        String yyyyMM = DateUtil.getCommDateFolder();
        String newFilename = isReName ? uuid + Constants.SEPARATE_HYPHEN + ts + Constants.FILE_POINT + fileType : oldName;
        String filePath = uploadPath + File.separator + yyyyMM + File.separator + newFilename;
        File newFile = new File(filePath);
        if (!newFile.getParentFile().exists()) {
            try {
                FileUtil.createDir(newFile.getParentFile());
            } catch (IOException e) {
                log.error("Director create failed, exception:\n", e);
                throw new JwBlogException(ExceptionConstants.FILE_TRANSFER_FAILED,
                        ExceptionConstants.FILE_TRANSFER_FAILED_DESC);
            }
        }
        try {
            multipartFile.transferTo(newFile);
        } catch (IOException e) {
            log.error("File upload failed, exception:\n", e);
            throw new JwBlogException(ExceptionConstants.FILE_TRANSFER_FAILED,
                    ExceptionConstants.FILE_TRANSFER_FAILED_DESC);

        }


        FileUpload fileUpload = new FileUpload();
        String isUpload2Cdn = webconfBizService.queryWebconfByKey(WebConfDataConfig.IS_UPLOAD_TO_QINIU_CDN);
        if (Constants.TRUE.equals(isUpload2Cdn)) {
            cdnContext = webconfBizService.queryWebconfByKey(WebConfDataConfig.QINIUYUN_CONTEXT);
            String cdnUrl = qiniuUploadUtil.upload(filePath, cdnContext + File.separator + yyyyMM, newFile.getName());
            fileUpload.setCdnUrl(cdnUrl);
        }
        long fileSize = multipartFile.getSize();
        fileUpload.setOldFileName(oldName);
        fileUpload.setFileName(newFilename);

        if (Arrays.asList(mediaExt).contains(fileType)) {
            JSONObject o = getMediaInfo(filePath);
            if (null != o) {
                fileUpload.setMediaInfo(o.toJSONString());
            }
        }
        fileUpload.setPath(filePath);
        fileUpload.setFileUuid(uuid);
        fileUpload.setSize(fileSize);
        fileUpload.setType(fileType);
        fileUpload.setUploadTime(new Date());
        fileUpload.setFileMd5(getMD5(newFile));
        fileUpload.setUrl(url + Constants.URL_SEPARATOR + newFilename);
        fileUpload.setIsDelete(false);
        fileUpload.setIsChunk(false);
        fileUpload.setCreateTime(new Date());
        fileUpload.setUpdateTime(new Date());
        try {
            fileUploadTransDao.doInsertSelective(fileUpload);
        } catch (DaoException e) {
            throw FileUploadBizException.CREATE_FAILED_EXCEPTION.print();
        }
        FileUploadBO fileUploadBO = new FileUploadBO();
        BeanUtils.copyProperties(fileUpload, fileUploadBO);

        return fileUploadBO;
    }


    private String getFileExt(String fileName) {
        if (StringUtils.isBlank(fileName)) {
            return Constants.BLANK;
        }
        if (!fileName.contains(Constants.FILE_POINT)) {
            return Constants.BLANK;
        }
        return StringUtils.trim(fileName.substring(fileName.lastIndexOf(Constants.FILE_POINT) + 1)).toLowerCase();
    }

    private String getFileName(String name) {
        if (StringUtils.isBlank(name)) {
            return Constants.BLANK;
        }
        if (!name.contains(Constants.FILE_POINT)) {
            return name;
        }
        return name.substring(0, name.lastIndexOf(Constants.FILE_POINT));
    }

    private JSONObject getMediaInfo(String filePath) {
        JSONObject media = new JSONObject();
        File source = new File(filePath);
        Encoder encoder = new Encoder();
        try {
            MultimediaInfo m = encoder.getInfo(source);
            media.put("format", m.getFormat());
            media.put("duration", m.getDuration());
            AudioInfo audioInfo = m.getAudio();
            if (null != audioInfo) {
                JSONObject audioObj = new JSONObject();
                audioObj.put("bitRate", m.getAudio().getBitRate());
                audioObj.put("channels", m.getAudio().getChannels());
                audioObj.put("decoder", m.getAudio().getDecoder());
                audioObj.put("samplingRate", m.getAudio().getSamplingRate());
                media.put("audioInfo", audioObj);
            }

            VideoInfo videoInfo = m.getVideo();
            if (null != videoInfo) {
                JSONObject videoObj = new JSONObject();

                videoObj.put("videoBitRate", m.getVideo().getBitRate());
                videoObj.put("width", m.getVideo().getSize().getWidth());
                videoObj.put("height", m.getVideo().getSize().getHeight());
                media.put("videoInfo", videoObj);

            }
            return media;

        } catch (Exception e) {
            log.error("FileUploadServiceImpl.getMediaInfo exec failed.\n", e);
        }
        return null;
    }
}
