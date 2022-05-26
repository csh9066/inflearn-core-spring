package hello.corespring.order;

import hello.corespring.member.Grade;
import hello.corespring.member.Member;
import hello.corespring.member.MemberService;
import hello.corespring.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrderServiceTest {

    private final OrderService orderService = new OrderServiceImpl();
    private final MemberService memberService = new MemberServiceImpl();

    @Test
    void createOrderWhenMemberGradeVip() {
        // given
        Long memberid = 1L;
        Member member = new Member(memberid, "memberA", Grade.VIP);
        memberService.join(member);
        // when
        Order order = orderService.createOrder(memberid, "itemA", 10000);
        // then
        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

    @Test
    void createOrderWhenMemberGradeBasic() {
        // given
        Long memberid = 1L;
        Member member = new Member(memberid, "memberA", Grade.BASIC);
        memberService.join(member);
        // when
        Order order = orderService.createOrder(memberid, "itemA", 10000);
        // then
        assertThat(order.getDiscountPrice()).isEqualTo(0);
    }
}