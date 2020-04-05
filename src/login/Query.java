package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Query
 */
public class Query extends HttpServlet {
	private static final long serialVersionUID = 1L;
       //测试的servlet
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Query() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		//response.sendRedirect("homepage.html");
		//response.sendRedirect("index.jsp");
		String mm = "ppppp";
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out .write(mm);//或者out .println(mm);
		out.print("<script type='text/javascript'>alert('审核成功')</script>");
		out.close();
		String username = (String)request.getSession().getAttribute("login_id");
		System.out.println(username);
		response.setContentType("text/html; charset=UTF-8"); //转码
	    PrintWriter outl = response.getWriter();
	    outl.flush();
	    outl.println("<script>");
	    outl.println("alert('此用户名已存在，请重新输入！');");
	    outl.println("history.back();");
	    outl.println("</script>");
//	    return mapping.findForward("");
	    response.sendRedirect("index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mm = "ppppp";
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out .write(mm);//或者out .println(mm);
		out.close();

		response.sendRedirect("index.jsp");
	}

}
