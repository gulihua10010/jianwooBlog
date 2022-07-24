package cn.jianwoo.blog;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import cn.hutool.extra.spring.SpringUtil;
import cn.jianwoo.blog.dao.base.EmailTemplateTransDao;
import cn.jianwoo.blog.enums.AsyncIpEnum;
import cn.jianwoo.blog.service.biz.NetWorkService;
import cn.jianwoo.blog.util.JwUtil;
import cn.jianwoo.blog.util.NotifiyUtil;
import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@RunWith(SpringJUnit4ClassRunner.class)
@MapperScan(basePackages = {"cn.jianwoo.blog.dao.base.mapper", "cn.jianwoo.blog.dao.biz.mapper", "com.baidu.fsg.uid.worker.dao"})
@SpringBootTest()
class BlogApplicationTests {
    //    @Autowired
//    CommentBizService commentBizService;
//    @Autowired
//    WebconfBizService webconfBizService;
//    @Autowired
//    private TagsBizService tagsBizService;
//    @Autowired
//    private UidGenService uidGenService;
//    @Autowired
//    private MenuBizService menuBizService;
//    @Autowired
//    private TempArticleTransDao tempArticleTransDao;

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


//        System.out.println(JSON.toJSONString(new BaseResponseDto()));
    }

//
//    @Test
//    public void test1() {
//        try {
//            List<MenuExt> menuExtList = menuBizService.queryBackGroudMenuList();
//            DomainUtil.printLog(menuExtList);
//        } catch (JwBlogException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    @Test
//    public void test2() {
//        try {
////            TempArticle article=tempArticleTransDao.queryLastestTempArticle();
//            BaseController controller = new BaseController();
//            controller.convertParam("{\"title\":\"啊\",\"author\":\"正则\",\"articleContent\":\"<p>邓东东</p>\",\"tags\":\"40\",\"type\":\"40\",\"imgSrc\":\"\",\"accessType\":\"1\",\"password\":\"\",\"isComment\":1,\"subToken\":\"2797665c3ee240fbbe9a6ba478d4724f\",\"access_token\":\"\"}", ArticleSubmitRequest.class);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    @Test
//    public void test3() {
//        Map<String, Object> map = new HashMap<>();
//        map.put("userName", "admin");
//        map.put("userId","001");
////        String token = JwtUtils.sign(map, 3600_000);
////        System.out.println(token);
//        String token="Bearer eyJhbGciOiJIUzUxMiJ9.eyJ1c2VyTmFtZSI6ImFkbWluIiwiZXhwIjoxNjIwMjg3MzcwLCJ1c2VySWQiOiIwMDEiLCJpYXQiOjE2MjAyODM3NzB9.9KT9ck8Rz92gKKIMqmdSwcqKMSNU9-w6wt22udkPGPGhNRaFkwmqzlv1kVCqV04RPMCu1XbH6oOH2fS6GGoErQ";
////        Map map1=JwtUtils.unSign(token);
////        System.out.println(JSON.toJSONString(map1));
//    }

    @Autowired
    private NotifiyUtil notifiyUtil;
    @Autowired
    private EmailTemplateTransDao emailTemplateTransDao;
    @Test
     void EmailTEst() throws Exception
    {//gulh+unit-error<gulh+unit-error@plbizgp.com>
        notifiyUtil.sendEmail("1729846470@qq.com,gulh+unit-error<gulh+unit-error@plbizgp.com>","test1","test content");
    }
    @Test
    void Test1()
    {
//        System.out.println(JSON.toJSONString(emailTemplateTransDao.queryAllEmailTplList()));
        //构建
//        String pwd = new String(Base64.decode("RmVqazZjTUxFNVVnQmpRaW04TXNuUT09"));
//        System.out.println(pwd);
//        String key = new String(Base64.decode("ZEhsd1pUMWliRzluSUE9PQ=="));
//        System.out.println(key);
//        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, "dHlwZT1ibG9nIA==".getBytes(StandardCharsets.UTF_8));
//        //解密为字符串
//        String decryptStr = aes.decryptStr("b7YLsLqb2A8IX7nM9+wTUg==", CharsetUtil.CHARSET_UTF_8);
        //构建
         final  ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("application");
        String base64Security = RESOURCE_BUNDLE.getString("aes.secret");

        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES,
                new String(Base64.decode(base64Security)).getBytes(StandardCharsets.UTF_8));
        //解密为字符串
        String decryptStr = aes.decryptStr((Base64.decode(Base64.decode("RmVqazZjTUxFNVVnQmpRaW04TXNuUT09"))), CharsetUtil.CHARSET_UTF_8);
        System.out.println(decryptStr);
        System.out.println(JwUtil.decrypt("RmVqazZjTUxFNVVnQmpRaW04TXNuUT09"));
    }


    @Autowired
    private NetWorkService netWorkService;
    @Test
    void Test2()
    {
         execIpAreaTask(10L,"127.0.0.1", AsyncIpEnum.COMMENT);
    }
    private static final String IP_REX_PATTERN = "((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})(\\.((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})){3}";

    public void execIpAreaTask(Long oid, String ip, AsyncIpEnum asyncIpEnum) {

        System.out.println(">> Start async task execIpAreaTask");
        try {
            if (StringUtils.isNotBlank(ip)) {
                Pattern r = Pattern.compile(IP_REX_PATTERN);
                Matcher m = r.matcher(ip.trim());
                if (m.matches()) {
                    String area = netWorkService.getIpArea(ip.trim());
                    Object o = asyncIpEnum.getClazz().newInstance();
                    BeanUtil.setFieldValue(o, asyncIpEnum.getPrimaryKey(), oid);
                    BeanUtil.setFieldValue(o, asyncIpEnum.getField(), area);
                    Object svcBean = SpringUtil.getBean(asyncIpEnum.getSvcBean());
                    MethodUtils.invokeMethod(svcBean, "doUpdateByPrimaryKeySelective", o);

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(">> End async task execIpAreaTask");

    }
}
