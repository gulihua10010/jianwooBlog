package cn.jianwoo.blog;

import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.biz.CommentBizService;
import cn.jianwoo.blog.service.biz.TagsBizService;
import cn.jianwoo.blog.service.biz.WebconfBizService;
import org.apache.commons.lang3.StringEscapeUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest class BlogApplicationTests
{
    @Autowired CommentBizService commentBizService;
    @Autowired WebconfBizService webconfBizService;
    @Autowired private TagsBizService tagsBizService;


    @Test void contextLoads()
    {
        ////        DomainUtil.printLog(commentBizService.queryAllCommentPage(1, 2));
        //        DomainUtil.printLog(webconfBizService.queryConfigWithMap());
        //        System.out.println(JSON.toJSONString(webconfBizService.queryConfigWithMap()));

        String s = StringEscapeUtils.escapeHtml4("d<button class=\"layui-btn layui-btn-sm reply\">回复</button>");
        System.out.println(s);
        //        System.out.println(IpAddressUtil.getAreaById("127.0.0.1"));

        try
        {
            //            commentBizService.doAddComment(1L,"gg","dd","dddd",-1L,"","");
            tagsBizService.doAddTags("dxd");
        }
        catch (JwBlogException e)
        {
            e.printStackTrace();
        }

    }

}
