package cn.itheima.mapper;

import cn.itheima.pojo.TbBanner;
import cn.itheima.pojo.TbBannerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbBannerMapper {
    int countByExample(TbBannerExample example);

    int deleteByExample(TbBannerExample example);

    int deleteByPrimaryKey(Integer rid);

    int insert(TbBanner record);

    int insertSelective(TbBanner record);

    List<TbBanner> selectByExample(TbBannerExample example);

    TbBanner selectByPrimaryKey(Integer rid);

    int updateByExampleSelective(@Param("record") TbBanner record, @Param("example") TbBannerExample example);

    int updateByExample(@Param("record") TbBanner record, @Param("example") TbBannerExample example);

    int updateByPrimaryKeySelective(TbBanner record);

    int updateByPrimaryKey(TbBanner record);
}