package cn.jianwoo.blog.config;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author GuLihua
 * @Description
 * @date 2021-02-24 18:02
 */
public class LongToStringSerializerConfig implements ObjectSerializer {
    @Override
    public void write(JSONSerializer serializer, Object o, Object o1, Type type, int i) throws IOException {
        SerializeWriter out = serializer.out;
        if (o == null) {
            out.writeNull();
            return;
        }
        String strVal = o.toString();
        out.writeString(strVal);
    }
}
