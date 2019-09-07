package com.uchain.jwtlogindemo.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class User implements Serializable {
    private Integer id;

    private String stuId;

    private String trueName;

    private String password;

    private Integer role;

    private String userDesc;

    private Integer userPicId;

    private Integer unqualifyTimes;

    private Integer groupId;

    private Integer qualifyTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId == null ? null : stuId.trim();
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName == null ? null : trueName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getUserDesc() {
        return userDesc;
    }

    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc == null ? null : userDesc.trim();
    }

    public Integer getUserPicId() {
        return userPicId;
    }

    public void setUserPicId(Integer userPicId) {
        this.userPicId = userPicId;
    }

    public Integer getUnqualifyTimes() {
        return unqualifyTimes;
    }

    public void setUnqualifyTimes(Integer unqualifyTimes) {
        this.unqualifyTimes = unqualifyTimes;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getQualifyTime() {
        return qualifyTime;
    }

    public void setQualifyTime(Integer qualifyTime) {
        this.qualifyTime = qualifyTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", stuId=").append(stuId);
        sb.append(", trueName=").append(trueName);
        sb.append(", password=").append(password);
        sb.append(", role=").append(role);
        sb.append(", userDesc=").append(userDesc);
        sb.append(", userPicId=").append(userPicId);
        sb.append(", unqualifyTimes=").append(unqualifyTimes);
        sb.append(", groupId=").append(groupId);
        sb.append(", qualifyTime=").append(qualifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}