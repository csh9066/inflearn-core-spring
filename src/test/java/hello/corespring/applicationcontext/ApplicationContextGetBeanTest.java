package hello.corespring.applicationcontext;

import hello.corespring.AppConfig;
import hello.corespring.discount.DiscountPolicy;
import hello.corespring.member.Grade;
import hello.corespring.member.Member;
import hello.corespring.member.MemberService;
import hello.corespring.order.Order;
import hello.corespring.order.OrderService;
import hello.corespring.order.OrderServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ApplicationContextGetBeanTest {

    private final ApplicationContext ac =
            new AnnotationConfigApplicationContext(AppConfig.class);
    
    @Test
    @DisplayName("getBean을 이용한 OrderService 이용")
    void getBeanOrderServiceTest() {
        //given
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        OrderService orderService = ac.getBean("orderService", OrderService.class);
        Member member = new Member(1L, "kimchi", Grade.VIP);
        memberService.join(member);

        // when
        Order order = orderService.createOrder(member.getId(), "baby", 10000);

        //then
        assertThat(order.getMemberId()).isEqualTo(member.getId());
    }

    @Test
    @DisplayName("현재 등록된 beanName 조회")
    void getBeanDefinitionNames() {
        String[] beanNames = ac.getBeanDefinitionNames();

        assertThat(beanNames)
                .contains("memberService", "memberRepository", "discountPolicy", "orderService", "appConfig");
    }

    @Test
    @DisplayName("빈 이름으로 조회")
    void getBeanWithName() {
        Object bean = ac.getBean("orderService");

        assertThat(bean).isInstanceOf(OrderService.class);
    }

    @Test
    @DisplayName("빈 타입으로 조회")
    void getBeanWithType() {
        OrderService bean = ac.getBean(OrderService.class);

        assertThat(bean).isInstanceOf(OrderService.class);
    }

    @Test
    @DisplayName("빈 구체타입으로 조회")
    void getBeanWithImplementType() {
        OrderServiceImpl bean = ac.getBean(OrderServiceImpl.class);

        assertThat(bean).isInstanceOf(OrderServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름이 없을 떄")
    void getBeanWhenNonExist() {
        assertThatThrownBy(() -> ac.getBean("Xxx", MemberService.class))
                .isInstanceOf(NoSuchBeanDefinitionException.class);
    }
}
