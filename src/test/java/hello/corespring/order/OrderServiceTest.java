package hello.corespring.order;

import hello.corespring.AppConfig;
import hello.corespring.member.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrderServiceTest {
    private OrderService orderService;
    private MemberService memberService;

    @BeforeEach
    void setUp() {
        AppConfig appConfig = new AppConfig();
        orderService = appConfig.orderService();
        memberService = appConfig.memberService();
    }

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