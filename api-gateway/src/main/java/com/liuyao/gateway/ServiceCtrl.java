package com.liuyao.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author <a href="mailto:1209725274@qq.com">刘瑶</a>
 * @date 2020/09/06 14:26
 * Created by Yao Liu.
 */
@SpringBootApplication
@EnableZuulProxy
public class ServiceCtrl {
    public static void main(String[] args) {
        SpringApplication.run(ServiceCtrl.class,args);
    }
}
