package hello.corespring;

import hello.corespring.discount.DiscountPolicy;
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

    // @Bean memberService -> new MemoryMemberRepository(); ?
    // @Bean orderService -> new MemoryMemberRepository(); ?

    /*
      빈 안에서 memberRepository()를 호출하면 new 연산자를 여러번 하는거 같은데 왜 한번만 호출할까?
       @Configuration의 어노테이션을 사용하면 cglib 이라는 라이브러리를 이용해 바이트 코드를 조작해
       싱글톤을 보장할 수 있게끔 해줌 @Configuration 가 없으면 싱글톤 보장 X
     */
    @Bean
    public MemberService memberService() {
        System.out.println("AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        System.out.println("AppConfig.orderService");
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy()
        );
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }
}
