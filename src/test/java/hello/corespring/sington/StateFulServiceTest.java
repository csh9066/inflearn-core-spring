package hello.corespring.sington;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;

class StateFulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StateFulService stateFulService1 = ac.getBean(StateFulService.class);
        StateFulService stateFulService2 = ac.getBean(StateFulService.class);

        //ThreadA A사용자 10000원 주문
        stateFulService1.order("userA", 10000);

        // ThreadB B사용자 20000원 주문
        stateFulService2.order("userB", 20000);

        // ThreadA A사용자 주문 금액 조회
        int price = stateFulService1.getPrice();

        assertThat(price).isNotEqualTo(10000);

        Long babo = 1L;
        Long bibo = 1L;

        assertThat(babo).isSameAs(bibo);
    }

    @Configuration
    static class TestConfig {

        @Bean
        public StateFulService stateFulService() {
            return new StateFulService();
        }
    }

}