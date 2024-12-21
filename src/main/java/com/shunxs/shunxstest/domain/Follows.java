package com.shunxs.shunxstest.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 存储用户关注关系的表
 * @TableName follows
 */
@TableName(value ="follows")
public class Follows implements Serializable {
    /**
     * 关注关系ID，自增主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 关注者的用户ID
     */
    private Long followerId;

    /**
     * 被关注者的用户ID
     */
    private Long followedId;

    /**
     * 关注关系创建时间
     */
    private Date createdAt;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 关注关系ID，自增主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 关注关系ID，自增主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 关注者的用户ID
     */
    public Long getFollowerId() {
        return followerId;
    }

    /**
     * 关注者的用户ID
     */
    public void setFollowerId(Long followerId) {
        this.followerId = followerId;
    }

    /**
     * 被关注者的用户ID
     */
    public Long getFollowedId() {
        return followedId;
    }

    /**
     * 被关注者的用户ID
     */
    public void setFollowedId(Long followedId) {
        this.followedId = followedId;
    }

    /**
     * 关注关系创建时间
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * 关注关系创建时间
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Follows other = (Follows) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getFollowerId() == null ? other.getFollowerId() == null : this.getFollowerId().equals(other.getFollowerId()))
            && (this.getFollowedId() == null ? other.getFollowedId() == null : this.getFollowedId().equals(other.getFollowedId()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getFollowerId() == null) ? 0 : getFollowerId().hashCode());
        result = prime * result + ((getFollowedId() == null) ? 0 : getFollowedId().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", followerId=").append(followerId);
        sb.append(", followedId=").append(followedId);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}