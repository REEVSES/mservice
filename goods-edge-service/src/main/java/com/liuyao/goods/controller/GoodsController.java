package com.liuyao.goods.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.liuyao.goods.dto.GoodsDTO;
import com.liuyao.goods.service.IGoodsService;
import com.liuyao.thrift.user.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author <a href="mailto:1209725274@qq.com">刘瑶</a>
 * @date 2020/09/08 14:29
 * Created by Yao Liu.
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Reference
    private IGoodsService iGoodsService;

    @RequestMapping(value = "/goodsList", method = RequestMethod.GET)
    @ResponseBody
    public List<GoodsDTO> courseList(HttpServletRequest request) {
        UserDTO user = (UserDTO)request.getAttribute("user");
        System.out.println(user.toString());
        return iGoodsService.goodsList();
    }
}
