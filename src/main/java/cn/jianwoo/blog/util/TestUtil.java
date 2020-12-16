package cn.jianwoo.blog.util;

import cn.jianwoo.blog.entity.Article;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class TestUtil {
    private static final TestUtil ourInstance = new TestUtil();

    private TestUtil() {
    }


    public static TestUtil getInstance() {
        return ourInstance;
    }


    public static void main(String[] args) throws Exception {
//        System.out.println(getInstance().getRandomStr(10));
//        System.out.println(getInstance().getRandomInt(0,1000));
//        System.out.println(getInstance().getRandomDate());
//        System.out.println(getInstance().getRandomLong());
//        Random random=new Random();
//        System.out.println(random.nextInt());
        Article article = new Article();
        DomainUtil.initTestData(article);
        System.out.println(article.toString());
    }


    public String getRandomStr(int len) {
        String ch = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            sb.append(ch.charAt(Math.abs(random.nextInt()) % (ch.length())));
        }
        return sb.toString();
    }


    public Integer getRandomInt(int min, int max) {
        Random random = new Random();
        return Math.abs(random.nextInt()) % (max - min) + min;
    }


    public Date getRandomDate() {
        Calendar calendar = Calendar.getInstance();
        Random random = new Random();
        calendar.set(Calendar.YEAR, Math.abs(random.nextInt()) % 20 + 2000);
        calendar.set(Calendar.MONTH, Math.abs(random.nextInt()) % 12);
        calendar.set(Calendar.DATE, Math.abs(random.nextInt()) % 30);
        calendar.set(Calendar.HOUR, Math.abs(random.nextInt()) % 60);
        calendar.set(Calendar.MINUTE, Math.abs(random.nextInt()) % 60);
        calendar.set(Calendar.SECOND, Math.abs(random.nextInt()) % 60);
        calendar.set(Calendar.MILLISECOND, Math.abs(random.nextInt()) % 1000);
        return calendar.getTime();
    }


    public Long getRandomLong() {
        Random random = new Random();
        return Math.abs(random.nextLong());
    }
}
