package com.zhaoli.lioj.judge.strategy;

import cn.hutool.json.JSONUtil;
import com.zhaoli.lioj.model.dto.question.JudgeCase;
import com.zhaoli.lioj.model.dto.question.JudgeConfig;
import com.zhaoli.lioj.judge.codesandbox.model.JudgeInfo;
import com.zhaoli.lioj.model.entity.Question;
import com.zhaoli.lioj.model.enums.JudgeInfoMessageEnum;

import java.util.List;

/**
 * 默认判题策略
 *
 * @author 赵立
 */
public class DefaultJudgeStrategy implements JudgeStrategy {
    /**
     * 执行判题
     *
     * @param judgeContext
     * @return
     */
    @Override
    public JudgeInfo doJudge(JudgeContext judgeContext) {
        JudgeInfo judgeInfoResponse = new JudgeInfo();
        //如果代码沙箱执行结果不为成功
        String responseMessage = judgeContext.getJudgeInfo().getMessage();
        if(!JudgeInfoMessageEnum.ACCEPTED.getText().equals(responseMessage)){
            judgeInfoResponse.setMessage(responseMessage);
            return judgeInfoResponse;
        }
        //代码沙箱执行结果成功
        JudgeInfo judgeInfo = judgeContext.getJudgeInfo();
        int memory = judgeInfo.getMemory();//执行消耗内存
        Long time = judgeInfo.getTime();//执行耗费时间
        List<String> inputList = judgeContext.getInputList();
        List<String> outputList = judgeContext.getOutputList();
        Question question = judgeContext.getQuestion();
        List<JudgeCase> judgeCaseList = judgeContext.getJudgeCaseList();
        JudgeInfoMessageEnum judgeInfoMessageEnum = JudgeInfoMessageEnum.ACCEPTED;
        judgeInfoResponse.setMemory(memory);
        judgeInfoResponse.setTime(time);
        //先判断沙箱执行的结果输出数量是否和预期输出数量相等
        if (outputList.size() != inputList.size()) {
            judgeInfoMessageEnum = JudgeInfoMessageEnum.WRONG_ANSWER;
            judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
            return judgeInfoResponse;
        }
        //依次判断每一项输出和预期输出是否相等
        for (int i = 0; i < outputList.size(); i++) {
            JudgeCase judgeCase = judgeCaseList.get(i);
            if (!judgeCase.getOutput().equals(outputList.get(i))) {
                judgeInfoMessageEnum = JudgeInfoMessageEnum.WRONG_ANSWER;
                judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
                return judgeInfoResponse;
            }
        }
        // 判题题目的限制是否符合要求
        String judgeConfigStr = question.getJudgeConfig();
        JudgeConfig judgeConfig = JSONUtil.toBean(judgeConfigStr, JudgeConfig.class);
        int needMemoryLimit = judgeConfig.getMemoryLimit();
        int needTimeLimit = judgeConfig.getTimeLimit();
        if (memory > needMemoryLimit) {
            judgeInfoMessageEnum = JudgeInfoMessageEnum.MEMORY_LIMIT_EXCEEDED;
            judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
            return judgeInfoResponse;
        }
        if (time > needTimeLimit) {
            judgeInfoMessageEnum = JudgeInfoMessageEnum.TIME_LIMIT_EXCEEDED;
            judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
            return judgeInfoResponse;
        }
        judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
        return judgeInfoResponse;
    }
}
