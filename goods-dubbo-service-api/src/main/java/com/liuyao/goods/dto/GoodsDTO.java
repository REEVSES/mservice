package com.liuyao.goods.dto;

import com.liuyao.thrift.user.dto.MerchantDTO;

import java.io.Serializable;

/**
 * @author <a href="mailto:1209725274@qq.com">刘瑶</a>
 * @date 2020/09/08 09:21
 * Created by Yao Liu.
 */
public class GoodsDTO implements Serializable {
    private int id;
    private String title;
    private String description;
    private MerchantDTO merchant;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MerchantDTO getMerchant() {
        return merchant;
    }

    public void setMerchant(MerchantDTO merchant) {
        this.merchant = merchant;
    }
}
