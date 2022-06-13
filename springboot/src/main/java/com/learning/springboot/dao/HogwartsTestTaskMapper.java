package com.learning.springboot.dao;

import com.learning.springboot.common.MySqlExtensionMapper;
import com.learning.springboot.dto.testTask.QueryHogwartsTestTaskListDto;
import com.learning.springboot.entity.HogwartsTestCase;
import com.learning.springboot.entity.HogwartsTestTask;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface HogwartsTestTaskMapper extends MySqlExtensionMapper<HogwartsTestTask> {

    Integer taskCount(@Param("params") QueryHogwartsTestTaskListDto params);
    /*

     */
    List<HogwartsTestTask> taskList(@Param("params")QueryHogwartsTestTaskListDto params,
                                    @Param("pageNum")Integer pageNum,
                                    @Param("pageSize")Integer pageSize);
}
