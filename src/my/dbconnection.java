package my;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class dbconnection 
{
		public Connection testConnect() throws Exception
		{
			// 注册MySQL驱动 (可以省略这一步)
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 连接MySQL服务器
			String username= "root";
			String password = "lpainiolsy1314";
			String connectionUrl = "jdbc:mysql://localhost:3306/express?useUnicode=true&characterEncoding=UTF-8";
			
			Connection conn = DriverManager.getConnection(connectionUrl, username, password);
			System.out.println("连接成功!");
			return conn;

		}

}
