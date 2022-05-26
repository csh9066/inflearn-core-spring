package hello.corespring.order;

import hello.corespring.discount.DiscountPolicy;
import hello.corespring.discount.FixDiscountPolicy;
import hello.corespring.member.Member;
import hello.corespring.member.MemberRepository;
import hello.corespring.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = MemoryMemberRepository.getInstance();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);

        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
