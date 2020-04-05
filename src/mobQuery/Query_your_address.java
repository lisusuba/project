package mobQuery;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import delivery.Deliveryer;
import my.MobAddress;
import my.MobQuery;

/**
 * Servlet implementation class Query_your_address
 */
public class Query_your_address extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Query_your_address() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String id = (String)request.getSession().getAttribute("login_id");
		List<MobAddress> row = MobQuery.Query_your_address(id);
		JSONArray result = new JSONArray(row);
		for(Object s:result)
		{
			System.out.println("MobAddress");
			//System.out.println(s.goods_num+" "+s.sender_id+" "+" "+s.sender_address+" "+s.recipient_address+" " +s.recipient_phone);
		    System.out.println(s);
		}
		// 返回应答数据
		response.setContentType("text/html;charset=UTF-8");
		response.setContentType("text/plain");
		Writer writer = response.getWriter();
		writer.write( result.toString(2));
		writer.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
