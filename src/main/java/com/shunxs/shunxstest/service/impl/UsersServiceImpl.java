package com.shunxs.shunxstest.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shunxs.shunxstest.domain.Users;
import com.shunxs.shunxstest.mapper.UsersMapper;
import com.shunxs.shunxstest.service.UsersService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.shunxs.shunxstest.constant.UserConstant.USER_LOGIN_STATE;
/**
* @author 15PRO
* @description 针对表【users(存储用户基本信息的表)】的数据库操作Service实现
* @createDate 2024-12-21 14:07:05
*/
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users>
    implements UsersService {

    private final UsersMapper usersMapper;
    private static final String UPLOAD_DIR = "uploads/avatars/";


    public UsersServiceImpl(UsersMapper usersMapper) {
        this.usersMapper = usersMapper;
    }

    @Override
    public Users userRegister(String nickname) {
        Users user = new Users();
        user.setNickname(nickname);
        this.save(user);
        return user;
    }

    @Override
    public Users userLogin(String nickname, HttpServletRequest request) {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("nickname", nickname);
        Users user = usersMapper.selectOne(queryWrapper);
        if (user == null){
            log.error("user login failed, user not exist");
            return null;
        }
        request.getSession().setAttribute(USER_LOGIN_STATE, user);
        return user;
    }

    @Override
    public String uploadAvatar(MultipartFile file, HttpServletRequest request) throws IOException {

        // 从会话中获取当前登录用户
        HttpSession session = request.getSession();
        Users currentUser = (Users) session.getAttribute(USER_LOGIN_STATE);
        if (currentUser == null) {
            throw new IllegalStateException("用户未登录");
        }
        String fileName = file.getOriginalFilename();
        String uniqueFileName = System.currentTimeMillis() + "_" + fileName;
        Path uploadPath = Paths.get(UPLOAD_DIR).toAbsolutePath().normalize();
        Path filePath = uploadPath.resolve(uniqueFileName);

        Files.createDirectories(uploadPath);
        Files.copy(file.getInputStream(), filePath);

        String avatarUrl = "/avatars/" + uniqueFileName;

        // 更新用户的头像URL
        currentUser.setAvatarUrl(avatarUrl);
        usersMapper.updateById(currentUser);

        return avatarUrl;
    }
}




