package cn.jianwoo.blog.service.base.impl;

import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.constants.ExceptionConstants;
import cn.jianwoo.blog.dao.base.FileUploadTransDao;
import cn.jianwoo.blog.entity.FileUpload;
import cn.jianwoo.blog.exception.DaoException;
import cn.jianwoo.blog.exception.FileUploadBizException;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.base.FileUploadService;
import cn.jianwoo.blog.util.DateUtil;
import cn.jianwoo.blog.util.JwUtil;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
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
    @Autowired
    private FileUploadTransDao fileUploadTransDao;

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
                e.printStackTrace();
            }
        }
    }

    @Override
    public FileUpload doUpload(MultipartFile multipartFile, String url, boolean isReName) throws JwBlogException {
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
        String newFilename = isReName ? uuid + Constants.SEPARATE_HYPHEN + ts + Constants.FILE_POINT + fileType : oldName;
        String filePath = uploadPath + File.separator + newFilename;
        File newFile = new File(filePath);
        try {
            multipartFile.transferTo(newFile);
        } catch (IOException e) {
            log.error("File upload failed, exception:\n", e);
            throw new JwBlogException(ExceptionConstants.FILE_TRANSFER_FAILED,
                    ExceptionConstants.FILE_TRANSFER_FAILED_DESC);

        }

        FileUpload fileUpload = new FileUpload();
        long fileSize = multipartFile.getSize();
        fileUpload.setOldFileName(oldName);
        fileUpload.setFileName(newFilename);
        if ("mp3mp4".indexOf(fileType) > -1) {
            fileUpload.setMediaTime(getDuration(filePath));
        }
        fileUpload.setPath(filePath);
        fileUpload.setFileUuid(uuid);
        fileUpload.setSize(fileSize);
        fileUpload.setType(fileType);
        fileUpload.setUploadTime(new Date());
        fileUpload.setFileMd5(getMD5(newFile));
        fileUpload.setUrl(url + Constants.URL_SEPARATOR + newFilename);
        fileUpload.setIsDelete(Constants.NO);
        fileUpload.setIsChunk(Constants.NO);
        fileUpload.setCreateDate(new Date());
        fileUpload.setUpdateDate(new Date());
        try {
            fileUploadTransDao.doInsert(fileUpload);
        } catch (DaoException e) {
            throw FileUploadBizException.CREATE_FAILED_EXCEPTION.print();
        }
        return fileUpload;
    }

    private String getFileExt(String fileName) {
        if (StringUtils.isBlank(fileName)) {
            return Constants.BLANK;
        }
        if (fileName.indexOf(Constants.FILE_POINT) == -1) {
            return Constants.BLANK;
        }
        return StringUtils.trim(fileName.substring(fileName.lastIndexOf(Constants.FILE_POINT) + 1)).toLowerCase();
    }

    private String getFileName(String name) {
        if (StringUtils.isBlank(name)) {
            return Constants.BLANK;
        }
        if (name.indexOf(Constants.FILE_POINT) == -1) {
            return name;
        }
        return name.substring(0, name.lastIndexOf(Constants.FILE_POINT));
    }

    private long getDuration(String filePath) {

        File source = new File(filePath);
        Encoder encoder = new Encoder();
        try {
            MultimediaInfo m = encoder.getInfo(source);
            return m.getDuration();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
