package util;


import java.io.FileInputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.io.File;


public class JDBCConnection {

	public static Connection conn = null;
	
	public static Connection getConnection() {
		
		try {
			if(conn == null)
			{

				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				
				
				Properties props = new Properties();
				FileInputStream input = new FileInputStream("C:\\Users\\mcvan\\Documents\\workspace-sts-3.9.8.RELEASE\\Project1\\src\\main\\resources\\connection.properties");
				props.load(input);
				

				
				String endpoint = props.getProperty("url");
				String username = props.getProperty("username");
				String password = props.getProperty("password");
//				String endpoint = "jdbc:oracle:thin:@michaelsdetdb.c9mbeoxqtszc.us-east-2.rds.amazonaws.com:1521:ORCL";
//				String username = "michael";
//				String password = "password";
				
				conn = DriverManager.getConnection(endpoint, username, password);
				return conn;
			}
			else
				return conn;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return conn;
	}
	
}
