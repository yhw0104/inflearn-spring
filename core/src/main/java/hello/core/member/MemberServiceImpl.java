package hello.core.member;

public class MemberServiceImpl implements MemberService{

    //의존관계가 인터페이스 뿐만 아니라 구현까지 모두 의존하는 문제점이 있음
    // MemberServiceImpl 에서 repository를 의존할 때 MemberRepository인터페이스(추상화)와 그 구현체인 MemoryMemberRepository(구체화)도 의존하고 있다.
    // --> DIP 위반

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);      // 다형성에 의해 MemberRepository의 인터페이스가 아닌 MemoryMemberRepository의 오버라이드 된 save함수가 실행
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
