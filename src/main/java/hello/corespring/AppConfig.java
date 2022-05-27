package hello.corespring;

import hello.corespring.discount.DiscountPolicy;
import hello.corespring.discount.FixDiscountPolicy;
import hello.corespring.discount.RateDiscountPolicy;
import hello.corespring.member.MemberRepository;
import hello.corespring.member.MemberService;
import hello.corespring.member.MemberServiceImpl;
import hello.corespring.member.MemoryMemberRepository;
import hello.corespring.order.OrderService;
import hello.corespring.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(
                MemoryMemberRepository.getInstance(),
                discountPolicy()
        );
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }

    @Bean
    public MemberRepository memberRepository() {
        return MemoryMemberRepository.getInstance();
    }
}
