package com.zhaoli.lioj.judge.codesandbox.impl;

import com.zhaoli.lioj.judge.codesandbox.CodeSandbox;
import com.zhaoli.lioj.judge.codesandbox.model.ExecuteCodeRequest;
import com.zhaoli.lioj.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * 远程代码沙箱（实际调用接口的沙箱）
 *
 * @author 赵立
 */
public class RemoteCodeSandbox implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("远程代码沙箱");
        return null;
    }
}
