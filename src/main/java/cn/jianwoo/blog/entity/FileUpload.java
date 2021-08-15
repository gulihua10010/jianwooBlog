package cn.jianwoo.blog.entity;

import java.io.Serializable;
import java.util.Date;

public class FileUpload implements Serializable {
    private Long oid;

    private String fileUuid;

    private String fileName;

    private String oldFileName;

    private Long size;

    private String type;

    private String mediaInfo;

    private String path;

    private String url;

    private String fileMd5;

    private Boolean isChunk;

    private Boolean isDelete;

    private Date uploadTime;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

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

    public String getMediaInfo() {
        return mediaInfo;
    }

    public void setMediaInfo(String mediaInfo) {
        this.mediaInfo = mediaInfo == null ? null : mediaInfo.trim();
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

    public Boolean getIsChunk() {
        return isChunk;
    }

    public void setIsChunk(Boolean isChunk) {
        this.isChunk = isChunk;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
        sb.append(", mediaInfo=").append(mediaInfo);
        sb.append(", path=").append(path);
        sb.append(", url=").append(url);
        sb.append(", fileMd5=").append(fileMd5);
        sb.append(", isChunk=").append(isChunk);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", uploadTime=").append(uploadTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}