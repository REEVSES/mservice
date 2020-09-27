package com.liuyao.thrift.user.dto;

/**
 * @author <a href="mailto:1209725274@qq.com">刘瑶</a>
 * @date 2020/09/08 09:47
 * Created by Yao Liu.
 */
public class MerchantDTO extends UserDTO {
    private String intro;
    private int stars;

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
}
