package com.liuyao.user.service;

import com.liuyao.thrift.user.UserInfo;
import com.liuyao.thrift.user.UserService;
import com.liuyao.user.mapper.UserMapper;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:1209725274@qq.com">刘瑶</a>
 * @date 2020/09/06 10:53
 * Created by Yao Liu.
 */
@Service
public class UserServiceImpl implements UserService.Iface {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserInfo getUserById(int id) throws TException {
        return userMapper.getUserById(id);
    }

    @Override
    public UserInfo getMerchantById(int id) throws TException {
        return userMapper.getMerchantById(id);
    }

    @Override
    public UserInfo getUserByName(String username) throws TException {
        return userMapper.getUserByName(username);
    }

    @Override
    public void registerUser(UserInfo userInfo) throws TException {
        userMapper.registerUser(userInfo);
    }
}
