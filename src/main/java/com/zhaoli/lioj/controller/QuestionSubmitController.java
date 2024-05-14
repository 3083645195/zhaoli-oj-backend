package com.zhaoli.lioj.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhaoli.lioj.common.BaseResponse;
import com.zhaoli.lioj.common.ErrorCode;
import com.zhaoli.lioj.common.ResultUtils;
import com.zhaoli.lioj.exception.BusinessException;
import com.zhaoli.lioj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.zhaoli.lioj.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.zhaoli.lioj.model.entity.QuestionSubmit;
import com.zhaoli.lioj.model.entity.User;
import com.zhaoli.lioj.model.vo.QuestionSubmitVO;
import com.zhaoli.lioj.service.QuestionSubmitService;
import com.zhaoli.lioj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 题目提交接口
 *
 * @author zhaoli
 * @from 
 */
@RestController
//@RequestMapping("/question_submit")
@Slf4j
@Deprecated
public class QuestionSubmitController {
//
//    @Resource
//    private QuestionSubmitService questionSubmitService;
//
//    @Resource
//    private UserService userService;
//
//    /**
//     * 提交题目
//     *
//     * @param questionSubmitAddRequest
//     * @param request
//     * @return 提交记录的 id
//     */
//    @PostMapping("/")
//    public BaseResponse<Long> doQuestionSubmit(@RequestBody QuestionSubmitAddRequest questionSubmitAddRequest,
//            HttpServletRequest request) {
//        if (questionSubmitAddRequest == null || questionSubmitAddRequest.getQuestionId() <= 0) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR);
//        }
//        // 登录才能提交题目
//        final User loginUser = userService.getLoginUser(request);
//        long questionId = questionSubmitAddRequest.getQuestionId();
//        long id = questionSubmitService.doQuestionSubmit(questionSubmitAddRequest, loginUser);
//        return ResultUtils.success(id);
//    }
//    /**
//     * 分页获取题目列表（除管理员外，普通用户只能看到非提交代码等公开信息）
//     *
//     * @param questionSubmitQueryRequest
//     * @return
//     */
//    @PostMapping("/list/page")
//    public BaseResponse<Page<QuestionSubmitVO>> listQuestionSubmitByPage(@RequestBody QuestionSubmitQueryRequest questionSubmitQueryRequest,
//                                                                         HttpServletRequest request) {
//        long current = questionSubmitQueryRequest.getCurrent();
//        long size = questionSubmitQueryRequest.getPageSize();
//        //从数据库中查询原始题目的提交分页
//        Page<QuestionSubmit> questionPage = questionSubmitService.page(new Page<>(current, size),
//                questionSubmitService.getQueryWrapper(questionSubmitQueryRequest));
//        final User loginUser = userService.getLoginUser(request);
//        //返回脱敏信息
//        return ResultUtils.success(questionSubmitService.getQuestionSubmitVOPage(questionPage,loginUser));
//    }
}
