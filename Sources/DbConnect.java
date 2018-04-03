package DbConnector;
import User.*;
import Admin.*;
import Login.*;
import Books.*;
import Main.*;

import java.sql.*;
import java.util.*;

/**
* Library Mangement System Using JavaFx.

* @author  Md. Belal Hosen
* @author  Md. Mahadi Sazzadur Rahman
* @version 1.0
* @since   2016-12-27 
*/

public class DbConnect{
	static Connection conn=null;;
	
	public static Connection connectdb()
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/hello","root","");
			System.out.println("database connected");
			return conn;
			
		}catch(Exception ex){
			System.out.println("Erro: "+ex);
			return null;
		}
	}
	
}



	


