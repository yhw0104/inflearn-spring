package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        //1. 조회: 호출 할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        //2. 조회: 호출 할 때 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        //3. 참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // memberService1 != memberService2
        assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void SingletonServiceTest() {

        //private으로 생성자를 막아두었다. 컴파일 오류가 발생
        //new SingletonService(); --> 오류


        // 1. 조회: 호출할 때 마다 같은 객체 생성
        SingletonService singletonService1 = SingletonService.getInstance();
        // 2. 조회: 호출할 때 마다 같은 객체 생성
        SingletonService singletonService2 = SingletonService.getInstance();

        // 참조값이 같은지 확
        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        // 같은 객체이므로 두 객체의 메모리도 같아야 함 (singletonService1 == singletonService2) / equals가 아님
        assertThat(singletonService1).isSameAs(singletonService2);
        //same == 인스턴스가 같은지
        //equals == 대상 내용이 같은지
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void SpringContainer() {
//        AppConfig appConfig = new AppConfig();
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        //1. 조회: 호출 할 때 마다 객체를 생성
        MemberService memberService1 = ac.getBean(MemberService.class);

        //2. 조회: 호출 할 때 마다 객체를 생성
        MemberService memberService2 = ac.getBean(MemberService.class);

        //3. 참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // memberService1 != memberService2
        assertThat(memberService1).isSameAs(memberService2);
    }
}
