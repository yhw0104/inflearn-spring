package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();   // 회원정보 메모리 저장할 곳

    private static long sequence = 0L;  // sequence -> key값을 생성해주는 변수

    @Override
    public Member save(Member member) {
        member.setId(++sequence);   // sequence 값을 올려 만들 때마다 id 값 생성
        store.put(member.getId(), member);  // store에 id와  이름을 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));  // id 값이 null이여도 optional로 감싸서 반환 -> Optional.ofNullable()
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()     // 람다식.루프
                .filter(member -> member.getName().equals(name))    // 루프를 돌려 name이 같은 member를 찾는다.
                .findAny();     // 파라미터와 같은 name 값을 찾으면 그 값을 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
