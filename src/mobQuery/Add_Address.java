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
 * Servlet implementation class Add_Address
 */
public class Add_Address extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Add_Address() {
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
		String id = (String)request.getSession().getAttribute("login_id");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String isdefault= request.getParameter("isdefault");
		String isupdate = request.getParameter("isupdate");
		String address_num1 = request.getParameter("address_num1");
		String isexpress=request.getParameter("isexpress");
		System.out.println("Add_address");
		System.out.println(name+","+phone+","+address+","+isdefault+","+isupdate+","+isexpress);
		try {
			MobQuery.insert_address(name, phone, address, id, isdefault,isupdate,address_num1,isexpress);
			// 返回应答数据
			JSONObject jresp = new JSONObject();
			jresp.put("error", 0); // 错误码,0表示成功
			jresp.put("reason", "OK"); // 错误原因描述, 如果没有错误则提示OK
			response.setContentType("text/html;charset=UTF-8");
			response.setContentType("text/plain");
			Writer writer = response.getWriter();
			writer.write( jresp.toString(2));
			writer.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
