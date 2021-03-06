package request;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegistServlet
 */
@WebServlet("/regist")
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    

	/**
	 * 1.서버의 리소스 요청을 위해서 사용
	 * 2.등록화면 요청
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher reqd = 
				request.getRequestDispatcher("registForm");
		reqd.forward(request, response);
		
		response.sendRedirect("registForm");
		
	}

	/**
	 * 1.사용자 데이터를 서버로 전송하기 위해서 사용
	 * 2.폼에서 발생한 데이터를 처리하기 위해 사용
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//3.<form>에서 넘어온 파라미터 추출
		//(1)name이 유일한 경우 일반적으로 1개의 String변수로 받는다
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//(2)동일한name이 두개이상 전달될 경우 String[]변수로 받는다 
		String[] hobbies = request.getParameterValues("hobby");	
		
		//4.출력
		//(1) 기본 : sysout 출력
		System.out.println("등록된 사용자 이름 : " + username);
		System.out.println("등록된 사용자 비밀번호 : " + password);
		
		if(hobbies!= null) {for (String hobby : hobbies) {
			System.out.println("등록된 취미 : " + hobby);
			}
		}else {
			System.out.println("등록된 취가 존재하지 않습니다");
		}
		System.out.println("==========================");
		
		
		//(2) 브라우저 출력 : PrintWriter 객체 출력
		
		PrintWriter out = response.getWriter();
		out.println("<HTML><BODY>");
		out.println("등록된 사용자 이름 : " + username + "<br/>");
		out.println("등록된 사용자 비밀번호 : " + password + "<br/>");
		
		//<input>중에서 type 이 checkbox인 경우는
		//아무것도 선택하지 않은 상황에 대한 고려가 항상 있어야 한다.
		if(hobbies != null) {for (String hobby : hobbies) {
			out.println("등록된 취미 : " + hobby + "<br/>");
			}
		}else {
			out.println("등록된 취미가 없습니다");
		}
		
		out.close();
		out.println("</HTML></BODY>");
	}

}
