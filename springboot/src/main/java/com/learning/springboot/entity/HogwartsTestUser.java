package com.learning.springboot.entity;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

/**
 * 表名：hogwarts_test_user
*/
@Table(name = "hogwarts_test_user")
@Data
public class HogwartsTestUser extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_name")
    private String userName;

    private String password;

    private String email;

    /**
     * job job
     */
    @Column(name = "auto_create_case_job_name")
    private String autoCreateCaseJobName;

    /**
     * job job
     */
    @Column(name = "start_test_job_name")
    private String startTestJobName;

    /**
     * Jenkins
     */
    @Column(name = "default_jenkins_id")
    private Integer defaultJenkinsId;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Transient
    private String projectName;

}