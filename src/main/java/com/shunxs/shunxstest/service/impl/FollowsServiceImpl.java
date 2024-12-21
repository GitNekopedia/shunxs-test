package com.shunxs.shunxstest.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shunxs.shunxstest.domain.Follows;
import com.shunxs.shunxstest.domain.Users;
import com.shunxs.shunxstest.domain.request.FollowRequest;
import com.shunxs.shunxstest.mapper.FollowsMapper;
import com.shunxs.shunxstest.mapper.UsersMapper;
import com.shunxs.shunxstest.service.FollowsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.shunxs.shunxstest.constant.UserConstant.USER_LOGIN_STATE;

/**
* @author 15PRO
* @description 针对表【follows(存储用户关注关系的表)】的数据库操作Service实现
* @createDate 2024-12-21 14:04:15
*/
@Service
public class FollowsServiceImpl extends ServiceImpl<FollowsMapper, Follows>
    implements FollowsService {

    @Autowired
    UsersMapper usersMapper;
    @Autowired
    FollowsMapper followsMapper;

    @Override
    public boolean addFollow(FollowRequest followRequest, HttpServletRequest request) {
        // 从会话中获取当前登录用户
        HttpSession session = request.getSession();
        Users currentUser = (Users) session.getAttribute(USER_LOGIN_STATE);
        if (currentUser == null) {
            throw new IllegalStateException("用户未登录");
        }

        // 获取关注用户的ID
        Long followedId = followRequest.getFollowedId();

        // 检查关注用户是否存在
        Users followUser = usersMapper.selectById(followedId);

        if (followUser == null) {
            throw new IllegalArgumentException("关注用户不存在");
        }

        // 检查当前用户是否已经关注了该用户
        QueryWrapper<Follows> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("follower_id", currentUser.getId())
                .eq("followed_id", followedId);
        if (followsMapper.selectCount(queryWrapper) > 0) {
            return false; // 已经关注过了
        }

        // 创建关注记录
        Follows follow = new Follows();
        follow.setFollowerId(currentUser.getId());
        follow.setFollowedId(followedId);

        return followsMapper.insert(follow) > 0;


    }

    @Override
    public List<Users> getFollowers(HttpServletRequest request, String nicknameSearch, int page, int size) {
        // 从会话中获取当前登录用户
        HttpSession session = request.getSession();
        Users currentUser = (Users) session.getAttribute(USER_LOGIN_STATE);
        if (currentUser == null) {
            throw new IllegalStateException("用户未登录");
        }

        // 创建分页对象
        Page<Follows> followsPage = new Page<>(page, size);

        // 查询当前用户的所有关注者的ID
        QueryWrapper<Follows> followsQueryWrapper = new QueryWrapper<>();
        followsQueryWrapper.eq("followed_id", currentUser.getId());

        Page<Follows> followsResult = followsMapper.selectPage(followsPage, followsQueryWrapper);

        // 提取关注者ID
        List<Long> followerIds = followsResult.getRecords().stream()
                .map(Follows::getFollowerId)
                .collect(Collectors.toList());

        // 如果没有关注者，返回空列表
        if (followerIds.isEmpty()) {
            return new ArrayList<>();
        }

        // 查询关注者的用户信息
        QueryWrapper<Users> usersQueryWrapper = new QueryWrapper<>();
        usersQueryWrapper.in("id", followerIds);
        List<Users> followers = usersMapper.selectList(usersQueryWrapper);

        // 如果提供了昵称搜索，在内存中过滤结果
        if (nicknameSearch != null && !nicknameSearch.trim().isEmpty()) {
            String searchTerm = nicknameSearch.trim().toLowerCase();
            followers = followers.stream()
                    .filter(user -> user.getNickname().toLowerCase().contains(searchTerm))
                    .collect(Collectors.toList());
        }

        return followers;
    }
}




