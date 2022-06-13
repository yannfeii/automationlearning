package com.learning.springboot.service.impl;

import com.learning.springboot.common.Constants;
import com.learning.springboot.common.PageTableRequest;
import com.learning.springboot.common.PageTableResponse;
import com.learning.springboot.dao.HogwartsTestCaseMapper;
import com.learning.springboot.dto.ResultDto;
import com.learning.springboot.dto.testcase.QueryHogwartsTestCaseListDto;
import com.learning.springboot.entity.HogwartsTestCase;
import com.learning.springboot.service.HogwartsTestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class HogwartsTestCaseServiceImpl implements HogwartsTestCaseService {

    @Autowired
    HogwartsTestCaseMapper hogwartsTestCaseMapper;


    @Override
    public ResultDto<PageTableResponse<HogwartsTestCase>> caselist(PageTableRequest<QueryHogwartsTestCaseListDto> pageTableRequest) {

        QueryHogwartsTestCaseListDto params = pageTableRequest.getParams();
        Integer pageNum = pageTableRequest.getPageNum();
        Integer pageSize = pageTableRequest.getPageSize();
        //查总数
        int caseCount = hogwartsTestCaseMapper.caseCount(params);
        //分页数据
        List<HogwartsTestCase> hogwartsTestCaseList =  hogwartsTestCaseMapper
                .caseList(params,(pageNum-1)*pageSize,pageSize);

        PageTableResponse<HogwartsTestCase> pageTabRes = new PageTableResponse<>();
        pageTabRes.setRecordsTotal(caseCount);
        pageTabRes.setData(hogwartsTestCaseList);

        return ResultDto.resultSuccess("成功",pageTabRes);
    }

    @Override
    public ResultDto<HogwartsTestCase> saveDB(HogwartsTestCase testCase) {
        testCase.setCreateTime(new Date());
        testCase.setUpdateTime(new Date());
        testCase.setDelFlag(Constants.DEL_FLAG_ZERO);
        //insertUseGeneratedKeys insert后返回主键值
        hogwartsTestCaseMapper.insertUseGeneratedKeys(testCase);
        return ResultDto.resultSuccess("数据插入成功",testCase);
    }

    @Override
    public ResultDto<HogwartsTestCase> updateCase(HogwartsTestCase testCase) {

        HogwartsTestCase query =  new HogwartsTestCase();
        query.setCreateUserId(testCase.getCreateUserId());
        query.setId(testCase.getId());
        query.setDelFlag(testCase.getDelFlag());

        HogwartsTestCase caseResult = hogwartsTestCaseMapper.selectOne(query);

        if(Objects.isNull(caseResult))
            return ResultDto.resultFail("未查到测试用例数据");

        testCase.setUpdateTime(new Date());
        //根据主键查询，如果字段为空则不对数据处理
        hogwartsTestCaseMapper.updateByPrimaryKeySelective(testCase);

        return ResultDto.resultSuccess("数据更新成功",testCase);
    }

    @Override
    public ResultDto<HogwartsTestCase> getCaseById(Integer caseId) {

        HogwartsTestCase query = new HogwartsTestCase();
        query.setId(caseId);
        query.setDelFlag(Constants.DEL_FLAG_ZERO);

        HogwartsTestCase caseResult = hogwartsTestCaseMapper.selectOne(query);

        if(Objects.isNull(caseResult)){
            return ResultDto.resultFail("未查到测试用例数据");
        }

        return ResultDto.resultSuccess("查询成功",caseResult);
    }


    @Override
    public ResultDto deleteCaseById(Integer caseId) {

        ResultDto<HogwartsTestCase> caseResultDto = getCaseById(caseId);

        if(caseResultDto.getResultCode() ==1){
            return caseResultDto;
        }

        HogwartsTestCase hogwartsTestCase = caseResultDto.getData();
        hogwartsTestCase.setDelFlag(Constants.DEL_FLAG_ONE);
        hogwartsTestCase.setUpdateTime(new Date());

        return ResultDto.resultSuccess("删除成功");
    }
}
