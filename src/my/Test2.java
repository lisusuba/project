package my;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;

import delivery.Deliveryer;
import sending.Sender;
public class Test2 
{
	//方法一，插入数据，插入注册数据
	public static boolean testConnect(String id,String name,String date,String password,String phone) throws Exception
	{
		//插入数据
		dbconnection db = new dbconnection();
		Connection conn = db.testConnect();
		Statement stmt = conn.createStatement();
		//查找数据库中id，与输入id进行比较有没有重复
		boolean flag=true;
		ResultSet rs = null;
		stmt = conn.createStatement();
		//select c_id,c_name,c_address from t_student；
		String sql = "select id from users";
		rs = stmt.executeQuery(sql);
		while(rs.next() && flag)
		{
			String userstable_id = rs.getString("id");
			System.out.println(userstable_id+" "+id);
			if(userstable_id.equals(id)) 
			{
				flag=false;
				System.out.println("现在匹配");
				System.out.println("关闭连接!");
			}
		}

		if(flag==true)
		{
			String sqll = "insert into users(id,name,date,password,phone) values(?,?,?,?,?)";
			PreparedStatement ptmt = conn.prepareStatement(sqll);
			
			ptmt.setString(1, id);
			ptmt.setString(2, name);
			ptmt.setString(3, date);
			ptmt.setString(4, password);
			ptmt.setString(5, phone);
			ptmt.execute();
			System.out.println("AddPerson插入成功关闭连接!");
		}
		conn.close();
		return flag;
	}
	
	//方法二，查找数据，查询ID，password已验证登录
	public static boolean query_Id_password(String login_id,String login_password)
	{
		//String log_id= (String)login_password;
		boolean flag=true;
		ResultSet rs = null;
		//查找数据
		dbconnection db = new dbconnection();
		Connection conn = null;
		try {
			conn = db.testConnect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//					Statement stmt = conn.createStatement();

		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//select c_id,c_name,c_address from t_student；
		String sql = "select id,password from users";
		try {
			 rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		
		try {
			while(rs.next() && flag)
			{
				String id = rs.getString("id");
				String password = rs.getString("password");
				System.out.println(id +"\t"+password);
				System.out.println(login_id+"\t"+login_password);

				if(login_password.equals(password) && login_id.equals(id)) 
				{
					System.out.println("现在匹配");
					System.out.println("关闭连接!");
					conn.close();
					flag=false;
					return true;
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//判断flag的值 若为false则跳出整个程序
		if(flag==false)
			return true;
		
		try {
			System.out.println("关闭连接!");
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
	//方法三，插入发送的快递信息
	public static void AddSend(String sender_id,String sender_name,String sender_phone,
			String sender_address,String recipient_name,String recipient_phone,
			String recipient_address,String date,String goods_description,String goods_type) throws Exception
	{
		//插入数据
		dbconnection db = new dbconnection();
		Connection conn = db.testConnect();
//			Statement stmt = conn.createStatement();
		System.out.println(sender_id);
		System.out.println(date);
		
		String sql = "insert into send_table(id,sender_name,date,sender_phone,"
				+ "sender_address,recipient_name,recipient_phone,recipient_address,"
				+ "goods_description,goods_type) values(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		
		ptmt.setString(1, sender_id);
		ptmt.setString(2, sender_name);
		ptmt.setString(3, date);
		ptmt.setString(4, sender_phone);
		ptmt.setString(5, sender_address);
		ptmt.setString(6, recipient_name);
		ptmt.setString(7, recipient_phone);
		ptmt.setString(8, recipient_address);
		ptmt.setString(9, goods_description);
		ptmt.setString(10, goods_type);
		ptmt.execute();
		conn.close();
		System.out.println("关闭连接!");
	}
	//方法四，代取人员查询订单，按是否已被代取值查询，0未被代取，1已被代取
	//查找数据
	public static List<Deliveryer> query_by_issended()
	{
		//定义data为查询出来未被接单的结果集合
		List<Deliveryer> data = new ArrayList<Deliveryer>();
		//String log_id= (String)login_password;
		ResultSet rs = null;
		//查找数据
		dbconnection db = new dbconnection();
		Connection conn = null;
		try {
			conn = db.testConnect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//						Statement stmt = conn.createStatement();

		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//select c_id,c_name,c_address from t_student；
		String sql = "select id,issended,sender_name,date,sender_phone,"
				+ "sender_address,recipient_name,recipient_phone,recipient_address,"
				+ "goods_description,goods_num,brokerage,goods_type from send_table";
		try {
			 rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//从send_table中查找信息
		try {
			while(rs.next())
			{
				int issended = rs.getInt("issended");
				String sender_id = rs.getString("id");
				String sender_name = rs.getString("sender_name");
				String date = rs.getString("date");
				String sender_phone = rs.getString("sender_phone");
				String sender_address = rs.getString("sender_address");
				String recipient_name =rs.getString("recipient_name");
				String recipient_phone = rs.getString("recipient_phone");
				String recipient_address = rs.getString("recipient_address");
				String goods_description = rs.getString("goods_description");
				int goods_num = rs.getInt("goods_num");
				int brokerage = rs.getInt("brokerage");
				String goods_type = rs.getString("goods_type");
				String phone = "";
				if(issended==0) 
				{
//					public Deliveryer(int goods_num,String date,String sender_id,String sender_name,
					//String sender_phone,String sender_address,
//							String recipient_name,String recipient_phone,
					//String recipient_address,String goods_description)
					//data为结果集合
					data.add(new Deliveryer(goods_num,date,sender_id,sender_name,sender_phone,
							sender_address,recipient_name,
							recipient_phone,recipient_address,goods_description,brokerage,goods_type,phone));
					System.out.println("现在匹配");
					System.out.println(goods_num+" "+date+" "+sender_id+" "+
					sender_name+" "+sender_phone+" "+sender_address+" "+
					recipient_name+" "+recipient_phone+" "+recipient_address+" "+
					goods_description+" "+brokerage+" "+goods_type);
					System.out.println("关闭连接!");
				}
			}
			conn.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return data;
	}
	//方法五，更新数据，更新是否已代取的标志位issended,并把取出来的订单插入到delivery_table表中
	public static boolean update_issended(String goods_num,String deliveryman_id)
	{
		boolean flag=true;
		//选出被快递员勾选的货物并插入deliveryman_table,变量定义
		System.out.println("快递员id"+deliveryman_id);
		ResultSet rs = null;
		String sender_id = null;
		String sender_name=null;
		String send_date = null;
		String sender_phone=null;
		String sender_address=null;
		String recipient_name=null;
		String recipient_phone=null;
		String recipient_address=null;
		String goods_description=null;
		int brokerage =0;
		int goods_nums = 0;
		//定义结束
		//查找数据
		dbconnection db = new dbconnection();
		Connection conn = null;
		try {
			conn = db.testConnect();
		} catch (Exception e) {
			flag=false;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//						Statement stmt = conn.createStatement();

		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			flag=false;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//查找deliveryman_name快递员姓名
		String selectsql = "select name from users where id='"+deliveryman_id+"'";
		String deliveryman_name=null;
		ResultSet deliveryman_name_rs = null;
		//获取下代取订单的时间
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm;ss");
		String get_date = sdf.format(d);
		try {
			deliveryman_name_rs = stmt.executeQuery(selectsql);
		} catch (SQLException e2) {
			flag=false;
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			while(deliveryman_name_rs.next())
			{
				deliveryman_name=deliveryman_name_rs.getString("name");
				System.out.println(deliveryman_name);
			}
		} catch (SQLException e) {
			flag=false;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//获取时间查找姓名结束
		//分离出goods_num
		for(int i = 0; i < goods_num.length() ; i++)
		{
		  
		  String ss = goods_num.substring(i, i+1);
		  if(!ss.equals(","))
			  if(!ss.equals("/"))
			  {
				    System.out.println(ss);
				    int a = Integer.parseInt(ss);
				//select c_id,c_name,c_address from t_student；
//				    update table_1 t1,table_2 t2 set t1.column = t2.column where t1.id = t2.pid
				    //把快递员勾选的货物插入到delivery_table
				   //查处被选中的货物的信息
				    String sqlselect = "select id,issended,sender_name,date,sender_phone,"
							+ "sender_address,recipient_name,recipient_phone,recipient_address,"
							+ "goods_description,brokerage, goods_num from send_table where goods_num="+a;
				    try {
						rs = stmt.executeQuery(sqlselect);
						 while(rs.next())
					    {
							sender_id = rs.getString("id");
							sender_name = rs.getString("sender_name");
							send_date = rs.getString("date");
							sender_phone = rs.getString("sender_phone");
						    sender_address = rs.getString("sender_address");
							recipient_name =rs.getString("recipient_name");
							recipient_phone = rs.getString("recipient_phone");
							recipient_address = rs.getString("recipient_address");
							goods_description = rs.getString("goods_description");
							brokerage = rs.getInt("brokerage");
							goods_nums = rs.getInt("goods_num");
					    }
					} catch (SQLException e2) {
						flag=false;
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				   //查找结束
				  //插入到delivery_table
					String insertsql = "insert into delivery_table(sender_id,sender_name,sender_address,"
							+ "deliveryman_id,deliveryman_name,goods_num,goods_description,recipient_name,"
							+ "recipient_phone,recipient_address,sender_phone,get_date,"
							+ "send_time,brokerage) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					PreparedStatement ptmt;
					try {
						ptmt = conn.prepareStatement(insertsql);
						ptmt.setString(1, sender_id);
						ptmt.setString(2, sender_name);
						ptmt.setString(3, sender_address);
						ptmt.setString(4, deliveryman_id);
						ptmt.setString(5, deliveryman_name);
						ptmt.setInt(6, goods_nums);
						ptmt.setString(7, goods_description);
						ptmt.setString(8, recipient_name);
						ptmt.setString(9, recipient_phone);
						ptmt.setString(10, recipient_address);
						ptmt.setString(11, sender_phone);
						ptmt.setString(12, get_date);
						ptmt.setString(13, send_date);
						ptmt.setInt(14, brokerage);
						ptmt.execute();
						System.out.println("插入delivery_table成功");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						flag=false;
						e1.printStackTrace();
					}
					//插入结束
					//更新是否已经被下单的状态
					String sql = "update send_table set issended=1 where goods_num="+a;
					//更新快递的状态 2020年3月12日
					String updateStateSql="update send_table set state=1 where goods_num="+a;
					try {
						 stmt.execute(sql);
						 stmt.execute(updateStateSql);
						 System.out.println("update_issended"+"更新成功");
						 System.out.println("state状态"+"更新成功");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						flag=false;
						e.printStackTrace();
					}
			  }
		}
		return flag;
	}
	//方法六，更新快递状态由未接单更新至已接单运送中
	//查找数据
		public static List<Deliveryer> query_by_id(String id)
		{
			//定义data为查询出来未被接单的结果集合
			List<Deliveryer> data = new ArrayList<Deliveryer>();
			//String log_id= (String)login_password;
			ResultSet rs = null;
			//查找数据
			dbconnection db = new dbconnection();
			Connection conn = null;
			try {
				conn = db.testConnect();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//							Statement stmt = conn.createStatement();

			Statement stmt = null;
			try {
				stmt = conn.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//select c_id,c_name,c_address from t_student；
			String sql = "select id,issended,sender_name,date,sender_phone,"
					+ "sender_address,recipient_name,recipient_phone,recipient_address,"
					+ "goods_description,goods_num,brokerage,goods_type from send_table";
			try {
				 rs = stmt.executeQuery(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//				
			try {
				while(rs.next())
				{
					int issended = rs.getInt("issended");
					String sender_id = rs.getString("id");
					String sender_name = rs.getString("sender_name");
					String date = rs.getString("date");
					String sender_phone = rs.getString("sender_phone");
					String sender_address = rs.getString("sender_address");
					String recipient_name =rs.getString("recipient_name");
					String recipient_phone = rs.getString("recipient_phone");
					String recipient_address = rs.getString("recipient_address");
					String goods_description = rs.getString("goods_description");
					int goods_num = rs.getInt("goods_num");
					int brokerage = rs.getInt("brokerage");
					String goods_type = rs.getString("goods_type");
					String deliveryid = Query_deliveryman_id(goods_num);
					String phone=Query_phone(deliveryid);
//					System.out.println("phone");
					if(sender_id.equals(id)) 
					{

//						public Deliveryer(int goods_num,String date,String sender_id,String sender_name,
						//String sender_phone,String sender_address,
//								String recipient_name,String recipient_phone,
						//String recipient_address,String goods_description)
						//data为结果集合
						data.add(new Deliveryer(goods_num,date,sender_id,sender_name,sender_phone,
								sender_address,recipient_name,
								recipient_phone,recipient_address,goods_description,brokerage,goods_type,phone));
						System.out.println("现在匹配");
						System.out.println(goods_num+" "+date+" "+sender_id+" "+
						sender_name+" "+sender_phone+" "+sender_address+" "+
						recipient_name+" "+recipient_phone+" "+recipient_address+" "+
						goods_description+" "+brokerage);
						System.out.println("关闭连接!");
					}
				}
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return data;
		}
		//方法七，按id查询个人的代取/送记录
		//查找数据
		public static List<Deliveryer> query_by_deliveryer_id(String id)
		{
//			int isget_goods;
			//定义data为查询出来未被接单的结果集合
			List<Deliveryer> data = new ArrayList<Deliveryer>();
			//String log_id= (String)login_password;
			ResultSet rs = null;
//			ResultSet rs1 = null;
//			String goods_type = null;
			//查找数据
			dbconnection db = new dbconnection();
			Connection conn = null;
			try {
				conn = db.testConnect();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//								Statement stmt = conn.createStatement();

			Statement stmt = null;
			try {
				stmt = conn.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//select c_id,c_name,c_address from t_student；
			String sql = "select goods_num,send_time,get_date,sender_id,"
					+ "sender_name,sender_phone,sender_address,recipient_name,recipient_phone,recipient_address,"
					+ "goods_description,brokerage from delivery_table where deliveryman_id='"+id+"'";
			try {
				 rs = stmt.executeQuery(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//					
			try {
				while(rs.next())
				{
					//String deliveryman_id = rs.getString("deliveryman_id");
					String sender_id = rs.getString("sender_id");
					String sender_name = rs.getString("sender_name");
					String get_date = rs.getString("get_date");
					String sender_phone = rs.getString("sender_phone");
					String sender_address = rs.getString("sender_address");
					String recipient_name =rs.getString("recipient_name");
					String recipient_phone = rs.getString("recipient_phone");
					String recipient_address = rs.getString("recipient_address");
					String goods_description = rs.getString("goods_description");
					int goods_num = rs.getInt("goods_num");
					int brokerage = rs.getInt("brokerage");
					String send_date = rs.getString("send_time");
					String goodstype=query_goods_type(goods_num);
					int isget_goods=MobQuery.select_isget_goods(goods_num);
					System.out.println(goodstype);
//					Deliveryer(String sender_id,String sender_name,String get_date,
//					String sender_phone,String sender_address,String recipient_name,String recipient_phone,
//					String recipient_address,String goods_description,int goods_num,int brokerage,
//					String send_time)
					data.add(new Deliveryer(sender_id,sender_name,get_date,
							sender_phone,sender_address,recipient_name,recipient_phone,
							recipient_address,goods_description,goods_num,brokerage,
							send_date,goodstype,isget_goods));
					System.out.println("现在匹配");
					System.out.println(goods_num+" "+send_date+" "+get_date+" "+sender_id+" "+
					sender_name+" "+sender_phone+" "+sender_address+" "+
					recipient_name+" "+recipient_phone+" "+recipient_address+" "+
					goods_description+" "+brokerage);
					System.out.println("关闭连接!");
				}
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return data;
		}
		
	//方法八，删除用户所选订单
	public static void Delete_orders(String goods_num)
	{
		//查找数据
		dbconnection db = new dbconnection();
		Connection conn = null;
		try {
			conn = db.testConnect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//						Statement stmt = conn.createStatement();

		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//分离出goods_num
		for(int i = 0; i < goods_num.length() ; i++)
		{
		  
		  String ss = goods_num.substring(i, i+1);
		  if(!ss.equals(","))
			  if(!ss.equals("/"))
			  {
				    System.out.println(ss);
				    int a = Integer.parseInt(ss);
					//String sql = "update send_table set issended=1 where goods_num="+a;
					String sqldelete = "delete from send_table where goods_num="+a;
					try {
						 stmt.execute(sqldelete);
						 System.out.println("订单删除成功");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			  }
		}
	}
	//方法九，删除快递员所选订单
		public static boolean Delete_order(String goods_num)
		{
			boolean flag=false;
			//查找数据
			dbconnection db = new dbconnection();
			Connection conn = null;
			try {
				conn = db.testConnect();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//							Statement stmt = conn.createStatement();

			Statement stmt = null;
			try {
				stmt = conn.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//删除订单
			String sqldelete = "delete from delivery_table where goods_num="+goods_num;
			try {
				 stmt.execute(sqldelete);
				 System.out.println("订单删除成功");
				 flag=true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return flag;
		}
	//方法九结束
	//方法十开始按id查询个人的代取/送记录,并且按照state的状态查出正在运送的快递
	//查找数据
	public static List<Deliveryer> query_deliverying(String id)
	{
		int state=0;
		//定义data为查询出来未被接单的结果集合
		List<Deliveryer> data = new ArrayList<Deliveryer>();
		//String log_id= (String)login_password;
		ResultSet rs = null;
		//查找数据
		dbconnection db = new dbconnection();
		Connection conn = null;
		try {
			conn = db.testConnect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//										Statement stmt = conn.createStatement();

		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//select c_id,c_name,c_address from t_student；
		String sql = "select goods_num,send_time,get_date,sender_id,"
				+ "sender_name,sender_phone,sender_address,recipient_name,recipient_phone,recipient_address,"
				+ "goods_description,brokerage,state from delivery_table where deliveryman_id='"+id+"'";
		try {
			 rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//							
		try {
			while(rs.next())
			{
				state=rs.getInt("state");
				if(state==1)
				{
					//String deliveryman_id = rs.getString("deliveryman_id");
					String sender_id = rs.getString("sender_id");
					String sender_name = rs.getString("sender_name");
					String get_date = rs.getString("get_date");
					String sender_phone = rs.getString("sender_phone");
					String sender_address = rs.getString("sender_address");
					String recipient_name =rs.getString("recipient_name");
					String recipient_phone = rs.getString("recipient_phone");
					String recipient_address = rs.getString("recipient_address");
					String goods_description = rs.getString("goods_description");
					int goods_num = rs.getInt("goods_num");
					int brokerage = rs.getInt("brokerage");
					String send_date = rs.getString("send_time");
					String goods_type = query_goods_type(goods_num);
					int isget_goods=MobQuery.select_isget_goods(goods_num);
//								Deliveryer(String sender_id,String sender_name,String get_date,
//								String sender_phone,String sender_address,String recipient_name,String recipient_phone,
//								String recipient_address,String goods_description,int goods_num,int brokerage,
//								String send_time)
					data.add(new Deliveryer(sender_id,sender_name,get_date,
							sender_phone,sender_address,recipient_name,recipient_phone,
							recipient_address,goods_description,goods_num,brokerage,
							send_date,goods_type,isget_goods));
					System.out.println("现在匹配");
					System.out.println(goods_num+" "+send_date+" "+get_date+" "+sender_id+" "+
					sender_name+" "+sender_phone+" "+sender_address+" "+
					recipient_name+" "+recipient_phone+" "+recipient_address+" "+
					goods_description+" "+brokerage);
					System.out.println("关闭连接!");
				}
				
			}
			conn.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return data;
	}
	//方法十结束
	//方法十一，确认送达并且改变订单的状态值开始
	public static void affrim(String goods_num) throws Exception
	{
		System.out.println("执行了吗");
		dbconnection db = new dbconnection();
		Connection conn = db.testConnect();
		Statement stmt = conn.createStatement();
		stmt = conn.createStatement();
		//select c_id,c_name,c_address from t_student；
		//更新快递的状态 2020年3月12日
		String updateStateSql="update send_table set state=2 where goods_num="+goods_num;
		stmt.execute(updateStateSql);
		conn.close();
	}
	//方法十一，确认送达并且改变订单的状态值结束
	//方法十二，查询已送达的快递开始
	//查找数据
	public static List<Deliveryer> query_deliveryed(String id)
	{
		int state=0;
		//定义data为查询出来未被接单的结果集合
		List<Deliveryer> data = new ArrayList<Deliveryer>();
		//String log_id= (String)login_password;
		ResultSet rs = null;
		//查找数据
		dbconnection db = new dbconnection();
		Connection conn = null;
		try {
			conn = db.testConnect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//											Statement stmt = conn.createStatement();

		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//select c_id,c_name,c_address from t_student；
		String sql = "select goods_num,send_time,get_date,sender_id,"
				+ "sender_name,sender_phone,sender_address,recipient_name,recipient_phone,recipient_address,"
				+ "goods_description,brokerage,state from delivery_table where deliveryman_id='"+id+"'";
		try {
			 rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//								
		try {
			while(rs.next())
			{
				state=rs.getInt("state");
				if(state==2)
				{
					//String deliveryman_id = rs.getString("deliveryman_id");
					String sender_id = rs.getString("sender_id");
					String sender_name = rs.getString("sender_name");
					String get_date = rs.getString("get_date");
					String sender_phone = rs.getString("sender_phone");
					String sender_address = rs.getString("sender_address");
					String recipient_name =rs.getString("recipient_name");
					String recipient_phone = rs.getString("recipient_phone");
					String recipient_address = rs.getString("recipient_address");
					String goods_description = rs.getString("goods_description");
					int goods_num = rs.getInt("goods_num");
					int brokerage = rs.getInt("brokerage");
					String send_date = rs.getString("send_time");
					String goods_type=query_goods_type(goods_num);
					int isget_goods=MobQuery.select_isget_goods(goods_num);
//									Deliveryer(String sender_id,String sender_name,String get_date,
//									String sender_phone,String sender_address,String recipient_name,String recipient_phone,
//									String recipient_address,String goods_description,int goods_num,int brokerage,
//									String send_time)
					data.add(new Deliveryer(sender_id,sender_name,get_date,
							sender_phone,sender_address,recipient_name,recipient_phone,
							recipient_address,goods_description,goods_num,brokerage,
							send_date,goods_type,isget_goods));
					System.out.println("现在匹配");
					System.out.println(goods_num+" "+send_date+" "+get_date+" "+sender_id+" "+
					sender_name+" "+sender_phone+" "+sender_address+" "+
					recipient_name+" "+recipient_phone+" "+recipient_address+" "+
					goods_description+" "+brokerage);
					System.out.println("关闭连接!");
				}
				
			}
			conn.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return data;
	}
	//方法十二，查处已送达的快递结束
	//方法十三，查找已发快递状态
//查找数据
	public static List<Deliveryer> query_your_send_state(String id,int state)
	{
		int send_state;
		//定义data为查询出来未被接单的结果集合
		List<Deliveryer> data = new ArrayList<Deliveryer>();
		//String log_id= (String)login_password;
		ResultSet rs = null;
		//查找数据
		dbconnection db = new dbconnection();
		Connection conn = null;
		try {
			conn = db.testConnect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//								Statement stmt = conn.createStatement();

		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//select c_id,c_name,c_address from t_student；
		String sql = "select id,issended,sender_name,date,sender_phone,"
				+ "sender_address,recipient_name,recipient_phone,recipient_address,"
				+ "goods_description,goods_num,brokerage,state,goods_type from send_table";
		try {
			 rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//					
		try {
			while(rs.next())
			{
				send_state=rs.getInt("state");
				if(send_state==state)
				{
					int issended = rs.getInt("issended");
					String sender_id = rs.getString("id");
					String sender_name = rs.getString("sender_name");
					String date = rs.getString("date");
					String sender_phone = rs.getString("sender_phone");
					String sender_address = rs.getString("sender_address");
					String recipient_name =rs.getString("recipient_name");
					String recipient_phone = rs.getString("recipient_phone");
					String recipient_address = rs.getString("recipient_address");
					String goods_description = rs.getString("goods_description");
					int goods_num = rs.getInt("goods_num");
					int brokerage = rs.getInt("brokerage");
					String goods_type = rs.getString("goods_type");
					String deliveryid = Query_deliveryman_id(goods_num);
					String phone=Query_phone(deliveryid);
					if(sender_id.equals(id)) 
					{

//								public Deliveryer(int goods_num,String date,String sender_id,String sender_name,
						//String sender_phone,String sender_address,
//										String recipient_name,String recipient_phone,
						//String recipient_address,String goods_description)
						//data为结果集合
						data.add(new Deliveryer(goods_num,date,sender_id,sender_name,sender_phone,
								sender_address,recipient_name,
								recipient_phone,recipient_address,goods_description,brokerage,goods_type,phone));
						System.out.println("现在匹配");
						System.out.println(goods_num+" "+date+" "+sender_id+" "+
						sender_name+" "+sender_phone+" "+sender_address+" "+
						recipient_name+" "+recipient_phone+" "+recipient_address+" "+
						goods_description+" "+brokerage);
						System.out.println("关闭连接!");
					}
				}
			}
			conn.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return data;
	}
	//方法十三结束
	//方法十四，查询货物类型
	public static String query_goods_type(int goods_num)
	{
		ResultSet rs = null;
		//查找数据
		dbconnection db = new dbconnection();
		Connection conn = null;
		String goods_type = null;
		try {
			conn = db.testConnect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Statement stmt = conn.createStatement();

		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql="select goods_type from send_table where goods_num="+goods_num;
	    try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			while(rs.next())
				goods_type=rs.getString("goods_type");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    //System.out.println(goods_type);
		return goods_type;
	}
	//方法十四查询货物类型结束
	//方法十五，根据货物编号查出delieryman_id
	public static String Query_deliveryman_id(int goods_num)
	{
		String deliveryman_id = null;
		ResultSet rs = null;
		//查找数据
		dbconnection db = new dbconnection();
		Connection conn = null;
		try {
			conn = db.testConnect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql="select deliveryman_id from delivery_table where goods_num="+goods_num;
	    try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			while(rs.next())
				deliveryman_id=rs.getString("deliveryman_id");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    System.out.println(deliveryman_id);
	    
		return deliveryman_id;
	}
	//方法十五结束
	//方法十六，根据id查处其电话
	public static String Query_phone(String id)
	{
		String phone = null;
		ResultSet rs = null;
		//查找数据
		dbconnection db = new dbconnection();
		Connection conn = null;
		try {
			conn = db.testConnect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql="select phone from users where id="+"'"+id+"'";
	    try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			while(rs.next())
				phone=rs.getString("phone");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    System.out.println(phone);
		return phone;
	}
	//方法十六结束
	public static void main(String[] args) 
	{
		
		List<Deliveryer> a=query_by_deliveryer_id("dw");
		JSONArray result = new JSONArray(a);
		for(Object s:result)
		{
			System.out.println("isdelivery");
			//System.out.println(s.goods_num+" "+s.sender_id+" "+" "+s.sender_address+" "+s.recipient_address+" " +s.recipient_phone);
		    System.out.println(s);
		}
//		String id=Query_deliveryman_id(9);
//		Query_phone(id);
	}
}
