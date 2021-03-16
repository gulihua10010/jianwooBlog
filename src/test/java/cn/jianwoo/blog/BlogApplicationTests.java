package cn.jianwoo.blog;

import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.entity.extension.MenuExt;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.biz.MenuBizService;
import cn.jianwoo.blog.util.DomainUtil;
import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@MapperScan(basePackages = {"cn.jianwoo.blog.dao.base.mapper", "cn.jianwoo.blog.dao.biz.mapper", "com.baidu.fsg.uid.worker.dao"})
@SpringBootTest
class BlogApplicationTests {
    //    @Autowired
//    CommentBizService commentBizService;
//    @Autowired
//    WebconfBizService webconfBizService;
//    @Autowired
//    private TagsBizService tagsBizService;
//    @Autowired
//    private UidGenService uidGenService;
    @Autowired
    private MenuBizService menuBizService;

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

//        System.out.println(StringUtils.compare(null, null)
//        );


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


        System.out.println(JSON.toJSONString(new BaseResponseDto()));
    }


    @Test
    public void test1() {
        try {
            List<MenuExt> menuExtList = menuBizService.queryBackGroudMenuList();
            DomainUtil.printLog(menuExtList);
        } catch (JwBlogException e) {
            e.printStackTrace();
        }

    }

}
