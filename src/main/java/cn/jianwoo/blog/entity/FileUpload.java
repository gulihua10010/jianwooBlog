package cn.jianwoo.blog.entity;

import java.io.Serializable;
import java.util.Date;

public class FileUpload implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long oid;
    private String fileUuid;
    private String fileName;
    private String oldFileName;
    private Long size;
    private String type;
    private Long mediaTime;
    private String path;
    private String url;
    private String fileMd5;
    private String isChunk;
    private String isDelete;
    private Date uploadTime;
    private Date createDate;
    private Date updateDate;

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public String getFileUuid() {
        return fileUuid;
    }

    public void setFileUuid(String fileUuid) {
        this.fileUuid = fileUuid == null ? null : fileUuid.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getOldFileName() {
        return oldFileName;
    }

    public void setOldFileName(String oldFileName) {
        this.oldFileName = oldFileName == null ? null : oldFileName.trim();
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Long getMediaTime() {
        return mediaTime;
    }

    public void setMediaTime(Long mediaTime) {
        this.mediaTime = mediaTime;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getFileMd5() {
        return fileMd5;
    }

    public void setFileMd5(String fileMd5) {
        this.fileMd5 = fileMd5 == null ? null : fileMd5.trim();
    }

    public String getIsChunk() {
        return isChunk;
    }

    public void setIsChunk(String isChunk) {
        this.isChunk = isChunk == null ? null : isChunk.trim();
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete == null ? null : isDelete.trim();
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", oid=").append(oid);
        sb.append(", fileUuid=").append(fileUuid);
        sb.append(", fileName=").append(fileName);
        sb.append(", oldFileName=").append(oldFileName);
        sb.append(", size=").append(size);
        sb.append(", type=").append(type);
        sb.append(", mediaTime=").append(mediaTime);
        sb.append(", path=").append(path);
        sb.append(", url=").append(url);
        sb.append(", fileMd5=").append(fileMd5);
        sb.append(", isChunk=").append(isChunk);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", uploadTime=").append(uploadTime);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append("]");
        return sb.toString();
    }
}