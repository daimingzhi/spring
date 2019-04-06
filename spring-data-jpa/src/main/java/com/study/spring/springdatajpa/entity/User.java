package com.study.spring.springdatajpa.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author dmz
 * @date Create in 9:14 2019/4/2
 */
@Entity
@Table(name = "USER")
@Data
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer useId;
    @Column
    private String useName;
    @Column
    private String useSex;
    @Column
    private Integer useAge;
    @Column(unique = true)
    private String useIdNo;
    @Column(unique = true)
    private String usePhoneNum;
    @Column(unique = true)
    private String useEmail;
    @Column
    private LocalDateTime createTime;
    @Column
    private LocalDateTime modifyTime;
    @Column
    private String useState;
}

