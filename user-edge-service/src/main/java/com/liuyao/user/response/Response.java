package com.liuyao.user.response;

import java.io.Serializable;

/**
 * @author <a href="mailto:1209725274@qq.com">刘瑶</a>
 * @date 2020/09/06 16:13
 * Created by Yao Liu.
 */
public class Response implements Serializable {

    public static final Response USERNAME_PASSWORD_INVALID = new Response("10001", "The user name does not exist or password verification failed ");
    public static final Response MOBILE_OR_EMAIL_REQUIRED = new Response("10002", "Mobile or email are required");
    public static final Response VERIFY_CODE_INVALID = new Response("10003", "Verify code invalid");
    public static final Response SEND_VERIFYCODE_FAILED = new Response("10004", "Send code invalid");

    public static final Response SUCCESS = new Response();
    private String code;
    private String message;

    //默认构造不需要继承后补充
    public Response(){
        this.code="00000";
        this.message="success";
    }

    //构造方法
    public Response(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static Response exception(Exception e) {
        return new Response("90000", e.getMessage());
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
