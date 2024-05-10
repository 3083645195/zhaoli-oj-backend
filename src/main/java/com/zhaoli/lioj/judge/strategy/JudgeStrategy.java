package com.zhaoli.lioj.judge.strategy;

import com.zhaoli.lioj.model.dto.questionsubmit.JudgeInfo;

/**
 * 判题策略
 *
 * @author 赵立
 */
public interface JudgeStrategy {
    /**
     * 执行判题
     *
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext);
}
