<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.*,java.util.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>

<%
    // 클라이언트로부터 전달받은 사용자 이름과 비밀번호
    String username = request.getParameter("username");
    String password = request.getParameter("password");

    // 실제 사용자 인증 로직은 여기에 구현되어야 함
    // 아래는 간단한 예시로 'kim'이라는 사용자 이름과 '1234'라는 비밀번호를 허용하는 것으로 가정
    if ("kim".equals(username) && "1234".equals(password)) {
        // 로그인 성공
        out.println("success");
    } else {
        // 로그인 실패
        out.println("failure");
    }
%>
