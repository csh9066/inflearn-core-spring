package hello.corespring.web;

import hello.corespring.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LogDemoService {

    private final MyLogger myLogger;

    public void logic(String testId) {
        myLogger.log("service id = " + testId);
    }
}
