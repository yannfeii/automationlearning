package com.learning.springboot.service;

import com.learning.springboot.common.PageTableRequest;
import com.learning.springboot.common.PageTableResponse;
import com.learning.springboot.dto.ResultDto;
import com.learning.springboot.dto.testcase.QueryHogwartsTestCaseListDto;
import com.learning.springboot.entity.HogwartsTestCase;

public interface HogwartsTestCaseService {

    ResultDto<PageTableResponse<HogwartsTestCase>> caselist(PageTableRequest<QueryHogwartsTestCaseListDto> pageTabRes);

    ResultDto<HogwartsTestCase> saveDB(HogwartsTestCase testCase);

    ResultDto<HogwartsTestCase> updateCase(HogwartsTestCase testCase);

    ResultDto getCaseById(Integer caseId);

    ResultDto deleteCaseById(Integer caseId);
}
