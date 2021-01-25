package cn.jianwoo.blog.service.biz.impl;

import cn.jianwoo.blog.service.biz.UidGenService;
import com.baidu.fsg.uid.UidGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UidGenServiceImpl implements UidGenService {

    @Resource
    private UidGenerator uidGenerator;


    @Override
    public long getUid() {
        return uidGenerator.getUID();
    }

    @Override
    public String parseUid(Long uid) {
        return uidGenerator.parseUID(uid);

    }
}
