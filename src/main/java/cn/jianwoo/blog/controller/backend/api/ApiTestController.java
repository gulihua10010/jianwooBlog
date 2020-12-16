package cn.jianwoo.blog.controller.backend.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GuLihua
 * @Description
 * @date 2020-10-14 19:28
 */
@RestController
@RequestMapping("/gt")
public class ApiTestController {

    @PostMapping("/preauth/query/list")
    public String gtPreAuthTest(@RequestBody String param) {
        System.out.println(param);
        JSONObject res = new JSONObject();
        res.put("retCode", "000000");
        res.put("retMsg", "");
        JSONArray reqNoList = new JSONArray();
        JSONObject reqNo = new JSONObject();
        reqNo.put("reqNo", "reqNo001");
        reqNo.put("result", "result001");
        JSONObject reqNo1 = new JSONObject();
        reqNo1.put("reqNo", "reqNo002");
        reqNo1.put("result", "result002");
        reqNoList.add(reqNo);
        reqNoList.add(reqNo1);
        res.put("list", reqNoList);
        return res.toJSONString();

    }

}
