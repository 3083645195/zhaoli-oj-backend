package com.zhaoli.lioj.judge.codesandbox;

import com.zhaoli.lioj.judge.codesandbox.model.ExecuteCodeRequest;
import com.zhaoli.lioj.judge.codesandbox.model.ExecuteCodeResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 代码沙箱代理（打印请求信息和响应信息）
 *
 * @author 赵立
 */
@Slf4j
public class CodeSandboxProxy implements CodeSandbox {
    private final CodeSandbox codeSandbox;

    public CodeSandboxProxy(CodeSandbox codeSandbox) {
        this.codeSandbox = codeSandbox;
    }

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        log.info("code sandbox request:" + executeCodeRequest);
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
        log.info("code sandbox response:" + executeCodeResponse);
        return executeCodeResponse;
    }
}
