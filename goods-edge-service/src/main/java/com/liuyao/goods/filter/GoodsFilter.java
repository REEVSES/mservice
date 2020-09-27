package com.liuyao.goods.filter;

import com.liuyao.thrift.user.dto.UserDTO;
import com.liuyao.user.sso.LoginFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author <a href="mailto:1209725274@qq.com">刘瑶</a>
 * @date 2020/09/08 14:28
 * Created by Yao Liu.
 */
public class GoodsFilter extends LoginFilter {
    @Override
    protected void login(HttpServletRequest request, HttpServletResponse response, UserDTO userDTO) {
        request.setAttribute("user", userDTO);
    }
}
