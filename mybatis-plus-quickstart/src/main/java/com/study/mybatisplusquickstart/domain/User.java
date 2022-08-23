package com.study.mybatisplusquickstart.domain;

import lombok.Data;

/**
 * 用户实体类
 * @author elijah
 */
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}