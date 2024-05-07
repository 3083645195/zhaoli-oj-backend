package com.zhaoli.lioj.model.dto.question;

import lombok.Data;

/**
 * 判题配置
 *
 * @author 赵立
 */
@Data
public class JudgeConfig {
    /**
     * 时间限制(ms)
     */
    private int timeLimit;
    /**
     * 内存限制(KB)
     */
    private int memoryLimit;
    /**
     * 堆栈限制(KB)
     */
    private int stackLimit;
}
