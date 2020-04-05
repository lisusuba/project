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
 * Servlet implementation class Delete_your_address
 */
public class Delete_your_address extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete_your_address() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//String sender_id = (String)request.getSession().getAttribute("login_id");
		String address_num = request.getParameter("address_num");
		System.out.println(address_num);
		boolean flag=MobQuery.Delete_your_address(address_num);
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

	}

}
