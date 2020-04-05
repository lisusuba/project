package my;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;

import delivery.Deliveryer;

public class MobQuery 
{
	//方法一，查询地址开始
	//查找数据
	public static List<MobAddress> Query_your_address(String id)
	{
		List<MobAddress> data = new ArrayList<MobAddress>();
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
//												Statement stmt = conn.createStatement();

		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//select c_id,c_name,c_address from t_student；
		String sql = "select address,phone,name,isdefault,address_num,isexpress from address_table where address_id='"+id+"'";
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
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				String address = rs.getString("address");
				int isdefault = rs.getInt("isdefault");
				int address_num=rs.getInt("address_num");
				int isexpress = rs.getInt("isexpress");
				data.add(new MobAddress(name,phone,address,isdefault,address_num,isexpress));
				System.out.println("Query_your_address"+name+phone+address+isdefault+address_num+isexpress);
				System.out.println("现在匹配");
				System.out.println("关闭连接!");
				System.out.println(data);
			}
			conn.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return data;
	}
	//方法一，查地址结束
	//方法二，删除用户所选地址开始
	public static boolean Delete_your_address(String address_num)
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
		String sqldelete = "delete from address_table where address_num="+address_num;
		System.out.println(address_num);
		try {
			 stmt.execute(sqldelete);
			 flag=true;
			 System.out.println("地址删除成功");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	//方法二，删除用户所选地址结束
	//方法三插入地址开始
	public static void insert_address(String name,String phone,String address,String id,String isdefault,String isupdate,String address_num1,String isexpress) throws Exception
	{
		String isdefault1;
		String isexpress1;
		//插入数据
		dbconnection db = new dbconnection();
		Connection conn = db.testConnect();
		Statement stmt = conn.createStatement();
		Statement stmt1 = conn.createStatement();
		Statement stmt2 = conn.createStatement();
		Statement stmt3 = conn.createStatement();
		//插入快递地址
		Statement stmt4 = conn.createStatement();
		Statement stmt5 = conn.createStatement();
		Statement stmt6 = conn.createStatement();
		Statement stmt7 = conn.createStatement();
		ResultSet rs = null;
		if(isexpress.equals("0"))
		{
			if(isupdate.equals("0"))//isupdate 0为保存1为更新
			{
				if(isdefault.equals("0"))
				{
					String sql = "select isdefault,isexpress from address_table where address_id="+"'"+id+"'";
					rs = stmt.executeQuery(sql);;
					while(rs.next())
					{
						isexpress1 = rs.getString("isexpress");
						isdefault1 = rs.getString("isdefault");
						System.out.println("insert_address"+isdefault1+","+isexpress1);
						if(isdefault1.equals("0")&&isexpress1.equals("0"))//isexpress1 //0为收货人地址。1为快递地址
						{
							String sqlupdata="update address_table set isdefault=1 where isdefault=0";
							stmt1.execute(sqlupdata);
						}
					}
				}
				stmt = conn.createStatement();
				String sqll = "insert into address_table(address_id,isdefault,address,phone,name,isexpress) values(?,?,?,?,?,?)";
				PreparedStatement ptmt = conn.prepareStatement(sqll);
				
				ptmt.setString(1, id);
				ptmt.setString(2, isdefault);
				ptmt.setString(3, address);
				ptmt.setString(4, phone);
				ptmt.setString(5, name);
				ptmt.setString(6, isexpress);
				ptmt.execute();
				System.out.println("地址插入成功");
			}
			else
			{
				//若是更新地址
				//若是默认地址则更新其他默认地址信息
				if(isdefault.equals("0"))
				{
					String sql = "select isdefault isexpress from address_table where address_id="+"'"+id+"'";
					rs = stmt3.executeQuery(sql);
					while(rs.next())
					{
						isdefault1 = rs.getString("isdefault");
						isexpress1 = rs.getString("isexpress");
						System.out.println("insert_address"+isdefault1+","+isexpress1);
						if(isdefault1.equals("0")&&isexpress1.equals("0"))
						{
							String sqlupdata1="update address_table set isdefault=1 where isdefault=0";
							stmt1.execute(sqlupdata1);
							
						}
					}
				}
				String sqlupdata="update address_table set isdefault=1,isdefault="+"'"+isdefault+"'"+"address="+"'"+address+"'"+"phone="+"'"+phone+"'"+"name="+"'"+name+"'"+"where address_num="+address_num1;
				stmt2.execute(sqlupdata);
			}
		}
		//更新或者插入快递的地址
		else
		{
			if(isupdate.equals("0"))//isupdate 0为保存1为更新
			{
				if(isdefault.equals("0"))
				{
					String sql = "select isdefault,isexpress from address_table where address_id="+"'"+id+"'";
					rs = stmt4.executeQuery(sql);;
					while(rs.next())
					{
						isdefault1 = rs.getString("isdefault");
						System.out.println(isdefault);
						isexpress1 = rs.getString("isexpress");
						//isdefault1 = rs.getString("isdefault");
						System.out.println("insert_address"+isdefault1+","+isexpress1);
						if(isdefault1.equals("0")&&isexpress1.equals("1"))//isexpress1 //0为收货人地址。1为快递地址
						{
							String sqlupdata="update address_table set isdefault=1 where isdefault=0";
							stmt5.execute(sqlupdata);
						}
					}
				}
				stmt4 = conn.createStatement();
				String sqll = "insert into address_table(address_id,isdefault,address,phone,name,isexpress) values(?,?,?,?,?,?)";
				PreparedStatement ptmt = conn.prepareStatement(sqll);
				
				ptmt.setString(1, id);
				ptmt.setString(2, isdefault);
				ptmt.setString(3, address);
				ptmt.setString(4, phone);
				ptmt.setString(5, name);
				ptmt.setString(6, isexpress);
				ptmt.execute();
				System.out.println("地址插入成功");
			}
			else
			{
				//若是更新地址
				//若是默认地址则更新其他默认地址信息
				if(isdefault.equals("0"))
				{
					String sql = "select isdefault isexpress from address_table where address_id="+"'"+id+"'";
					rs = stmt7.executeQuery(sql);
					while(rs.next())
					{
						isdefault1 = rs.getString("isdefault");
						isexpress1 = rs.getString("isexpress");
						System.out.println("insert_address"+isdefault1+","+isexpress1);
						if(isdefault1.equals("0")&&isexpress1.equals("1"))
						{
							String sqlupdata1="update address_table set isdefault=1 where isdefault=0";
							stmt5.execute(sqlupdata1);
							
						}
					}
				}
				String sqlupdata="update address_table set isdefault=1,isdefault="+"'"+isdefault+"'"+"address="+"'"+address+"'"+"phone="+"'"+phone+"'"+"name="+"'"+name+"'"+"where address_num="+address_num1;
				stmt6.execute(sqlupdata);
			}
		}
		//select c_id,c_name,c_address from t_student；
		stmt1.close();
		stmt2.close();
		stmt3.close();
		stmt.close();
		stmt4.close();
		stmt5.close();
		stmt6.close();
		stmt7.close();
		conn.close();
	}
	//方法三结束
	//方法四：向delivery_table插入用户选中的订单并把issended和state改为1；
	public static boolean insert_delivery(String sender_id,String sender_name,String sender_address,String deliveryman_id,String goods_num,String goods_description,String recipient_name,String recipient_phone,String recipient_address,String sender_phone,String send_time,String brokerage)
	{//String sender_id,String sender_name,String sender_address,String deliveryman_id,String goods_num,String goods_description,String recipient_name,String recipient_phone,String recipient_address,String sender_phone,String sender_time,String brokerage
		boolean flag=true;
		ResultSet rs = null;
		String deliveryman_name = null;
		Statement stmt = null;
		//查找数据
		dbconnection db = new dbconnection();
		Connection conn = null;
		try {
			conn = db.testConnect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			flag=false;
			e.printStackTrace();
		}
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			flag=false;
			e.printStackTrace();
		}
		String sqlselect = "select name from users where id="+"'"+deliveryman_id+"'";
		try {
			 rs = stmt.executeQuery(sqlselect);
			 while(rs.next())
			 {
				 deliveryman_name=rs.getString("name");
				 System.out.println(deliveryman_name);
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			flag=false;
			e.printStackTrace();
		}
		String sqlupdata = "update send_table set issended=1,state=1 where goods_num="+goods_num;
		try {
			stmt.execute(sqlupdata);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			flag=false;
			e.printStackTrace();
		}
		//获取下代取订单的时间
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm;ss");
		String get_date = sdf.format(d);
		String sqlinsert = "insert into delivery_table(sender_id,sender_name,sender_address,deliveryman_id,deliveryman_name,goods_num,goods_description,recipient_name,recipient_phone,recipient_address,sender_phone,get_date,send_time,brokerage,state) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ptmt;
		try {
			ptmt = conn.prepareStatement(sqlinsert);
			ptmt.setString(1, sender_id);
			ptmt.setString(2, sender_name);
			ptmt.setString(3, sender_address);
			ptmt.setString(4, deliveryman_id);
			ptmt.setString(5, deliveryman_name);
			ptmt.setString(6, goods_num);
			ptmt.setString(7, goods_description);
			ptmt.setString(8, recipient_name);
			ptmt.setString(9, recipient_phone);
			ptmt.setString(10, recipient_address);
			ptmt.setString(11, sender_phone);
			ptmt.setString(12, get_date);
			ptmt.setString(13, send_time);
			ptmt.setString(14, brokerage);
			ptmt.setInt(15, 1);
			ptmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			flag=false;
			e.printStackTrace();
		}
		String sqlinsert1="insert into isget_goods_table(goods_num) value(?)";
		try {
			ptmt = conn.prepareStatement(sqlinsert1);
			ptmt.setString(1, goods_num);
			ptmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			flag=false;
			e.printStackTrace();
		}
		return flag;
	}
	//方法四结束
	//方法五骑手确认取到货，更新isget_goods_table表中的isget_goods的值为1
	public static int upstate(String goods_num)
	{
		String isget = null;
		int flag = 1;
		dbconnection db = new dbconnection();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = db.testConnect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			flag = 3;    //3代表错误
			e.printStackTrace();
		}
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			flag = 3;
			e.printStackTrace();
		}
		String selectsql="select isget_goods from isget_goods_table where goods_num="+"'"+goods_num+"'";
		try {
			 rs = stmt.executeQuery(selectsql);
			 while(rs.next())
			 {
				 isget=rs.getString("isget_goods");
				 System.out.println(isget);
				 if(isget.equals("1"))
				 {
					 flag=2;  //2代表已确认取到货
					 System.out.println("已确认");
				 }
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			flag=3;
			e.printStackTrace();
		}
		if(!isget.equals("1"))
		{
			String updatesql="update isget_goods_table set isget_goods=1 where goods_num="+"'"+goods_num+"'";
			try {
				stmt.execute(updatesql);
				System.out.println("更新成功");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				flag = 3;
				e.printStackTrace();
			}
		}
		return flag;
	}
	//方法五更新delivery_table和send_table表中的state的值为1结束
	//方法六骑手确认已送达货物,更新isget_goods_table里面的isget_goods的状态为2
	public static int up_isget_goods(String goods_num)
	{
		String isget = null;
		int flag=1;  //1代表成功
		dbconnection db = new dbconnection();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = db.testConnect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			flag = 3;  //3代表错误
			e.printStackTrace();
		}
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			flag = 3;
			e.printStackTrace();
		}
		String selectsql="select isget_goods from isget_goods_table where goods_num="+"'"+goods_num+"'";
		try {
			 rs = stmt.executeQuery(selectsql);
			 while(rs.next())
			 {
				 isget=rs.getString("isget_goods");
				 System.out.println(isget);
				 if(isget.equals("2"))
				 {
					 flag=2;  //2代表已确认送达
					 System.out.println("已确认");
				 }
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			flag=3;
			e.printStackTrace();
		}
		if(!isget.equals("2"))
		{
			String updatesql="update isget_goods_table set isget_goods=2 where goods_num="+"'"+goods_num+"'";
			try {
				stmt.execute(updatesql);
				System.out.println("更新成功");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				flag = 3;
				e.printStackTrace();
			}
		}
		return flag;
	}
	//方法六就结束
	//方法七，查询isget_goods_table中的isget——goods
	public static int select_isget_goods(int goods_num)
	{
		int isget_goods = -1;
		dbconnection db = new dbconnection();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = db.testConnect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String selectsql="select isget_goods from isget_goods_table where goods_num="+"'"+goods_num+"'";
		try {
			 rs = stmt.executeQuery(selectsql);
			 while(rs.next())
			 {
				 isget_goods=rs.getInt("isget_goods");
				 System.out.println(isget_goods);
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isget_goods;
	}
	//方法七结束
	//方法八，更新state 和isget_goods_table里面的isget_goods
	public static int Customer_Affirm_finish(String goods_num)
	{
		String isget;
		ResultSet rs=null;
		int flag = 1;
		dbconnection db = new dbconnection();
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = db.testConnect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			flag = 3;    //3代表错误
			e.printStackTrace();
		}
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			flag = 3;
			e.printStackTrace();
		}
		String selectsql="select isget_goods from isget_goods_table where goods_num="+"'"+goods_num+"'";
		try {
			 rs = stmt.executeQuery(selectsql);
			 while(rs.next())
			 {
				 isget=rs.getString("isget_goods");
				 System.out.println(isget);
				 if(isget.equals("3"))
				 {
					 flag=2;  //2代表已确认送达
					 System.out.println("已确认");
				 }
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			flag=3;
			e.printStackTrace();
		}
		if(flag!=2)
		{
			String updatesql1="update isget_goods_table set isget_goods=3 where goods_num="+"'"+goods_num+"'";
			String updatesql="update send_table set state=2 where goods_num="+"'"+goods_num+"'";
			String updatesql2="update delivery_table set state=2 where goods_num="+"'"+goods_num+"'";
			try {
				stmt.execute(updatesql);
				stmt.execute(updatesql1);
				stmt.execute(updatesql2);
				System.out.println("更新成功");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				flag = 3;
				e.printStackTrace();
			}
		}
		return flag;
	}
	//方法八结束
	//方法九，查询一个用户的全部订单
	public static List<Deliveryer> query_by_sended(String id)
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
				+ "goods_description,goods_num,brokerage,goods_type from send_table where id="+"'"+id+"'";
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
			
	//			public Deliveryer(int goods_num,String date,String sender_id,String sender_name,
				//String sender_phone,String sender_address,
	//								String recipient_name,String recipient_phone,
				//String recipient_address,String goods_description)
				//data为结果集合
				data.add(new Deliveryer(goods_num,date,sender_id,sender_name,sender_phone,
						sender_address,recipient_name,
						recipient_phone,recipient_address,goods_description,brokerage,goods_type,phone,issended));
				System.out.println("现在匹配");
				System.out.println(goods_num+" "+date+" "+sender_id+" "+
				sender_name+" "+sender_phone+" "+sender_address+" "+
				recipient_name+" "+recipient_phone+" "+recipient_address+" "+
				goods_description+" "+brokerage+" "+goods_type);
				System.out.println("关闭连接!");
				
			}
			conn.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return data;
	}
	//方法九结束
	//方法十，删除客户所选订单
		public static boolean Delete_order(String goods_num)
		{
			boolean flag=true;
			//查找数据
			dbconnection db = new dbconnection();
			Connection conn = null;
			try {
				conn = db.testConnect();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				flag=false;
				e.printStackTrace();
			}
//								Statement stmt = conn.createStatement();

			Statement stmt = null;
			try {
				stmt = conn.createStatement();
			} catch (SQLException e) {
				flag=false;
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//删除订单
			String sqldelete = "delete from send_table where goods_num="+goods_num;
			try {
				 stmt.execute(sqldelete);
				 System.out.println("订单删除成功");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				flag=false;
				e.printStackTrace();
			}
			return flag;
		}
		//方法十结束
		//方法十一，删除快递员所选订单
		public static boolean Delete_order1(String goods_num)
		{
			boolean flag=true;
			//查找数据
			dbconnection db = new dbconnection();
			Connection conn = null;
			try {
				conn = db.testConnect();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				flag=false;
				e.printStackTrace();
			}
//										Statement stmt = conn.createStatement();

			Statement stmt = null;
			try {
				stmt = conn.createStatement();
			} catch (SQLException e) {
				flag=false;
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//删除订单
			String sqldelete = "delete from delivery_table where goods_num="+goods_num;
			try {
				 stmt.execute(sqldelete);
				 System.out.println("订单删除成功");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				flag=false;
				e.printStackTrace();
			}
			return flag;
		}
		//方法十一结束
	public static void main(String[] args) throws Exception 
	{
		List<Deliveryer>data = query_by_sended("fd");
		JSONArray result = new JSONArray(data);
		for(Object s:result)
		{
			System.out.println("Query_all_sended");
			//System.out.println(s.goods_num+" "+s.sender_id+" "+" "+s.sender_address+" "+s.recipient_address+" " +s.recipient_phone);
		    System.out.println(s);
		}
	}
}
