package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);    // Optional -> null값을 그대로 반환하지 않고 optional로 감싸서 반환
    List<Member> findAll();
}
