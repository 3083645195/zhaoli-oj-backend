package com.zhaoli.lioj.model.dto.questionsubmit;

import lombok.Data;

/**
 * 判题信息
 *
 * @author 赵立
 */
@Data
public class JudgeInfo {
    /**
     * 程序执行信息
     */
    private String message;
    /**
     * 消耗内存(KB)
     */
    private Long memory;
    /**
     * 消耗时间(ms)
     */
    private Long time;
}
