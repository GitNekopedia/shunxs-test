package com.shunxs.shunxstest.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shunxs.shunxstest.domain.Follows;
import com.shunxs.shunxstest.domain.Users;
import com.shunxs.shunxstest.domain.request.FollowRequest;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;


/**
* @author 15PRO
* @description 针对表【follows(存储用户关注关系的表)】的数据库操作Service
* @createDate 2024-12-21 14:04:15
*/
public interface FollowsService extends IService<Follows> {
    boolean addFollow(FollowRequest followRequest, HttpServletRequest request);

    List<Users> getFollowers(HttpServletRequest request, String nicknameSearch, int page, int size);
}
