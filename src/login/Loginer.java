package login;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import my.Test2;

/**
 * Servlet implementation class Loginer
 */
public class Loginer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Loginer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//response.sendRedirect("homepage.html");
		request.setCharacterEncoding("UTF-8");
		String login_id = request.getParameter("login_id");
		String login_password = request.getParameter("login_password");
		
		//获取session
		HttpSession session=request.getSession();
		//session.setAttribute(name, value);
		session.setAttribute("login_id",login_id);
		
		LoginInformation Loginer = new LoginInformation(login_id,login_password);
//		System.out.println(Loginer.login_id+Loginer.login_password);
		
		boolean istrue = Test2.query_Id_password(Loginer.login_id, Loginer.login_password);
		System.out.println("现在的状态"+istrue);
		if(istrue)
		{
			System.out.println("现在进入");
			//response.sendRedirect("homepage.html");
			// 返回应答数据
			JSONObject jresp = new JSONObject();
			jresp.put("error", 0); // 错误码,0表示成功
			jresp.put("reason", "OK"); // 错误原因描述, 如果没有错误则提示OK
			response.setContentType("text/html;charset=UTF-8");
			response.setContentType("text/plain");
			Writer writer = response.getWriter();
			writer.write( jresp.toString(2));
			writer.close();
		}
	}

}
