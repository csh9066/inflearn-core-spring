package hello.corespring.web;

import hello.corespring.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final MyLogger myLogger;

    @GetMapping("/log-demo")
    public String logDemo(HttpServletRequest request) {
        System.out.println("myLogger = " + myLogger.getClass());

        String url = request.getRequestURL().toString();
        myLogger.setRequestURL(url);

        myLogger.log("controller test");
        logDemoService.logic("testId");

        return "ok";

    }
}
