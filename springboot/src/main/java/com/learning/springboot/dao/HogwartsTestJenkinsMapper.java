package com.learning.springboot.dao;

import com.learning.springboot.common.MySqlExtensionMapper;
import com.learning.springboot.dto.jenkins.QueryHogwartsTestJenkinsListDto;
import com.learning.springboot.entity.HogwartsTestJenkins;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HogwartsTestJenkinsMapper extends MySqlExtensionMapper<HogwartsTestJenkins> {

    /**
     * 统计总数
     * @param params
     * @return
     */
    Integer count(@Param("params") QueryHogwartsTestJenkinsListDto params);

    /**
     * 列表分页查询
     * @param params
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<HogwartsTestJenkins> list(@Param("params") QueryHogwartsTestJenkinsListDto params,
                                   @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);
}
