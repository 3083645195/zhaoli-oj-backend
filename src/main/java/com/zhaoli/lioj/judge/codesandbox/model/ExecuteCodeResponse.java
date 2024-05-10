package com.zhaoli.lioj.judge.codesandbox.model;

import com.zhaoli.lioj.model.dto.questionsubmit.JudgeInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 返回
 *
 * @author 赵立
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
     * 执行状态
     */
    private Integer status;
    /**
     * 判题信息
     */
    private JudgeInfo judgeInfo;
}
