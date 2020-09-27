package com.liuyao.user.controller;

import com.liuyao.thrift.user.UserInfo;
import com.liuyao.thrift.user.dto.UserDTO;
import com.liuyao.user.reids.RedisClient;
import com.liuyao.user.response.LoginResponse;
import com.liuyao.user.response.Response;
import com.liuyao.user.thrift.ServiceProvider;
import org.apache.thrift.TException;
import org.apache.tomcat.util.buf.HexUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.util.Random;

/**
 * @author <a href="mailto:1209725274@qq.com">刘瑶</a>
 * @date 2020/09/06 15:42
 * Created by Yao Liu.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ServiceProvider serviceProvider;

    @Autowired
    private RedisClient redisClient;

    /**
     * 落地页-用thymeleaf实现
     * @return
     */
    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Response login(@RequestParam("username")String username,@RequestParam("password")String password){
        //1.验证
        UserInfo userInfo=null;
            try{
                userInfo = serviceProvider.getUserService().getUserByName(username);
            }catch (TException e){
                e.printStackTrace();
                return Response.USERNAME_PASSWORD_INVALID;
            }
            if (userInfo==null){
                return Response.USERNAME_PASSWORD_INVALID;
            }
            if (!userInfo.getPassword().equalsIgnoreCase(md5(password))){
                return Response.USERNAME_PASSWORD_INVALID;
            }
        //2.生成token
        String token = genToken();
        //3.缓存用户
        redisClient.set(token,toDTO(userInfo),1800);
        return new LoginResponse(token);

    }


    @RequestMapping(value="/register", method = RequestMethod.POST)
    @ResponseBody
    public Response register(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             @RequestParam(value="mobile", required = false) String mobile,
                             @RequestParam(value="email", required = false) String email,
                             @RequestParam("verifyCode") String verifyCode) {

        if(StringUtils.isBlank(mobile) && StringUtils.isBlank(email)) {
            return Response.MOBILE_OR_EMAIL_REQUIRED;
        }

        if(StringUtils.isNotBlank(mobile)) {
            String redisCode = redisClient.get(mobile);
            if(!verifyCode.equals(redisCode)) {
                return Response.VERIFY_CODE_INVALID;
            }
        }else {
            String redisCode = redisClient.get(email);
            if(!verifyCode.equals(redisCode)) {
                return Response.VERIFY_CODE_INVALID;
            }
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setPassword(md5(password));
        userInfo.setMobile(mobile);
        userInfo.setEmail(email);

        try {
            serviceProvider.getUserService().registerUser(userInfo);
        } catch (TException e) {
            e.printStackTrace();
            return Response.exception(e);
        }

        return Response.SUCCESS;
    }

    @RequestMapping(value = "/sendVerifyCode", method = RequestMethod.POST)
    @ResponseBody
    public Response sendVerifyCode(@RequestParam(value="mobile", required = false) String mobile,
                                   @RequestParam(value="email", required = false) String email) {

        String message = "Verify code is:";
        String code = randomCode("0123456789", 6);
        try {

            boolean result = false;
            if(StringUtils.isNotBlank(mobile)) {
                result = serviceProvider.getMessageService().sendMobileMessage(mobile, message+code);
                redisClient.set(mobile, code);
            } else if(StringUtils.isNotBlank(email)) {
                result = serviceProvider.getMessageService().sendEmailMessage(email, message+code);
                redisClient.set(email, code);
            } else {
                return Response.MOBILE_OR_EMAIL_REQUIRED;
            }

            if(!result) {
                return Response.SEND_VERIFYCODE_FAILED;
            }
        } catch (TException e) {
            e.printStackTrace();
            return Response.exception(e);
        }

        return Response.SUCCESS;

    }

    private UserDTO toDTO(UserInfo userInfo) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userInfo,userDTO);
        return userDTO;
    }
    @RequestMapping(value="/authentication", method = RequestMethod.POST)
    @ResponseBody
    public UserDTO authentication(@RequestHeader("token") String token) {

        return redisClient.get(token);
    }

    private String genToken() {
        return randomCode("0123456789abcdefghigklmnopqrstuvwxyz",32);
    }

    private String randomCode(String s,int size) {
        StringBuilder result = new StringBuilder(size);
        Random random = new Random();
        for (int i=0;i<size;i++){
            int loc = random.nextInt(s.length());
            result.append(s.charAt(loc));
        }
        return result.toString();
    }

    /**
     * 自定义md5
     * @param password
     * @return
     */
    private String md5(String password){
        try {
                MessageDigest md5 = MessageDigest.getInstance("MD5");
                byte[] md5Bytes = md5.digest(password.getBytes("utf-8"));
                return HexUtils.toHexString(md5Bytes);
        } catch (Exception e) {
                e.printStackTrace();
        }
        return null;
    }
}
