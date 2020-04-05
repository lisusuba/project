package query;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import my.Test2;

/**
 * Servlet implementation class Affrim_orders_success
 */
public class Affrim_orders_success extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Affrim_orders_success() {
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
		// 因为请求字段里的中文数据，所以要显式设置一下字符编码
		request.setCharacterEncoding("UTF-8");
		
		// 从请求里提取参数字段的值
		String goods_num = request.getParameter("goods_num");
		System.out.println("affirm"+goods_num);
		try {
			Test2.affrim(goods_num);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
