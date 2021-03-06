package delete;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import my.Test2;
//快递员删除订单
/**
 * Servlet implementation class Delete_order
 */
public class Delete_order extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete_order() {
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
		request.setCharacterEncoding("UTF-8");
		String goods_num = request.getParameter("goods_num");
		System.out.println("delete_order"+goods_num);
		boolean flag=Test2.Delete_order(goods_num);
		if(flag)
		{
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
