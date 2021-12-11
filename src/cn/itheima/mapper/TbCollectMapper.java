package cn.itheima.mapper;

import cn.itheima.pojo.TbCollect;
import cn.itheima.pojo.TbCollectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbCollectMapper {
    int countByExample(TbCollectExample example);

    int deleteByExample(TbCollectExample example);

    int deleteByPrimaryKey(Integer rid);

    int insert(TbCollect record);

    int insertSelective(TbCollect record);

    List<TbCollect> selectByExample(TbCollectExample example);

    TbCollect selectByPrimaryKey(Integer rid);

    int updateByExampleSelective(@Param("record") TbCollect record, @Param("example") TbCollectExample example);

    int updateByExample(@Param("record") TbCollect record, @Param("example") TbCollectExample example);

    int updateByPrimaryKeySelective(TbCollect record);

    int updateByPrimaryKey(TbCollect record);
}