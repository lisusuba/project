package delivery;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import my.Test2;
/**
 * Servlet implementation class Updateissend
 */
public class Updateissend extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Updateissend() {
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
		//从session中取的id
		String deliveryman_id = (String)request.getSession().getAttribute("login_id");
		//取值结束
		request.setCharacterEncoding("UTF-8");
		String goods_num = request.getParameter("goods_num");
		System.out.println("Updateissend"+goods_num);
		
		boolean flag=Test2.update_issended(goods_num,deliveryman_id);
		
		// 返回应答数据
		JSONObject jresp = new JSONObject();
//		jresp.put("error", 0); // 错误码,0表示成功
//		jresp.put("reason", "OK"); // 错误原因描述, 如果没有错误则提示OK
		jresp.put("flag1", flag);
		response.setContentType("text/html;charset=UTF-8");
		response.setContentType("text/plain");
		Writer writer = response.getWriter();
		writer.write( jresp.toString(2));
		writer.close();
	}

}
