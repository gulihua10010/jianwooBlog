package cn.jianwoo.blog.controller.main.api;

import cn.jianwoo.blog.annotation.IpLimit;
import cn.jianwoo.blog.base.BaseController;
import cn.jianwoo.blog.builder.JwBuilder;
import cn.jianwoo.blog.config.apiversion.ApiVersion;
import cn.jianwoo.blog.config.router.main.TagsMainApiUrlConfig;
import cn.jianwoo.blog.dto.response.TagListResponse;
import cn.jianwoo.blog.dto.response.vo.TagsVO;
import cn.jianwoo.blog.service.biz.TagsBizService;
import cn.jianwoo.blog.service.bo.TagsBO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gulihua
 * @Description
 * @date 2022-06-09 11:14
 */
@RestController
@RequestMapping(TagsMainApiUrlConfig.URL_PREFIX)
@Slf4j
public class TagsApiMainController extends BaseController {

    @Autowired
    private TagsBizService tagsBizService;


    /**
     * 获取所有标签<br/>
     * url:/api/tags/query/all/list<br/>
     *
     * @return 返回响应 {@link TagListResponse}<br/>
     * status<br/>
     * data<br/>
     * --id<br/>
     * --name<br/>
     * --count<br/>
     * @author gulihua
     */
    @ApiVersion()
    @PostMapping(TagsMainApiUrlConfig.URL_QUERY_ALL_LIST)
    @IpLimit(key = "queryAllTags")
    public String queryAllTags(@RequestBody String param) {

        try {
            TagListResponse response = TagListResponse.getInstance();
            List<TagsVO> list = new ArrayList<TagsVO>();
            List<TagsBO> tags = tagsBizService.queryMainAllTags(request.getRemoteAddr());
            if (CollectionUtils.isNotEmpty(tags)) {
                for (TagsBO tag : tags) {
                    TagsVO vo = JwBuilder.of(TagsVO::new)
                            .with(TagsVO::setId, tag.getId())
                            .with(TagsVO::setCount, tag.getCount())
                            .with(TagsVO::setName, StringEscapeUtils.escapeHtml4(tag.getName())).build();
                    list.add(vo);
                }
            }

            response.setData(list);
            return super.responseToJSONString(response);
        } catch (Exception e) {
            return super.exceptionToString(e);

        }


    }
}
