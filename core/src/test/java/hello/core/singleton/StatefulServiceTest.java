package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA: A사용자가 10000원 주문
        statefulService1.order("userA", 10000);
        //ThreadB: B사용자가 20000원 주문
        statefulService2.order("userB", 20000);

        //ThreadA: A사용자가 주문 금액 조회
        int price = statefulService1.getPrice();
        System.out.println("price = " + price);
        // 실행결과 값 : price = 20000
        // 결국 같은 statusService 인스턴스(싱글톤)에서 order 메서드 실행됨 -> 공유되는 필드(price)가 변경이 되므로 20000원이 나옴.

        assertThat(statefulService1.getPrice()).isEqualTo(20000);   // 잘못된 테스트 실행
    }


    static class TestConfig {

        @Bean
        public StatefulService statusService() {
            return new StatefulService();
        }
    }
}