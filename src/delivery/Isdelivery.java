package delivery;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import my.Test2;
import sending.Sender;
/**
 * Servlet implementation class Isdelivery
 */
public class Isdelivery extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Isdelivery() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// 数据查询		
		List<Deliveryer> row = Test2.query_by_issended();
		
		// List -> JSONArray
		JSONArray result = new JSONArray(row);
		for(Object s:result)
		{
			System.out.println("isdelivery");
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
