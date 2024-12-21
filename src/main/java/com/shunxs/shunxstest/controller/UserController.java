package com.shunxs.shunxstest.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.shunxs.shunxstest.BaseResponse;
import com.shunxs.shunxstest.common.ErrorCode;
import com.shunxs.shunxstest.common.ResultUtils;
import com.shunxs.shunxstest.domain.Users;
import com.shunxs.shunxstest.domain.request.UserLoginRequest;
import com.shunxs.shunxstest.mapper.UsersMapper;
import com.shunxs.shunxstest.service.UsersService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UsersService usersService;
    @Autowired
    private UsersMapper usersMapper;

    @PostMapping("/register")
    public ResponseEntity<?> userRegister(@RequestParam String nickname){
        if (nickname == null || nickname.trim().isEmpty()){
            return ResponseEntity.badRequest().body("Nickname cannot be empty");
        }

        try {
            Users newUser = usersService.userRegister(nickname);
            return ResponseEntity.ok(newUser);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
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


}
