package cn.jianwoo.blog;

import cn.jianwoo.blog.service.biz.CommentBizService;
import cn.jianwoo.blog.service.biz.TagsBizService;
import cn.jianwoo.blog.service.biz.UidGenService;
import cn.jianwoo.blog.service.biz.WebconfBizService;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@MapperScan(basePackages = {"cn.jianwoo.blog.dao.base.mapper", "cn.jianwoo.blog.dao.biz.mapper", "com.baidu.fsg.uid.worker.dao"})
@SpringBootTest
class BlogApplicationTests {
    @Autowired
    CommentBizService commentBizService;
    @Autowired
    WebconfBizService webconfBizService;
    @Autowired
    private TagsBizService tagsBizService;
    @Autowired
    private UidGenService uidGenService;


    @Test
    void contextLoads() {
        ////        DomainUtil.printLog(commentBizService.queryAllCommentPage(1, 2));
        //        DomainUtil.printLog(webconfBizService.queryConfigWithMap());
        //        System.out.println(JSON.toJSONString(webconfBizService.queryConfigWithMap()));

//        String s = StringEscapeUtils.escapeHtml4("d<button class=\"layui-btn layui-btn-sm reply\">回复</button>");
//        System.out.println(s);
        //        System.out.println(IpAddressUtil.getAreaById("127.0.0.1"));


//        Map jsonParam = new HashMap();
//        jsonParam.put("challenge", "642fdb299f42d0616e0f878153f26a9dip");
//        jsonParam.put("validate", "5d9b03e75b1c074f922654662028bb74");
//        jsonParam.put("seccode", "5d9b03e75b1c074f922654662028bb74|jordan");
//        jsonParam.put("json_format", "1");
//         String url="http://api.geetest.com/validate.php";
//         String param="challenge=642fdb299f42d0616e0f878153f26a9dip&validate=5d9b03e75b1c074f922654662028bb74&seccode=5d9b03e75b1c074f922654662028bb74|jordan&json_format=1";
//        HttpClientUtil.doPost(url,jsonParam);
//        System.out.println(uidGenService.getUid());

        System.out.println(StringUtils.compare(null, null)
        );


//
//        try
//        {
//            //            commentBizService.doAddComment(1L,"gg","dd","dddd",-1L,"","");
////            tagsBizService.doAddTag("dxd");
//        }
//        catch (JwBlogException e)
//        {
//            e.printStackTrace();
//        }

    }

}
