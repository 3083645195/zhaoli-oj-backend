package com.zhaoli.lioj.judge.codesandbox.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 接收
 * @author 赵立
 */
@Data
/**
 * 简化 Java 类的构建器（Builder）模式的使用
 */
@Builder
/**
 * 用于自动生成一个无参构造方法
 */
@NoArgsConstructor
/**
 * 用于自动生成一个包含所有参数的构造方法
 */
@AllArgsConstructor
public class ExecuteCodeRequest {
    /**
     * 输入用例
     */
    private List<String> inputList;
    /**
     * 代码
     */
    private String code;
    /**
     * 编程语言
     */
    private String language;
}
