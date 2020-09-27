package com.liuyao.goods.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.liuyao.goods.dto.GoodsDTO;
import com.liuyao.goods.mapper.GoodsMapper;
import com.liuyao.thrift.user.UserInfo;
import com.liuyao.thrift.user.dto.MerchantDTO;
import org.apache.thrift.TException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author <a href="mailto:1209725274@qq.com">刘瑶</a>
 * @date 2020/09/08 10:05
 * Created by Yao Liu.
 */
@Service
public class GoodsServiceImpl implements IGoodsService{

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    ServiceProvider serviceProvider;

    @Override
    public List<GoodsDTO> goodsList() {
        List<GoodsDTO> goodsDTOS = goodsMapper.listGoods();
        if(goodsDTOS!=null) {
            for(GoodsDTO goodsDTO : goodsDTOS) {
                Integer merchantId = goodsMapper.getGoodsMarchent(goodsDTO.getId());
                if(merchantId!=null) {
                    try {
                        System.out.println(serviceProvider.toString());
                        UserInfo userInfo = serviceProvider.getUserService().getMerchantById(merchantId);
                        goodsDTO.setMerchant(trans2Merchant(userInfo));
                    } catch (TException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            }
        }
        return goodsDTOS;
    }
    private MerchantDTO trans2Merchant(UserInfo userInfo) {
        MerchantDTO merchantDTO = new MerchantDTO();
        BeanUtils.copyProperties(userInfo, merchantDTO);
        return merchantDTO;
    }
}
