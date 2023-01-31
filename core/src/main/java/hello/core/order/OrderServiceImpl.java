package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor    //( final이 붙은 값을 파라미터로 받는 생성자를 만듬) --> 단일 생성자이면 @Autowired가 필요 없으므로 자동으로 di 해줌
public class OrderServiceImpl implements  OrderService{

    //회원 레파지토리
    private final MemberRepository memberRepository;
    //고정 할인 정책
    private final DiscountPolicy discountPolicy;

//    @Autowired
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        //memberRepository와 discountPolicy가 의존하는 구현체에가 들어간다. memberRepository --> memoryMemberRepository , discountPolicy --> RateDiscountPolicy
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
