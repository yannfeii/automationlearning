package com.learning.springboot.service;

import com.learning.springboot.common.PageTableRequest;
import com.learning.springboot.common.PageTableResponse;
import com.learning.springboot.dto.RequestInfoDto;
import com.learning.springboot.dto.ResultDto;
import com.learning.springboot.dto.TokenDto;
import com.learning.springboot.dto.testTask.QueryHogwartsTestTaskListDto;
import com.learning.springboot.dto.testTask.TestTaskDto;
import com.learning.springboot.entity.HogwartsTestTask;

import java.io.IOException;

public interface HogwartsTestTaskService {
    /**
     *  新增测试任务信息
     * @param testTaskDto
     * @return
     */
    ResultDto<HogwartsTestTask> saveTask(TestTaskDto testTaskDto, Integer taskType);

    /**
     *  删除测试任务信息
     * @param taskId
     * @param createTaskId
     * @return
     */
    ResultDto<HogwartsTestTask> deleteTask(Integer taskId,Integer createTaskId);

    /**
     *  修改测试任务信息
     * @param hogwartsTestTask
     * @return
     */
    ResultDto<HogwartsTestTask> updateTask(HogwartsTestTask hogwartsTestTask);

    /**
     *  根据id查询
     * @param taskId
     * @return
     */
    ResultDto<HogwartsTestTask> getTaskById(Integer taskId,Integer createTaskId);

    /**
     *  查询测试任务信息列表
     * @param pageTableRequest
     * @return
     */
    ResultDto<PageTableResponse<HogwartsTestTask>> taskList(PageTableRequest<QueryHogwartsTestTaskListDto> pageTableRequest);

    /**
     *  开始执行测试任务信息
     * @param hogwartsTestTask
     * @return
     */
    ResultDto startTask(TokenDto tokenDto, RequestInfoDto requestInfoDto, HogwartsTestTask hogwartsTestTask) throws IOException;

    /**
     *  修改测试任务状态信息
     * @param hogwartsTestTask
     * @return
     */
    ResultDto<HogwartsTestTask> updateTaskStatus(HogwartsTestTask hogwartsTestTask);

    ResultDto getAllureReport(HogwartsTestTask hogwartsTestTask);
}
