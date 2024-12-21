package com.shunxs.shunxstest.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shunxs.shunxstest.domain.Follows;
import com.shunxs.shunxstest.mapper.FollowsMapper;
import com.shunxs.shunxstest.service.FollowsService;
import org.springframework.stereotype.Service;

/**
* @author 15PRO
* @description 针对表【follows(存储用户关注关系的表)】的数据库操作Service实现
* @createDate 2024-12-21 14:04:15
*/
@Service
public class FollowsServiceImpl extends ServiceImpl<FollowsMapper, Follows>
    implements FollowsService {

}




