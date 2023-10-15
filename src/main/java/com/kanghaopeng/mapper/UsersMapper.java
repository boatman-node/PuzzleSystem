package com.kanghaopeng.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kanghaopeng.entity.Users;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersMapper extends BaseMapper<Users> {
    @Select("select * from Users where username = #{username}")
    Users OneUser(@Param("username")String username);
}
