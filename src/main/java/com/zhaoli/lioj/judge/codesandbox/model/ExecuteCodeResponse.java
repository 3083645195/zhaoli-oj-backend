package com.zhaoli.lioj.judge.codesandbox.model;

import com.zhaoli.lioj.model.dto.questionsubmit.JudgeInfo;

import java.util.List;

/**
 * 返回
 * @author 赵立
 */
public class ExecuteCodeResponse {
    /**
     * 输出用例
     */
    private List<String> outputList;
    /**
     * 接口信息
     */
    private String message;
    /**
     * 判题信息
     */
    private JudgeInfo judgeInfo;
}
