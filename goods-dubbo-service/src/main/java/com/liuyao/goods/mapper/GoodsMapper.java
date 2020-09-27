package com.liuyao.goods.mapper;

import com.liuyao.goods.dto.GoodsDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author <a href="mailto:1209725274@qq.com">刘瑶</a>
 * @date 2020/09/08 11:19
 * Created by Yao Liu.
 */
@Mapper
public interface GoodsMapper {

    @Select("select * from pe_goods")
    List<GoodsDTO> listGoods();

    @Select("select user_id from pr_user_goods where goods_id=#{goodsId}")
    Integer getGoodsMarchent(@Param("goodsId") int goodsId);
}
