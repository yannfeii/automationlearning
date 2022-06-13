package com.learning.springboot.dao;

import com.learning.springboot.common.MySqlExtensionMapper;
import com.learning.springboot.dto.testcase.QueryHogwartsTestCaseListDto;
import com.learning.springboot.entity.HogwartsTestCase;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HogwartsTestCaseMapper extends MySqlExtensionMapper<HogwartsTestCase> {

    Integer caseCount(@Param("params")QueryHogwartsTestCaseListDto params);
    /*

     */
    List<HogwartsTestCase> caseList(@Param("params")QueryHogwartsTestCaseListDto params,
                                    @Param("pageNum")Integer pageNum,
                                    @Param("pageSize")Integer pageSize);

}