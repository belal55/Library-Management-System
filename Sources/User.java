package User;
import Main.*;
import Admin.*;
import DbConnector.*;
import Books.*;
import Login.*;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.cell.PropertyValueFactory;

/**
* Library Mangement System Using JavaFx.

* @author  Md. Belal Hosen
* @author  Md. Mahadi Sazzadur Rahman
* @version 1.0
* @since   2016-12-27 
*/
public class User{

	private SimpleIntegerProperty id;
	private SimpleStringProperty firstname;
	private SimpleStringProperty lastname;
	private SimpleStringProperty username;
	private SimpleStringProperty password;
	private SimpleStringProperty bbook;
	private SimpleStringProperty contactno;
	private SimpleStringProperty email;
	
	
	

	public User(int id,String firstname,String lastname,String username,String password,String bbook,String contactno,String email)
	{
		this.id=new SimpleIntegerProperty(id);
		this.firstname=new SimpleStringProperty(firstname);
		this.lastname=new SimpleStringProperty(lastname);
		this.username=new SimpleStringProperty(username);
		this.password=new SimpleStringProperty(password);
		this.bbook=new SimpleStringProperty(bbook);
		this.contactno=new SimpleStringProperty(contactno);
		this.email=new SimpleStringProperty(email);
		
	
	}
	
	
	
	public void setid(int uid)
	{
		id.set(uid);
	}
	
	public void setusername(String uname)
	{
		username.set(uname);
	}
	
	public void setpassword(String pass)
	{
		password.set(pass);
	}
	
	public void setbbook(String brbook)
	{
		bbook.set(brbook);
	}
	
	public void setfirstname(String fname)
	{
		firstname.set(fname);
	}
	
	public void setlastname(String lname)
	{
		lastname.set(lname);
	}
	
	public void setcontactno(String cno)
	{
		contactno.set(cno);
	}
	
	public void setemail(String Email)
	{
		email.set(Email);
	}
	
	
	
	public int getId()
	{
		return id.get();
	}
	
	public String getUsername()
	{
		return username.get();
	}
	
	public String getPassword()
	{
		return password.get();
	}
	
	public String getBbook()
	{
		return bbook.get();
	}
	
	public String getFirstname()
	{
		return firstname.get();
	}
	
	public String getLastname()
	{
		
		return lastname.get();
	}
	
	public String getContactno()
	{
		return contactno.get();
	}
	
	public String getEmail()
	{
		return email.get();
	}
	
	
}