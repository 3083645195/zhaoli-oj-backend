package com.zhaoli.lioj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhaoli.lioj.common.ErrorCode;
import com.zhaoli.lioj.exception.BusinessException;
import com.zhaoli.lioj.mapper.QuestionSubmitMapper;
import com.zhaoli.lioj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.zhaoli.lioj.model.entity.Question;
import com.zhaoli.lioj.model.entity.QuestionSubmit;
import com.zhaoli.lioj.model.entity.User;
import com.zhaoli.lioj.model.enums.QuestionSubmintLanguageEnum;
import com.zhaoli.lioj.model.enums.QuestionSubmitStatusEnum;
import com.zhaoli.lioj.service.QuestionService;
import com.zhaoli.lioj.service.QuestionSubmitService;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author 立
 * @description 针对表【question_submit(题目提交)】的数据库操作Service实现
 * @createDate 2024-05-06 19:52:41
 */
@Service
public class QuestionSubmitServiceImpl extends ServiceImpl<QuestionSubmitMapper, QuestionSubmit>
        implements QuestionSubmitService {

    @Resource
    private QuestionService questionService;

    /**
     * 提交题目
     *
     * @param questionSubmitAddRequest
     * @param loginUser
     * @return
     */
    @Override
    public long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser) {
        //校验编程语言是否合法
        String language = questionSubmitAddRequest.getLanguage();
        QuestionSubmintLanguageEnum languageEnum = QuestionSubmintLanguageEnum.getEnumByValue(language);
        if (languageEnum == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "编程语言错误");
        }
        // 判断实体是否存在，根据类别获取实体
        long questionId = questionSubmitAddRequest.getQuestionId();
        //查询题目实体是否存在
        Question question = questionService.getById(questionId);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 是否已提交题目
        long userId = loginUser.getId();
        // 每个用户串行提交题目
        QuestionSubmit questionSubmit = new QuestionSubmit();
        questionSubmit.setUserId(userId);
        questionSubmit.setQuestionId(questionId);
        questionSubmit.setCode(questionSubmitAddRequest.getCode());
        questionSubmit.setLanguage(language);
        //设置判题初始状态
        questionSubmit.setStatus(QuestionSubmitStatusEnum.WAITING.getValue());//等待中
        // todo 判题信息
        questionSubmit.setJudgeInfo("{}");
        boolean result = this.save(questionSubmit);
        if (!result) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "数据插入失败");
        }
        return questionSubmit.getId();
    }
}




