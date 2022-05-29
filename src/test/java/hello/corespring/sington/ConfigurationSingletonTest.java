package hello.corespring.sington;

import hello.corespring.AppConfig;
import hello.corespring.member.MemberServiceImpl;
import hello.corespring.member.MemoryMemberRepository;
import hello.corespring.order.OrderServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationSingletonTest {

    @Test
    @DisplayName("MemberRepository이 정말 싱글톤으로 생성되었는지 확인")
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean(MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean(OrderServiceImpl.class);
        MemoryMemberRepository memberRepository = ac.getBean(MemoryMemberRepository.class);

        assertThat(memberService.getMemberRepository())
                .isSameAs(orderService.getMemberRepository())
                .isSameAs(memberRepository);
    }

    @Test
    void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        AppConfig bean = ac.getBean(AppConfig.class);

        // 로그를 찍어보면 AppConfig@CGLIB 처럼 되어있을 거임
        System.out.println(bean);
    }
}
