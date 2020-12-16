package cn.jianwoo.blog.dao.base.mapper;

import cn.jianwoo.blog.entity.FileUpload;
import cn.jianwoo.blog.entity.example.fileUploadExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface fileUploadMapper {
    long countByExample(fileUploadExample example);

    int deleteByExample(fileUploadExample example);

    int deleteByPrimaryKey(Long oid);

    int insert(FileUpload record);

    int insertSelective(FileUpload record);

    List<FileUpload> selectByExample(fileUploadExample example);

    FileUpload selectByPrimaryKey(Long oid);

    int updateByExampleSelective(@Param("record") FileUpload record, @Param("example") fileUploadExample example);

    int updateByExample(@Param("record") FileUpload record, @Param("example") fileUploadExample example);

    int updateByPrimaryKeySelective(FileUpload record);

    int updateByPrimaryKey(FileUpload record);
}