package cn.jianwoo.blog.dao.base.mapper;

import cn.jianwoo.blog.entity.FileUpload;
import cn.jianwoo.blog.entity.example.FileUploadExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FileUploadMapper {
    long countByExample(FileUploadExample example);

    int deleteByExample(FileUploadExample example);

    int deleteByPrimaryKey(Long oid);

    int insert(FileUpload record);

    int insertSelective(FileUpload record);

    List<FileUpload> selectByExample(FileUploadExample example);

    FileUpload selectByPrimaryKey(Long oid);

    int updateByExampleSelective(@Param("record") FileUpload record, @Param("example") FileUploadExample example);

    int updateByExample(@Param("record") FileUpload record, @Param("example") FileUploadExample example);

    int updateByPrimaryKeySelective(FileUpload record);

    int updateByPrimaryKey(FileUpload record);
}