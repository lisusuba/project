package my;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
    //import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
	
		
	/**
	 * Servlet implementation class AddPerson
	 */
	//@WebServlet("/AddStudent")
	public class AddPerson extends HttpServlet
	{
		private static final long serialVersionUID = 1L;
	
		/**
		 * @see HttpServlet#HttpServlet()
		 */
		public AddPerson()
		{
			super();
			// TODO Auto-generated constructor stub
		}
	
		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
		 *      response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException
		{
			// TODO Auto-generated method stub
			response.getWriter().append("Served at: ").append(request.getContextPath());
		}
	
		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
		 *      response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException
		{
			String flag="1";
			boolean bool=true;
			// 因为请求字段里的中文数据，所以要显式设置一下字符编码
			request.setCharacterEncoding("UTF-8");
			
			//从请求里提取参数字段的值
			String id = request.getParameter("id");
			String name = request.getParameter("regist_name");
			String password = request.getParameter("regist_password");
			String phone = request.getParameter("regist_phone");
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm;ss");
			String date = sdf.format(d);
			System.out.println(id+" "+name+" "+password+" "+date+" "+phone);
			
			// 添加到数据库
			person s = new person(id, name, date, password,phone);
			//Test2 addstu = new Test2();
			try 
			{
				bool=Test2.testConnect(s.id, s.name, s.date, s.password,s.phone);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(bool);
			if(bool==true)
				flag="1";
			else
				flag="0";
//			DemoDb.i.add( s );
//			System.out.println(s.id+s.name+s.date+s.password);
			// 返回应答数据
			JSONObject jresp = new JSONObject();
//			jresp.put("error", 0); // 错误码,0表示成功
//			jresp.put("reason", "OK"); // 错误原因描述, 如果没有错误则提示OK
			jresp.put("flag1", bool);
			response.setContentType("text/html;charset=UTF-8");
			response.setContentType("text/plain");
			Writer writer = response.getWriter();
			writer.write(jresp.toString(2));
			System.out.println(flag);
			writer.close();

		}

}
