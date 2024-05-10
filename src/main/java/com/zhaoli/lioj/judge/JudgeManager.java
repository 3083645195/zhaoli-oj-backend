package com.zhaoli.lioj.judge;

import com.zhaoli.lioj.judge.strategy.DefaultJudgeStrategy;
import com.zhaoli.lioj.judge.strategy.JavaLanguageJudgeStrategy;
import com.zhaoli.lioj.judge.strategy.JudgeContext;
import com.zhaoli.lioj.judge.strategy.JudgeStrategy;
import com.zhaoli.lioj.model.dto.questionsubmit.JudgeInfo;
import com.zhaoli.lioj.model.entity.QuestionSubmit;
import com.zhaoli.lioj.model.enums.QuestionSubmintLanguageEnum;
import org.springframework.stereotype.Service;

/**
 * 判题管理(简化调用)
 *
 * @author 赵立
 */
@Service
public class JudgeManager {
    /**
     * 执行判题
     *
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext){
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if(QuestionSubmintLanguageEnum.JAVA.getValue().equals(language)){
            judgeStrategy = new JavaLanguageJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }
}
