package com.zhaoli.lioj.model.dto.question;

import lombok.Data;

/**
 * 判题用例
 *
 * @author 赵立
 */
@Data
public class JudgeCase {
    /**
     * 输入用例
     */
    private String input;
    /**
     * 输出用例
     */
    private String output;
}
