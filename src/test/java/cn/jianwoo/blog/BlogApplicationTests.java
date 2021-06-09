package cn.jianwoo.blog;

import cn.jianwoo.blog.base.BaseController;
import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.dao.base.TempArticleTransDao;
import cn.jianwoo.blog.dto.request.ArticleSubmitRequest;
import cn.jianwoo.blog.entity.extension.MenuExt;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.biz.MenuBizService;
import cn.jianwoo.blog.util.DomainUtil;
import cn.jianwoo.blog.util.JwtUtils;
import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private TempArticleTransDao tempArticleTransDao;

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

    @Test
    public void test2() {
        try {
//            TempArticle article=tempArticleTransDao.queryLastestTempArticle();
            BaseController controller = new BaseController();
            controller.convertParam("{\"title\":\"啊\",\"author\":\"正则\",\"articleContent\":\"<p>邓东东</p>\",\"tags\":\"40\",\"type\":\"40\",\"imgSrc\":\"\",\"visitType\":\"1\",\"password\":\"\",\"isComment\":1,\"subToken\":\"2797665c3ee240fbbe9a6ba478d4724f\",\"access_token\":\"\"}", ArticleSubmitRequest.class);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test3() {
        Map<String, Object> map = new HashMap<>();
        map.put("userName", "admin");
        map.put("userId","001");
//        String token = JwtUtils.sign(map, 3600_000);
//        System.out.println(token);
        String token="Bearer eyJhbGciOiJIUzUxMiJ9.eyJ1c2VyTmFtZSI6ImFkbWluIiwiZXhwIjoxNjIwMjg3MzcwLCJ1c2VySWQiOiIwMDEiLCJpYXQiOjE2MjAyODM3NzB9.9KT9ck8Rz92gKKIMqmdSwcqKMSNU9-w6wt22udkPGPGhNRaFkwmqzlv1kVCqV04RPMCu1XbH6oOH2fS6GGoErQ";
//        Map map1=JwtUtils.unSign(token);
//        System.out.println(JSON.toJSONString(map1));
    }

}
