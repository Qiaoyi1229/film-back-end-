package com.example.film.dto.req;

import java.util.Date;

/**
 * @author 陈乐
 * @version 1.0
 * @date 2020/3/11 10:38
 */
public class UserReq {

    private Integer id;

    private String name;

    private String password;

    private String mail;

    private String phone;

    private Integer age;

    private String headIamge;

    private Integer role;

    private Date createTime;

    private String code;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getHeadIamge() {
        return headIamge;
    }

    public void setHeadIamge(String headIamge) {
        this.headIamge = headIamge;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
