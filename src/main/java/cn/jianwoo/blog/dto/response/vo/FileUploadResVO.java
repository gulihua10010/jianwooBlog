package cn.jianwoo.blog.dto.response.vo;

import java.io.Serializable;

/**
 * @author GuLihua
 * @Description
 * @date 2020-08-18 15:53
 */
public class FileUploadResVO implements Serializable {
    private static final long serialVersionUID = 4563971387655597400L;
    /**
     * 文件名
     */
    private String fileName;

//    private String path;

    /**
     * 文件 web url
     */
    private String url;


    public String getFileName() {
        return fileName;
    }


    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


//    public String getPath()
//    {
//        return path;
//    }
//
//
//    public void setPath(String path)
//    {
//        this.path = path;
//    }


    public String getUrl() {
        return url;
    }


    public void setUrl(String url) {
        this.url = url;
    }
}
