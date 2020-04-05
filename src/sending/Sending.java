package sending;

import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.mysql.cj.Session;

import my.Test2;

/**
 * Servlet implementation class Sending
 */
public class Sending extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sending() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
//		String username = (String)request.getSession().getAttribute("login_id");
//		System.out.println(username);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		boolean flag;
		request.setCharacterEncoding("UTF-8");
		String fromname = request.getParameter("fromname");
		String fromphone = request.getParameter("fromphone");
		String fromaddress = request.getParameter("fromaddress");
		String toname = request.getParameter("toname");
		String tophone = request.getParameter("tophone");
		String toaddress = request.getParameter("toaddress");
		String goods_description = request.getParameter("goods_description");
		String goods_type = request.getParameter("goods_type");//手机端新加入的属性，电脑端没有
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm;ss");
		String date = sdf.format(d);
		//String username = (String)request.getSession().getAttribute("users"); //这里的xxxx就是你之前存在session里面的时候命的那个名字
		String sender_id = (String)request.getSession().getAttribute("login_id");
		//System.out.println(toname+" "+tophone);
		System.out.println(goods_type);
//		public Sender(String sender_id,String sender_name,String sender_phone,String sender_adress,
//				String recipient_name,String recipient_phone,String recipient_adress,String date,String goods_description)
		Sender sender = new Sender(sender_id,fromname,fromphone,fromaddress,toname,tophone,toaddress,date,goods_description,goods_type);
		System.out.println("sending"+sender.goods_type);
		try {
			Test2.AddSend(sender.sender_id, sender.sender_name, sender.sender_phone, 
					sender.sender_address,sender.recipient_name,
					sender.recipient_phone, sender.recipient_address, 
					sender.date, sender.goods_description,sender.goods_type);
			flag=true;
		} catch (Exception e) {
			flag=false;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
