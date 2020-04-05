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
 * Servlet implementation class Insert_delivery_orders
 */
public class Insert_delivery_orders extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Insert_delivery_orders() {
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
		System.out.println("Insert_delivery_orders"+deliveryman_id);
		//取值结束
		request.setCharacterEncoding("UTF-8");
		String send_time = request.getParameter("datetime");
		String sender_id = request.getParameter("sender_id");
		String sender_name = request.getParameter("sender_name");
		String sender_phone = request.getParameter("sender_phone");
		String sender_address = request.getParameter("sender_address");
		String recipient_name = request.getParameter("recipient_name");
		String recipient_phone = request.getParameter("recipient_phone");
		String recipient_address = request.getParameter("recipient_address");
		String goods_description = request.getParameter("goods_description");
		String goods_num = request.getParameter("goods_num");
		String brokerage = request.getParameter("borkerage");
		boolean flag=MobQuery.insert_delivery(sender_id,sender_name, sender_address,deliveryman_id,goods_num,goods_description,recipient_name,recipient_phone,recipient_address, sender_phone,send_time,brokerage);
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
