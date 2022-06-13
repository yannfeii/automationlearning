package com.learning.springboot.entity;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

/**
 * 表名：hogwarts_test_case
*/
@Data
@Table(name = "hogwarts_test_case")
public class HogwartsTestCase extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "case_name")
    private String caseName;

    private String remark;

    @Column(name = "del_flag")
    private Integer delFlag;

    @Column(name = "create_user_id")
    private Integer createUserId;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "case_data")
    private String caseData;

}