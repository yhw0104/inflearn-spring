package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient /*implements InitializingBean, DisposableBean*/ {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작 시 호출하는 메서드
    public void connect(){
        System.out.println("connect: " + url);
    }

    //연결된 상태
    public void call(String message){
        System.out.println("call: " + url + " message = " + message);
    }

    //서비스 종료시 호출하는 메서드 (안전하게 서비스 연결이 끊김)
    public void disconnect() {
        System.out.println("close: " + url);
    }


    @PostConstruct  //어노테이션을 활용한 의존 주입이후 초기화
    //의존 주입 다 끝나고 실행됨(@Bean에 붙는 initMethod)
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메세지");
    }

    @PreDestroy     //어노테이션을 활용한 종료시 초기화
    //서비스 종료시 실행(@Bean에 붙는 destroyMethod)
    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }

//    //의존 주입 다 끝나고 실행됨(InitializingBean 인터페이스 오버라이드)
//    @Override
//    public void destroy() throws Exception {
//        disconnect();
//    }
//
//    //서비스 종료시 실행(DisposableBean 인터페이스 오버라이드)
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        connect();
//        call("초기화 연결 메시지");
//    }
}