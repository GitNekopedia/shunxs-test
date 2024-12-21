package com.shunxs.shunxstest.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 存储用户基本信息的表
 * @TableName users
 */
@TableName(value ="users")
public class Users implements Serializable {
    /**
     * 用户ID，自增主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户昵称，不能为空
     */
    private String nickname;

    /**
     * 用户头像URL
     */
    private String avatarUrl;

    /**
     * 假删除标志，0表示未删除，1表示已删除
     */
    private Integer isDeleted;

    /**
     * 用户创建时间
     */
    private Date createdAt;

    /**
     * 用户信息更新时间
     */
    private Date updatedAt;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID，自增主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 用户ID，自增主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 用户昵称，不能为空
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 用户昵称，不能为空
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 用户头像URL
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * 用户头像URL
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    /**
     * 假删除标志，0表示未删除，1表示已删除
     */
    public Integer getIsDeleted() {
        return isDeleted;
    }

    /**
     * 假删除标志，0表示未删除，1表示已删除
     */
    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * 用户创建时间
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * 用户创建时间
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * 用户信息更新时间
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * 用户信息更新时间
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
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
        Users other = (Users) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getNickname() == null ? other.getNickname() == null : this.getNickname().equals(other.getNickname()))
            && (this.getAvatarUrl() == null ? other.getAvatarUrl() == null : this.getAvatarUrl().equals(other.getAvatarUrl()))
            && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getNickname() == null) ? 0 : getNickname().hashCode());
        result = prime * result + ((getAvatarUrl() == null) ? 0 : getAvatarUrl().hashCode());
        result = prime * result + ((getIsDeleted() == null) ? 0 : getIsDeleted().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", nickname=").append(nickname);
        sb.append(", avatarUrl=").append(avatarUrl);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}