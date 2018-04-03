package Books;
import User.*;
import Admin.*;
import Login.*;
import DbConnector.*;
import Main.*;

import java.sql.*;
import java.util.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
* Library Mangement System Using JavaFx.

* @author  Md. Belal Hosen
* @author  Md. Mahadi Sazzadur Rahman
* @version 1.0
* @since   2016-12-27 
*/

public class Books{

	private SimpleIntegerProperty bookid;
	private SimpleStringProperty bookname;
	private SimpleStringProperty author;
	private SimpleIntegerProperty copy;
	private SimpleStringProperty shelfno;
	private SimpleIntegerProperty courseId;
	private SimpleStringProperty dept;

	
	
	

	public Books(int bid,String name,String author,int copy,String shelfno,int courseid,String dept)
	{
		this.bookid=new SimpleIntegerProperty(bid);
		this.bookname=new SimpleStringProperty(name);
		this.author=new SimpleStringProperty(author);
		this.copy=new SimpleIntegerProperty(copy);
		this.shelfno=new SimpleStringProperty(shelfno);
		this.courseId=new SimpleIntegerProperty(courseid);
		this.dept=new SimpleStringProperty(dept);
	
		
	
	}
	
	
	
	public void setbookid(int bid)
	{
		bookid.set(bid);
	}
	
	public void setbookname(String bname)
	{
		bookname.set(bname);
	}
	
	public void setauthor(String auth)
	{
		author.set(auth);
	}
	
	public void copy(int cp)
	{
		copy.set(cp);
	}
	
	public void setshelfno(String shno)
	{
		shelfno.set(shno);
	}
	
	public void setcourseId(int cid)
	{
		courseId.set(cid);
	}
	
	public void setdept(String dpt)
	{
		dept.set(dpt);
	}
	
	
	
	
	
	public int getBookid()
	{
		return bookid.get();
	}
	
	public String getBookname()
	{
		return bookname.get();
	}
	
	public String getAuthor()
	{
		return author.get();
	}
	
	public int getCopy()
	{
		return copy.get();
	}
	
	public String getShelfno()
	{
		return shelfno.get();
	}
	
	public int getCourseId()
	{
		
		return courseId.get();
	}
	
	public String getDept()
	{
		return dept.get();
	}
	
	
	
	
}