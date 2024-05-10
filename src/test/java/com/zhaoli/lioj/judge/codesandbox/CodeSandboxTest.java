package com.zhaoli.lioj.judge.codesandbox;

import com.zhaoli.lioj.judge.codesandbox.impl.ExampleCodeSandbox;
import com.zhaoli.lioj.judge.codesandbox.model.ExecuteCodeRequest;
import com.zhaoli.lioj.judge.codesandbox.model.ExecuteCodeResponse;
import com.zhaoli.lioj.model.enums.QuestionSubmintLanguageEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author 赵立
 */
@SpringBootTest
class CodeSandboxTest {
    @Value("${codesandbox.type:example}")
    private String type;

    @Test
    void executeCode() {
        CodeSandbox codeSandbox = new ExampleCodeSandbox();
        String code = "int main() { printf(\"Hello, World!\"); return 0;}";
        String language = QuestionSubmintLanguageEnum.JAVA.getValue();
        List<String> inputList = Arrays.asList("1 2", "2 3");
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputList(inputList)
                .build();
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
        Assertions.assertNotNull(executeCodeResponse);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String type = scanner.next();
            System.out.println(type);
            CodeSandbox codeSandbox = CodeSandboxFactory.newInstance(type);
            String code = "int main() { printf(\"Hello, World!\"); return 0;}";
            String language = QuestionSubmintLanguageEnum.JAVA.getValue();
            List<String> inputList = Arrays.asList("1 2", "2 3");
            ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                    .code(code)
                    .language(language)
                    .inputList(inputList)
                    .build();
            ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
            Assertions.assertNotNull(executeCodeResponse);
        }
    }

    @Test
    void executeCodeValue() {
        CodeSandbox codeSandbox = CodeSandboxFactory.newInstance(type);
        String code = "int main() { printf(\"Hello, World!\"); return 0;}";
        String language = QuestionSubmintLanguageEnum.JAVA.getValue();
        List<String> inputList = Arrays.asList("1 2", "2 3");
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputList(inputList)
                .build();
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
        Assertions.assertNotNull(executeCodeResponse);
    }

    @Test
    void executeCodeProxy() {
        CodeSandbox codeSandbox = CodeSandboxFactory.newInstance(type);
        codeSandbox = new CodeSandboxProxy(codeSandbox);
        String code = "int main() { printf(\"Hello, World!\"); return 0;}";
        String language = QuestionSubmintLanguageEnum.JAVA.getValue();
        List<String> inputList = Arrays.asList("1 2", "2 3");
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputList(inputList)
                .build();
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
        Assertions.assertNotNull(executeCodeResponse);
    }
}