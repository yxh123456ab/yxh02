package cn.itheima.mapper;

import cn.itheima.pojo.TbRecommended;
import cn.itheima.pojo.TbRecommendedExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbRecommendedMapper {
    int countByExample(TbRecommendedExample example);

    int deleteByExample(TbRecommendedExample example);

    int deleteByPrimaryKey(Integer rid);

    int insert(TbRecommended record);

    int insertSelective(TbRecommended record);

    List<TbRecommended> selectByExample(TbRecommendedExample example);

    TbRecommended selectByPrimaryKey(Integer rid);

    int updateByExampleSelective(@Param("record") TbRecommended record, @Param("example") TbRecommendedExample example);

    int updateByExample(@Param("record") TbRecommended record, @Param("example") TbRecommendedExample example);

    int updateByPrimaryKeySelective(TbRecommended record);

    int updateByPrimaryKey(TbRecommended record);
}