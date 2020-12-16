package cn.jianwoo.blog.util;

import java.util.Calendar;

public class ArticleUtil {
    private static final ArticleUtil ourInstance = new ArticleUtil();

    private ArticleUtil() {
    }


    public static ArticleUtil getInstance() {
        return ourInstance;
    }


    public static void main(String[] args) {
//        Long currMullis=Calendar.getInstance().getTimeInMillis();
//        System.out.println(currMullis.toString().substring(currMullis.toString().length()-8,currMullis.toString().length()));
//        System.out.println(currMullis.toString());
//        System.out.println(TestUtil.getInstance().getRandomInt(0,10000));
        System.out.println(ArticleUtil.getInstance().generateArticleOid());
    }


    public Long generateArticleOid() {
        Long currMullis = Calendar.getInstance().getTimeInMillis();
        String str = currMullis.toString().substring(currMullis.toString().length() - 8
        );
        Integer r = TestUtil.getInstance().getRandomInt(0, 10000);
        if (r < 10) {
            str += ("000" + r.toString());
        } else if (r < 100) {
            str += ("00" + r.toString());
        } else if (r < 1000) {
            str += ("0" + r.toString());
        } else {
            str += (r.toString());
        }
        return Long.valueOf(str);
    }
}
