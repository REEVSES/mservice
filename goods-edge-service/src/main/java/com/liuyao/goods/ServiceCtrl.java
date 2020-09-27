package com.liuyao.goods;

import com.liuyao.goods.filter.GoodsFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:1209725274@qq.com">刘瑶</a>
 * @date 2020/09/06 14:26
 * Created by Yao Liu.
 */
@SpringBootApplication
public class ServiceCtrl {
    public static void main(String[] args) {
        SpringApplication.run(ServiceCtrl.class,args);
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        GoodsFilter goodsFilter = new GoodsFilter();
        filterRegistrationBean.setFilter(goodsFilter);

        List<String> urlPatterns = new ArrayList<>();
        urlPatterns.add("/*");
        filterRegistrationBean.setUrlPatterns(urlPatterns);
        return filterRegistrationBean;
    }
}
