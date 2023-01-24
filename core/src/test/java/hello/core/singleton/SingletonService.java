package hello.core.singleton;

public class SingletonService {

    // static을 이용해 클래스에 올라가게 만듬 -> 하나만 존재하게
    //자신을 내부의 프라이빗으로 하나 가지게 함
    private static final SingletonService instance = new SingletonService();


    // singletonService 객체를 생성하려면 getInstance() 메서드로만 호출이 가능하다. (private 생성자 이므로)
    // 이 메서드를 호출하면 항상 같은 인스턴스(객체)를 반환한다. (static영역에 메모리가 있으므로)
    public static hello.core.singleton.SingletonService getInstance() {
        return instance;
    }

    private SingletonService() {

    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

}
