package com.zhaoli.lioj.judge.codesandbox.impl;

import com.zhaoli.lioj.judge.codesandbox.CodeSandbox;
import com.zhaoli.lioj.judge.codesandbox.model.ExecuteCodeRequest;
import com.zhaoli.lioj.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * 第三方代码沙箱（调用网上现成的代码沙箱）
 *
 * @author 赵立
 */
public class ThirdPartyCodeSandbox implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("第三方代码沙箱");
        return null;
    }
}
