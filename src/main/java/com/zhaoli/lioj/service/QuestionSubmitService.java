package com.zhaoli.lioj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhaoli.lioj.model.dto.question.QuestionQueryRequest;
import com.zhaoli.lioj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.zhaoli.lioj.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.zhaoli.lioj.model.entity.Question;
import com.zhaoli.lioj.model.entity.QuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhaoli.lioj.model.entity.User;
import com.zhaoli.lioj.model.vo.QuestionSubmitVO;
import com.zhaoli.lioj.model.vo.QuestionVO;

import javax.servlet.http.HttpServletRequest;

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
    /**
     * 获取查询条件
     *
     * @param questionSubmitQueryRequest
     * @return
     */
    QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest);


    /**
     * 获取题目封装
     *
     * @param questionSubmit
     * @param loginUser
     * @return
     */
    QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, User loginUser);


    /**
     * 分页获取题目封装
     *
     * @param questionSubmit
     * @param loginUser
     * @return
     */
    Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmit, User loginUser);
}
