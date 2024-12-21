package com.shunxs.shunxstest.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.shunxs.shunxstest.BaseResponse;
import com.shunxs.shunxstest.common.ErrorCode;
import com.shunxs.shunxstest.common.ResultUtils;
import com.shunxs.shunxstest.domain.Users;
import com.shunxs.shunxstest.domain.request.FollowRequest;
import com.shunxs.shunxstest.domain.request.UserLoginRequest;

import com.shunxs.shunxstest.service.FollowsService;
import com.shunxs.shunxstest.service.UsersService;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UsersService usersService;
    @Autowired
    private FollowsService followsService;

    @PostMapping("/register")
    public BaseResponse<Users> userRegister(@RequestParam String nickname){
        if (nickname == null || nickname.trim().isEmpty()){
            return ResultUtils.error(ErrorCode.NULL_ERROR, "昵称不能为空", null);
        }

        try {
            Users newUser = usersService.userRegister(nickname);
            return ResultUtils.success(newUser);
        } catch (Exception e){
            return ResultUtils.error(ErrorCode.SYSTEM_ERROR, "注册失败", e.getMessage());
        }
    }

    @PostMapping("/login")
    public BaseResponse<Users> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null){
            return null;
        }
        String userAccount = userLoginRequest.getNickname();
        if (StringUtils.isEmpty(userAccount)){
            return null;
        }
        Users user = usersService.userLogin(userAccount, request);
        return ResultUtils.success(user);
    }

    @PostMapping("/avatar")
    public BaseResponse<String> uploadAvatar(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        if (file.isEmpty()) {
            return ResultUtils.error(ErrorCode.SYSTEM_ERROR, "头像上传失败，请重试", null);
        }
        try {
            String avatarUrl = usersService.uploadAvatar(file, request);
            return ResultUtils.success("头像上传成功。URL: " + avatarUrl);
        } catch (IOException e) {
            return ResultUtils.error(ErrorCode.SYSTEM_ERROR, "头像上传失败，请重试", e.getMessage());
        }

    }

    @PostMapping("/follow")
    public BaseResponse<String> addFollow(@RequestBody FollowRequest followRequest, HttpServletRequest request){
        boolean result = followsService.addFollow(followRequest, request);
        if (result){
            return ResultUtils.success("关注成功");
        } else {
            return ResultUtils.error(ErrorCode.SYSTEM_ERROR, "关注失败");
        }
    }

    @GetMapping("followers")
    public BaseResponse<List<Users>> getFollowers(
            HttpServletRequest request,
            @RequestParam(defaultValue = "") String nicknameSearch,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
            ){
        List<Users> followers = followsService.getFollowers(request, nicknameSearch, page, size);
        return ResultUtils.success(followers);
    }


}
