package com.shunxs.shunxstest.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.shunxs.shunxstest.domain.Users;
import com.shunxs.shunxstest.domain.request.FollowRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
* @author 15PRO
* @description 针对表【users(存储用户基本信息的表)】的数据库操作Service
* @createDate 2024-12-21 14:07:05
*/
public interface UsersService extends IService<Users> {

    Users userRegister(String nickname);

    Users userLogin(String nickname, HttpServletRequest request);

    String uploadAvatar(MultipartFile file, HttpServletRequest request) throws IOException;


}
