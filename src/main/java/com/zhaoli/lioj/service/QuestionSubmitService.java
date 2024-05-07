package com.zhaoli.lioj.service;

import com.zhaoli.lioj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.zhaoli.lioj.model.entity.QuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhaoli.lioj.model.entity.User;

/**
* @author 立
* @description 针对表【question_submit(题目提交)】的数据库操作Service
* @createDate 2024-05-06 19:52:41
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {
    /**
     * 提交题目
     *
     * @param questionSubmitAddRequest 题目提交信息
     * @param loginUser
     * @return 提交记录的 id
     */
    long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);
}
