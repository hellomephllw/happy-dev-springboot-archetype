package com.demo.app.dao.user;

import com.happy.dto.PagingDto;
import com.demo.app.entity.user.User;
import com.happy.base.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends BaseMapper<User> {

    PagingDto<User> paging(@Param("startNo") int startNo, @Param("pageSize") int pageSize, @Param("age") Integer age);

    void updateProps(@Param("name") String name);

}