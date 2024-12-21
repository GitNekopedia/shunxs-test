-- 用户表
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '用户ID，自增主键',
    nickname VARCHAR(50) NOT NULL COMMENT '用户昵称，不能为空',
    avatar_url VARCHAR(255) COMMENT '用户头像URL',
    is_deleted TINYINT(1) NOT NULL DEFAULT 0 COMMENT '假删除标志，0表示未删除，1表示已删除',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '用户创建时间',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '用户信息更新时间'
) COMMENT '存储用户基本信息的表';

-- 关注关系表
CREATE TABLE follows (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '关注关系ID，自增主键',
    follower_id BIGINT NOT NULL COMMENT '关注者的用户ID',
    followed_id BIGINT NOT NULL COMMENT '被关注者的用户ID',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '关注关系创建时间',
    INDEX idx_follower (follower_id) COMMENT '关注者索引，用于加速查询用户的关注列表',
    INDEX idx_followed (followed_id) COMMENT '被关注者索引，用于加速查询用户的粉丝列表',
    UNIQUE KEY unique_follow (follower_id, followed_id) COMMENT '确保同一用户不会重复关注同一个人'
) COMMENT '存储用户关注关系的表';
