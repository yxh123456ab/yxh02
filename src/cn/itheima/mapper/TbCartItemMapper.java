package cn.itheima.mapper;

import cn.itheima.pojo.TbCartItem;
import cn.itheima.pojo.TbCartItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbCartItemMapper {
    int countByExample(TbCartItemExample example);

    int deleteByExample(TbCartItemExample example);

    int deleteByPrimaryKey(Integer rid);

    int insert(TbCartItem record);

    int insertSelective(TbCartItem record);

    List<TbCartItem> selectByExample(TbCartItemExample example);

    TbCartItem selectByPrimaryKey(Integer rid);

    int updateByExampleSelective(@Param("record") TbCartItem record, @Param("example") TbCartItemExample example);

    int updateByExample(@Param("record") TbCartItem record, @Param("example") TbCartItemExample example);

    int updateByPrimaryKeySelective(TbCartItem record);

    int updateByPrimaryKey(TbCartItem record);
}