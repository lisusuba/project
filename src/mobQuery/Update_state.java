package mobQuery;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import my.MobQuery;

/**
 * Servlet implementation class Update_state
 */
public class Update_state extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update_state() {
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
		int flag;
		//String id = (String)request.getSession().getAttribute("login_id");
		String goods_num = request.getParameter("goods_num");
		flag = MobQuery.upstate(goods_num);
		System.out.println("Update_state"+flag+","+goods_num);
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
