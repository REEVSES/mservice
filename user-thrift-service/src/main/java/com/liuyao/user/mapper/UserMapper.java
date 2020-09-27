package com.liuyao.user.mapper;

import com.liuyao.thrift.user.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author <a href="mailto:1209725274@qq.com">刘瑶</a>
 * @date 2020/09/06 14:09
 * Created by Yao Liu.
 */
@Mapper
public interface UserMapper {
    @Select("select id,username,password,real_name as realName,mobile,email from pe_user where id=#{id}")
    UserInfo getUserById(@Param("id")int id);
    @Select("select id,username,password,real_name as realName,mobile,email from pe_user where username=#{username}")
    UserInfo getUserByName(@Param("username")String username);
    @Insert("insert into pe_user(username,password,real_name,mobile,email) values (#{user.username},#{user.password},#{user.realname},#{user.mobile},#{user.email})")
    void registerUser(@Param("user") UserInfo userInfo);

    /**
     * 跨表查询
     * @param id
     * @return
     */
    @Select("select u.id,u.username,u.password,u.real_name as realName," +
            "u.mobile,u.email,t.intro,t.stars from pe_user u," +
            "pe_merchant t where u.id=#{id} " +
            "and u.id=t.user_id")
    UserInfo getMerchantById(@Param("id")int id);
}
