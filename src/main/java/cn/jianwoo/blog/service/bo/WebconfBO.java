package cn.jianwoo.blog.service.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author GuLihua
 * @Description
 * @date 2020-11-25 14:53
 */
@Data
@EqualsAndHashCode()
@NoArgsConstructor
@AllArgsConstructor
public class WebconfBO implements Serializable {
    private String title;
    private String author;
    private String keywords;
    private String description;
    private String record;
    private String domain;
    private String footHtml;
    private String logoImg;
    private String homeImg;
    private Integer numPerPage;
    private Boolean isComment;
}