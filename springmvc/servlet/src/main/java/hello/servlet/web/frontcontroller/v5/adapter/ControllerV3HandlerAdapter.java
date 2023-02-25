package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        //controllerV3 인터페이스의 구현체가 나오면 참, 아니면 거짓으로 리턴
        return (handler instanceof ControllerV3);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        // supports 메서드에서 이미 controllerV3이면 참으로 나오기 때문에 handler는 Object타입에서 ControllerV3타입으로 형변환 해줘야됨
        ControllerV3 controller = (ControllerV3) handler;

        // handler에서 온 결과 값을 ModelView로 변환해서 반환한다.
        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);    //5. MemberFormControllerV3를 보여준다.

        return mv;
    }

    private static Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }

}
