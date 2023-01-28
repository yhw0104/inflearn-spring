package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component  // 컴포넌트는 해당 빈을 생성해주는 역할 (의존관계 자동주입 x) -> AppConfig파일에서 @Configuration역할
public class MemberServiceImpl implements MemberService{

    //의존관계가 인터페이스 뿐만 아니라 구현까지 모두 의존하는 문제점이 있음
    // MemberServiceImpl 에서 repository를 의존할 때 MemberRepository인터페이스(추상화)와 그 구현체인 MemoryMemberRepository(구체화)도 의존하고 있다.
    // --> DIP 위반
    private final MemberRepository memberRepository;

    // 생성자를 통해 MemberRepository 지정
    @Autowired //생성자로 의존성 주입한 곳에 @Autowired 어노테이션을 붙여서 자동으로 의존관계를 주입해준다. 마치 ac.getBean(MemberRepository.class) 처럼 // -> AppConfig파일에서 생성자 역할(의존관계 주입)
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);      // 다형성에 의해 MemberRepository의 인터페이스가 아닌 MemoryMemberRepository의 오버라이드 된 save함수가 실행
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
