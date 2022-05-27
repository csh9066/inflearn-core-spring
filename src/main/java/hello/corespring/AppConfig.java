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

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(
                MemoryMemberRepository.getInstance(),
                discountPolicy()
        );
    }

    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }

    public MemberRepository memberRepository() {
        return MemoryMemberRepository.getInstance();
    }
}
