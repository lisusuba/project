package query;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import delivery.Deliveryer;
import my.Test2;

/**
 * Servlet implementation class Query_your_sended
 */
public class Query_your_sended extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Query_your_sended() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String sender_id = (String)request.getSession().getAttribute("login_id");
		// 数据查询		
		List<Deliveryer> row = Test2.query_by_id(sender_id);
		
		// List -> JSONArray
		JSONArray result = new JSONArray(row);
		System.out.println(result);
		// 返回应答数据
		response.setContentType("text/html;charset=UTF-8");
		response.setContentType("text/plain");
		Writer writer = response.getWriter();
		writer.write( result.toString(2));
		writer.close();
	}

}
