package Admin;
import User.*;
import Books.*;
import Login.*;
import DbConnector.*;
import Main.*;

import java.sql.*;
import java.util.*;
import javafx.application.Application;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.stage.*;
import javafx.geometry.*;
import java.sql.*;
import javafx.beans.property.*;
import javafx.collections.*;
import javafx.scene.control.cell.*;
import javafx.scene.control.*;
import javafx.collections.*;
import java.util.*;
import javafx.collections.transformation.*;
import java.util.function.*;
import javafx.scene.control.Alert.*;
import java.util.regex.*;

/**
* Library Mangement System Using JavaFx.

* @author  Md. Belal Hosen
* @author  Md. Mahadi Sazzadur Rahman
* @version 1.0
* @since   2016-12-27 
*/

public class Admin extends Application{
	Connection conn = DbConnect.connectdb();
	PreparedStatement st = null;
	ResultSet rs = null;
	String fname,lname,uname,password,contactno,email,bookname;
	
	public TableView<User> table = new TableView<User>();
	ObservableList<User> data = FXCollections.observableArrayList();
	
	public TableView<Books> table1 = new TableView<Books>();
	ObservableList<Books> data1 = FXCollections.observableArrayList();
	
	final ObservableList<String> options = FXCollections.observableArrayList();
	public ListView<String> list = new ListView<String>(options);
	
	
	public static Stage classStage = new Stage();
	
	public void setuname(String name){
		this.uname=name;
	}
	
	public void setpass(String pass){
		this.password=pass;
	}	
	
	
	public void load_admin_details(){
		String sql = "select * from users where uname=? and  password=?";
			
			
			try{
				st=conn.prepareStatement(sql);
				st.setString(1,uname);
				st.setString(2,password);
				rs=st.executeQuery();
				if(rs.next()){
					fname=rs.getString("fname");
					lname=rs.getString("lname");
					uname=rs.getString("uname");
					contactno=rs.getString("contactno");
					email=rs.getString("email");
				
	
				}
				else{
					System.out.println("unseuccess");
				}
				st.close();
				rs.close();
			}catch(Exception ex){
				System.out.println("error: "+ex);
			}
	}
	
	
	public void load_books(){
		data1.clear();
		String sql3 = "select * from books";
			try{
				 st=conn.prepareStatement(sql3);
				 rs=st.executeQuery(sql3);
				 while(rs.next()){
					data1.add(
						new Books(
							rs.getInt("bookid"),
							rs.getString("bookname"),
							rs.getString("author"),
							rs.getInt("copy"),
							rs.getString("shelfno"),
							rs.getInt("courseId"),
							rs.getString("dept")
				 
						));
					table1.setItems(data1);
				}
				 st.close();
				 rs.close();
			}catch(Exception exc){
				System.out.println(exc);
			}
	}
	
	public void load_users(){
		data.clear();
		String sql2 = "select * from users";
			try{
				 st=conn.prepareStatement(sql2);
				 rs=st.executeQuery(sql2);
				 while(rs.next()){
					data.add(
					new User(
						rs.getInt("id"),
						rs.getString("fname"),
						rs.getString("lname"),
						rs.getString("uname"),
						rs.getString("password"),
						rs.getString("bbook"),
						rs.getString("contactno"),
						rs.getString("email")));
					table.setItems(data);
				}
				 st.close();
				 rs.close();
			}catch(Exception exc){
				System.out.println(exc);
			}
	}
	
	
	public void book_list_box(){
		options.clear();
		try{
			String sql4 = "select bookname from books";
			st=conn.prepareStatement(sql4);
			rs=st.executeQuery();
			
			while(rs.next()){
				options.add(rs.getString("bookname"));
			}
			st.close();
			rs.close();
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	
	
	
	public void start(Stage primaryStage)
	{
		load_admin_details();
		
		//welcome label
		Label l = new Label("Welcome "+uname);
		l.setPadding(new Insets(50));
		l.setTextFill(Color.web("#FFF"));
		l.setFont(Font.font("Arial", 16));
		l.setTextAlignment(TextAlignment.RIGHT);
		
		//Menu lable & buttons
		Label l2 = new Label("Menu");
		l2.setPadding(new Insets(20,50,20,50));
		l2.setTextFill(Color.web("#FFF"));
		l2.setFont(Font.font("Arial BLACK", 16));
		l2.setTextAlignment(TextAlignment.CENTER);
		
		Button profile = new Button("Profile");
		profile.setPadding(new Insets(10,0,10,0));
		profile.setPrefWidth(130.0);
		profile.setTextFill(Color.web("#000"));
		
		Button users = new Button("Users");
		users.setPadding(new Insets(10,0,10,0));
		users.setPrefWidth(130.0);
		
		Button books = new Button("Books");
		books.setPadding(new Insets(10,0,10,0));
		books.setPrefWidth(130.0);
		
		Button logout = new Button("Logout");
		logout.setPadding(new Insets(10,0,10,0));
		logout.setPrefWidth(130.0);
		
		Button add_book = new Button("Add Book");
		add_book.setPadding(new Insets(10,0,10,0));
		add_book.setPrefWidth(130.0);
	
		Button add = new Button("Add");
		add.setPadding(new Insets(10,50,10,50));
		add.setTextFill(Color.web("#000"));
		
		Button delete_book = new Button("Delete Book");
		delete_book.setPadding(new Insets(10,0,10,0));
		delete_book.setPrefWidth(130.0);
		
		Button delete = new Button("Delete");
		delete.setPadding(new Insets(10));
		delete.setPrefWidth(100);
		
		Button update = new Button("Update");
		update.setPadding(new Insets(10));
		update.setPrefWidth(100);
		
		Button issue_book = new Button("Issue Book");
		issue_book.setPadding(new Insets(10,0,10,0));
		issue_book.setPrefWidth(130.0);
		
		//users details
		Label l3 = new Label("First Name: "+fname);
		l3.setPadding(new Insets(100,10,10,100));
		l3.setTextFill(Color.web("#000"));
		l3.setFont(Font.font("Arial BLACK", 16));
		l3.setTextAlignment(TextAlignment.CENTER);
		
		Label l4 = new Label("Last Name: "+lname);
		l4.setPadding(new Insets(10,10,10,100));
		l4.setTextFill(Color.web("#000"));
		l4.setFont(Font.font("Arial BLACK", 16));
		l4.setTextAlignment(TextAlignment.CENTER);
		
		Label l5 = new Label("Contact No: "+contactno);
		l5.setPadding(new Insets(10,10,10,100));
		l5.setTextFill(Color.web("#000"));
		l5.setFont(Font.font("Arial BLACK", 16));
		l5.setTextAlignment(TextAlignment.CENTER);
		
		Label l6 = new Label("Email: "+email);
		l6.setPadding(new Insets(10,10,10,100));
		l6.setTextFill(Color.web("#000"));
		l6.setFont(Font.font("Arial BLACK", 16));
		l6.setTextAlignment(TextAlignment.CENTER);
		
		//search book labels and field
		Label l7 = new Label("Search Book: ");
		l7.setPadding(new Insets(10,10,20,100));
		l7.setFont(Font.font("Arial", 16));
		
		TextField search = new TextField();
		search.setPadding(new Insets(10));
		search.setPrefWidth(500);
		search.setPromptText("Enter book's name or author's name...");
		
		//search user label and field
		Label l17 = new Label("Search User: ");
		l17.setPadding(new Insets(10,10,20,100));
		l17.setFont(Font.font("Arial", 16));
		
		TextField search_user = new TextField();
		search_user.setPadding(new Insets(10));
		search_user.setPrefWidth(500);
		search_user.setPromptText("Enter Firstname or Lastname...");
		
		//Adding book form
		Label l8 = new Label("Book Id: ");
		l8.setPadding(new Insets(50,10,10,50));
		l8.setTextFill(Color.web("#000"));
		l8.setFont(Font.font("Arial BLACK", 16));
		
		Label l9 = new Label("Book Title: ");
		l9.setPadding(new Insets(10,10,10,28));
		l9.setTextFill(Color.web("#000"));
		l9.setFont(Font.font("Arial BLACK", 16));
		
		Label l10 = new Label("Author: ");
		l10.setPadding(new Insets(10,10,10,60));
		l10.setTextFill(Color.web("#000"));
		l10.setFont(Font.font("Arial BLACK", 16));
		
		Label l11 = new Label("No Of Copy: ");
		l11.setPadding(new Insets(10,10,10,20));
		l11.setTextFill(Color.web("#000"));
		l11.setFont(Font.font("Arial BLACK", 16));
		
		Label l12 = new Label("Shelf No: ");
		l12.setPadding(new Insets(10,10,10,45));
		l12.setTextFill(Color.web("#000"));
		l12.setFont(Font.font("Arial BLACK", 16));
		
		Label l13 = new Label("Course Id: ");
		l13.setPadding(new Insets(10,10,10,35));
		l13.setTextFill(Color.web("#000"));
		l13.setFont(Font.font("Arial BLACK", 16));
		
		Label l14 = new Label("Department: ");
		l14.setPadding(new Insets(10,10,10,17));
		l14.setTextFill(Color.web("#000"));
		l14.setFont(Font.font("Arial BLACK", 16));
		
		TextField t1 = new TextField();
		t1.setPadding(new Insets(10));
		t1.setPrefWidth(400);
		t1.setPromptText("Enter Book Id");
		
		TextField t2 = new TextField();
		t2.setPadding(new Insets(10));
		t2.setPrefWidth(400);
		t2.setPromptText("Enter Book Name");
		
		TextField t3 = new TextField();
		t3.setPadding(new Insets(10));
		t3.setPrefWidth(400);
		t3.setPromptText("Enter Author Name");
		
		TextField t4 = new TextField();
		t4.setPadding(new Insets(10));
		t4.setPrefWidth(400);
		t4.setPromptText("Enter Number of Copies");
		
		TextField t5 = new TextField();
		t5.setPadding(new Insets(10));
		t5.setPrefWidth(400);
		t5.setPromptText("Enter Shelf No");
		
		TextField t6 = new TextField();
		t6.setPadding(new Insets(10));
		t6.setPrefWidth(400);
		t6.setPromptText("Enter Course Id");
		
		
		TextField t7 = new TextField();
		t7.setPadding(new Insets(10));
		t7.setPrefWidth(400);
		t7.setPromptText("Enter Department");
		
		//deleting books
		TextField t8 = new TextField();
		t8.setPadding(new Insets(10));
		t8.setPrefWidth(400);
		t8.setPromptText("Book Id");
		
		TextField t9 = new TextField();
		t9.setPadding(new Insets(10));
		t9.setPrefWidth(400);
		t9.setPromptText("Book Title");
		
		TextField t10 = new TextField();
		t10.setPadding(new Insets(10));
		t10.setPrefWidth(400);
		t10.setPromptText("Author Name");
		
		TextField t11 = new TextField();
		t11.setPadding(new Insets(10));
		t11.setPrefWidth(400);
		t11.setPromptText("Copy");
		
		TextField t12 = new TextField();
		t12.setPadding(new Insets(10));
		t12.setPrefWidth(400);
		t12.setPromptText("Shelf No");
		
		TextField t13 = new TextField();
		t13.setPadding(new Insets(10));
		t13.setPrefWidth(400);
		t13.setPromptText("Course Id");
		
		TextField t14 = new TextField();
		t14.setPadding(new Insets(10));
		t14.setPrefWidth(400);
		t14.setPromptText("Dept");
		
		Label selectbooks = new Label("Select Book");
		selectbooks.setPadding(new Insets(20,50,10,50));
		selectbooks.setTextFill(Color.web("#000"));
		selectbooks.setFont(Font.font("Arial BLACK", 18));
		
		Label bookdetails = new Label("Book Details");
		bookdetails.setPadding(new Insets(20,50,10,50));
		bookdetails.setTextFill(Color.web("#000"));
		bookdetails.setFont(Font.font("Arial BLACK", 18));
		
		//issue book
		Label l15 = new Label("Book Id: ");
		l15.setPadding(new Insets(10));
		l15.setTextFill(Color.web("#000"));
		l15.setFont(Font.font("Arial BLACK", 16));
		
		TextField t15 = new TextField();
		t15.setPadding(new Insets(10));
		t15.setPrefWidth(400);
		t15.setPromptText("Enter Book's Id");
		
		Label l16 = new Label("User Id: ");
		l16.setPadding(new Insets(10));
		l16.setTextFill(Color.web("#000"));
		l16.setFont(Font.font("Arial BLACK", 16));
		
		TextField t16 = new TextField();
		t16.setPadding(new Insets(10));
		t16.setPrefWidth(400);
		t16.setPromptText("Enter User's Id");
		
		Button issue = new Button("Issue");
		issue.setPadding(new Insets(10));
		issue.setPrefWidth(100);
		
		Button submit = new Button("Submit");
		submit.setPadding(new Insets(10));
		submit.setPrefWidth(100);
	
		
		//show users
		table.setEditable(true);
		
		TableColumn<User, String> idcol = new TableColumn<User, String>("Id");
		idcol.setMinWidth(100);
		idcol.setCellValueFactory(new PropertyValueFactory<User, String>("id"));
		
		TableColumn<User, String> fncol = new TableColumn<User, String>("First Name");
		fncol.setMinWidth(100);
		fncol.setCellValueFactory(new PropertyValueFactory<User, String>("firstname"));
		
		TableColumn<User, String> lncol = new TableColumn<User, String>("Last Name");
		lncol.setMinWidth(100);
		lncol.setCellValueFactory(new PropertyValueFactory<User, String>("lastname"));
		
		TableColumn<User, String> uncol = new TableColumn<User, String>("Username");
		uncol.setMinWidth(100);
		uncol.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
		
		TableColumn<User, String> passcol = new TableColumn<User, String>("Password");
		passcol.setMinWidth(100);
		passcol.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
		
		TableColumn<User, String> bbookcol = new TableColumn<User, String>("Borrowed Book");
		bbookcol.setMinWidth(200);
		bbookcol.setCellValueFactory(new PropertyValueFactory<User, String>("bbook"));
		
		TableColumn<User, String> contactnocol = new TableColumn<User, String>("Contact No");
		contactnocol.setMinWidth(200);
		contactnocol.setCellValueFactory(new PropertyValueFactory<User, String>("contactno"));
		
		TableColumn<User, String> emailcol = new TableColumn<User, String>("Email");
		emailcol.setMinWidth(200);
		emailcol.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
		
		
		table.getColumns().add(idcol);
		table.getColumns().add(fncol);
		table.getColumns().add(lncol);
		table.getColumns().add(uncol);
		table.getColumns().add(passcol);
		table.getColumns().add(contactnocol);
		table.getColumns().add(emailcol);
		table.getColumns().add(bbookcol);
		
		//show books
		table1.setEditable(true);
		
		TableColumn<Books, String> bidcol = new TableColumn<Books, String>("Book Id");
		bidcol.setMinWidth(100);
		bidcol.setCellValueFactory(new PropertyValueFactory<Books, String>("bookid"));
		
		TableColumn<Books, String> ncol = new TableColumn<Books, String>("Book Title");
		ncol.setMinWidth(300);
		ncol.setCellValueFactory(new PropertyValueFactory<Books, String>("bookname"));
		
		TableColumn<Books, String> acol = new TableColumn<Books, String>("Author");
		acol.setMinWidth(300);
		acol.setCellValueFactory(new PropertyValueFactory<Books, String>("author"));
		
		TableColumn<Books, String> ccol = new TableColumn<Books, String>("Copy");
		ccol.setMinWidth(100);
		ccol.setCellValueFactory(new PropertyValueFactory<Books, String>("copy"));
		
		TableColumn<Books, String> scol = new TableColumn<Books, String>("Shelf No");
		scol.setMinWidth(100);
		scol.setCellValueFactory(new PropertyValueFactory<Books, String>("shelfno"));
		
		TableColumn<Books, String> cidcol = new TableColumn<Books, String>("Course Id");
		cidcol.setMinWidth(100);
		cidcol.setCellValueFactory(new PropertyValueFactory<Books, String>("courseId"));
		
		TableColumn<Books, String> dcol = new TableColumn<Books, String>("Department");
		dcol.setMinWidth(100);
		dcol.setCellValueFactory(new PropertyValueFactory<Books, String>("dept"));
		
		
		table1.getColumns().add(bidcol);
		table1.getColumns().add(ncol);
		table1.getColumns().add(acol);
		table1.getColumns().add(ccol);
		table1.getColumns().add(scol);
		table1.getColumns().add(cidcol);
		table1.getColumns().add(dcol);
		
		
		//vbox hbox and panes...
		BorderPane root = new BorderPane();
		ScrollPane scrollPane = new ScrollPane();
		ScrollPane scrollPane1 = new ScrollPane();
		scrollPane.setContent(table);
		scrollPane1.setContent(table1);
		scrollPane.setPannable(true);
		scrollPane1.setPannable(true);
		scrollPane.setPrefSize(300, 300);
		scrollPane1.setPrefSize(300, 300);
		
		
		HBox h1 = new HBox();
		HBox h2 = new HBox();
		HBox h3 = new HBox();
		HBox h4 = new HBox();
		HBox h5 = new HBox();
		HBox h6 = new HBox();
		HBox h7 = new HBox();
		HBox h8 = new HBox();
		HBox h9 = new HBox();
		HBox h10 = new HBox();
		HBox h11 = new HBox();
		HBox h12 = new HBox();
		HBox h13 = new HBox();
		HBox h14 = new HBox();
		HBox h15 = new HBox();
		HBox h16 = new HBox();
		HBox h17 = new HBox();
		
		
		VBox v1 = new VBox();
		VBox v2 = new VBox();
		VBox v3 = new VBox();
		VBox v4 = new VBox();
		VBox v5 = new VBox();
		VBox v6 = new VBox();
		VBox v7 = new VBox();
		VBox v8 = new VBox();
		VBox v9 = new VBox();
		VBox v10 = new VBox();
		v3.setSpacing(5);
		v4.setSpacing(5);
		v5.setSpacing(5);
		v6.setSpacing(5);
		v9.setSpacing(5);
		v3.setPadding(new Insets(10,0,0,10));
		v4.setPadding(new Insets(10,0,0,10));
		v5.setPadding(new Insets(10,0,0,10));
		
		
		
		h1.setAlignment(Pos.CENTER);
		v1.setAlignment(Pos.BASELINE_CENTER);
		h1.setStyle("-fx-background-color: #336699;");
		v1.setStyle("-fx-background-color: #03a53e;");
		v2.setStyle("-fx-background-color: #FFFFFF;");
		VBox.setMargin(users, new Insets(10, 0, 0, 0));
		VBox.setMargin(books, new Insets(10, 0, 0, 0));
		VBox.setMargin(logout, new Insets(10, 0, 0, 0));
		VBox.setMargin(add_book, new Insets(10, 0, 0, 0));
		VBox.setMargin(delete_book, new Insets(10, 0, 0, 0));
		VBox.setMargin(issue_book, new Insets(10, 0, 0, 0));
		HBox.setMargin(add, new Insets(10, 0, 0, 200));
		HBox.setMargin(t1, new Insets(50, 0, 10, 0));
		VBox.setMargin(table, new Insets(10, 50, 0, 50));
		VBox.setMargin(scrollPane, new Insets(10, 50, 0, 50));
		VBox.setMargin(table1, new Insets(10, 50, 0, 50));
		VBox.setMargin(scrollPane1, new Insets(10, 50, 0, 50));
		VBox.setMargin(h2, new Insets(20, 50, 0, 50));
		VBox.setMargin(list, new Insets(10, 50, 50, 50));
		VBox.setMargin(t8, new Insets(10, 50, 0, 50));
		VBox.setMargin(t9, new Insets(10, 50, 0, 50));
		VBox.setMargin(t10, new Insets(10, 50, 0, 50));
		VBox.setMargin(t11, new Insets(10, 50, 0, 50));
		VBox.setMargin(t12, new Insets(10, 50, 0, 50));
		VBox.setMargin(t13, new Insets(10, 50, 0, 50));
		VBox.setMargin(t14, new Insets(10, 50, 0, 50));
		VBox.setMargin(h13, new Insets(10, 50, 0, 50));
		VBox.setMargin(h14, new Insets(50, 50, 10, 50));
		VBox.setMargin(h15, new Insets(10, 50, 10, 50));
		VBox.setMargin(h16, new Insets(10, 50, 10, 250));
		VBox.setMargin(h17, new Insets(20, 50, 0, 50));
		
		
		root.setTop(h1);
		root.setLeft(v1);
		root.setCenter(v2);
		
		//Adding all children
		
		h1.getChildren().add(l);
		h2.getChildren().addAll(l7,search);
		h3.getChildren().addAll(l8,t1);
		h4.getChildren().addAll(l9,t2);
		h5.getChildren().addAll(l10,t3);
		h6.getChildren().addAll(l10,t3);
		h7.getChildren().addAll(l11,t4);
		h8.getChildren().addAll(l12,t5);
		h9.getChildren().addAll(l13,t6);
		h10.getChildren().addAll(l14,t7);
		h11.getChildren().addAll(add);
		h12.getChildren().addAll(v7,v8);
		h13.getChildren().addAll(delete,update);
		h14.getChildren().addAll(l15,t15);
		h15.getChildren().addAll(l16,t16);
		h16.getChildren().addAll(issue,submit);
		h17.getChildren().addAll(l17,search_user);
		
		
		v1.getChildren().addAll(l2,profile,users,books,add_book,delete_book,issue_book,logout);
		v2.getChildren().addAll(l3,l4,l5,l6);
		v4.getChildren().addAll(table1,scrollPane1,h2);
		v3.getChildren().addAll(table,scrollPane,h17);
		v5.getChildren().addAll(h3,h4,h5,h6,h7,h8,h9,h10,h11);
		v6.getChildren().add(h12);
		v7.getChildren().addAll(selectbooks,list);
		v8.getChildren().addAll(bookdetails,t8,t9,t10,t11,t12,t13,t14,h13);
		v9.getChildren().addAll(h14,h15,h16);
		
		
		Scene s = new Scene(root,1200,600);
		primaryStage.setTitle("Library Mangement System");
		primaryStage.setScene(s);
		primaryStage.show();
		Admin.classStage = primaryStage ;
		
		
		//for listbook
		
		list.setMaxSize(300,550);
		list.setOnMouseClicked(e ->{
			try{
				String sql5="select * from books where bookname=?";
				st=conn.prepareStatement(sql5);
				st.setString(1, list.getSelectionModel().getSelectedItem());
				rs=st.executeQuery();
				
				while(rs.next()){
					t8.setText(rs.getString("bookid"));
					t9.setText(rs.getString("bookname"));
					t10.setText(rs.getString("author"));
					t11.setText(rs.getString("copy"));
					t12.setText(rs.getString("shelfno"));
					t13.setText(rs.getString("courseId"));
					t14.setText(rs.getString("dept"));
					bookname=rs.getString("bookname");
					
				}
				
				st.close();
				rs.close();
				
			}catch(Exception em){
				System.out.println(em);
			}
		});

		
		//button events
		
		profile.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
               root.setCenter(v2);
            }
        });
		
		users.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
               root.setCenter(v3);
			   load_users();
            }
        });
		
		books.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
				load_books();
               root.setCenter(v4);
            }
        });
		
		logout.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                Login log = new Login();
				log.start(Login.classStage);
				primaryStage.hide();
				
            }
        });
		
		add_book.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
               root.setCenter(v5);
            }
        });
		
		
		
		add.setOnAction(e->{
            
			
             Alert alert = new Alert(AlertType.INFORMATION); 
			   
			  
			 if(t1.getText().equals("") || t2.getText().equals("") || t3.getText().equals("") || t4.getText().equals("") || t5.getText().equals("") || t6.getText().equals("") || t7.getText().equals("")){
				 
				alert.setTitle("Warning Dialog");
				alert.setHeaderText("Fill up all the field!");
				alert.setContentText("");
				alert.showAndWait();
			 }else if(Pattern.matches("^(?=.*[0-9])[0-9]+$", t1.getText()) == false){
				alert.setTitle("Warning Dialog");
				alert.setHeaderText("Invalid Book Id!");
				alert.setContentText("Book Id can contains only number.");
				alert.showAndWait();
			 }else if(Pattern.matches("^(?=.*[A-Z])(?=.*[a-z])[A-Za-z.\\s]+$", t2.getText()) == false){
				alert.setTitle("Warning Dialog");
				alert.setHeaderText("Invalid Book Name!");
				alert.setContentText("Only uppercase and lowercase expected.");
				alert.showAndWait();
			 }else if(Pattern.matches("^(?=.*[A-Z])(?=.*[a-z])[A-Za-z.\\s]+$", t3.getText()) == false){
				alert.setTitle("Warning Dialog");
				alert.setHeaderText("Invalid Author Name!");
				alert.setContentText("Only uppercase and lowercase expected.");
				alert.showAndWait();
			 }else if(Pattern.matches("^(?=.*[0-9])[0-9]+$", t4.getText()) == false){
				alert.setTitle("Warning Dialog");
				alert.setHeaderText("Invalid No of Copies!");
				alert.setContentText("Only number expected.");
				alert.showAndWait();
			 }else if(Pattern.matches("^(?=.*[0-9])[0-9]+$", t6.getText()) == false){
				alert.setTitle("Warning Dialog");
				alert.setHeaderText("Invalid Course Id!");
				alert.setContentText("Only number expected.");
				alert.showAndWait();
			 }else if(Pattern.matches("^(?=.*[a-z])[a-z.\\s]+$", t7.getText()) == false){
				alert.setTitle("Warning Dialog");
				alert.setHeaderText("Invalid Department!");
				alert.setContentText("Only lowercase latter expected.");
				alert.showAndWait();
			 }else{
				 
				int bookid = Integer.parseInt(t1.getText());
				String bookname=t2.getText();
				String author=t3.getText();
				int copy=Integer.parseInt(t4.getText());
				String shelfno=t5.getText();
				int courseId=Integer.parseInt(t6.getText());
				String dept=t7.getText();
				 
				  try{
					String qu = "INSERT INTO books VALUES ("+ "'" + bookid + "',"+ "'" + bookname + "',"+ "'" + author + "',"+ "'" + copy + "',"+ "'" + shelfno + "',"+ "'" + courseId + "',"+ "'" + dept + "')";
				   st=conn.prepareStatement(qu);
				  int n=st.executeUpdate(qu);
				   if(n==0){
					alert.setTitle("Warning Dialog");
					alert.setHeaderText("Somthing went wrong try again!");
					alert.setContentText("");
					alert.showAndWait();
					
				   }
				   else{
					alert.setTitle("Message");
					alert.setHeaderText("New Book Added!");
					alert.setContentText("");
					
					Optional<ButtonType> result = alert.showAndWait();
						if (result.get() == ButtonType.OK){
							t1.setText("");
							t2.setText("");
							t3.setText("");
							t4.setText("");
							t5.setText("");
							t6.setText("");
							t7.setText("");
						}
				   }
				   st.close();
			   }catch(Exception ee){
				   System.out.println(ee);
			   }
			 }
	
        });
		
		
		delete_book.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
               root.setCenter(v6);
			   book_list_box();
            }
        });
		
		delete.setOnAction(e->{
			
			if(t8.getText().equals("")){
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Warning Dialog");
				alert.setHeaderText("Select Book First!");
				alert.setContentText("");
				alert.showAndWait();
				
			}else{
				try{
				String q = "delete from books where bookname=?";
				st=conn.prepareStatement(q);
				st.setString(1,t9.getText());
				st.executeUpdate();
				st.close();
				
			}catch(Exception ep){
				System.out.println(ep);
			}
			
			Alert alert = new Alert(AlertType.INFORMATION); 
			alert.setTitle("Message");
			alert.setHeaderText("Book deleted!");
			alert.setContentText("");
					
			Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK){
					t8.setText("");
					t9.setText("");
					t10.setText("");
					t11.setText("");
					t12.setText("");
					t13.setText("");
					t14.setText("");
					book_list_box();
					load_books();
				}
			}
				   
			
		});
		
		update.setOnAction(e->{
			if(t8.getText().equals("")){
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Warning Dialog");
				alert.setHeaderText("Select Book First!");
				alert.setContentText("");
				alert.showAndWait();
			}else{
				try{
				String sql6="update books set bookid=?, bookname=?, author=?, copy=?, shelfno=?, courseId=?, dept=? where bookname='"+bookname+"' ";
				st=conn.prepareStatement(sql6);
				st.setString(1,t8.getText());
				st.setString(2,t9.getText());
				st.setString(3,t10.getText());
				st.setString(4,t11.getText());
				st.setString(5,t12.getText());
				st.setString(6,t13.getText());
				st.setString(7,t14.getText());
				
				Alert alert = new Alert(AlertType.INFORMATION); 
				alert.setTitle("Message");
				alert.setHeaderText("Book updated!");
				alert.setContentText("");
				alert.showAndWait();
				st.execute();
				
				st.close();
				
			}catch(Exception eee){
				System.out.println(eee);
			}
			}
			
			
			
		});
		
		issue_book.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
               root.setCenter(v9);
			   book_list_box();
            }
        });
		
		
		issue.setOnAction(e->{
			String Id=t16.getText();
			String bId=t15.getText();
			String bookTitle;
			int copies;
			String borrowedbooks;
			Alert alert = new Alert(AlertType.INFORMATION); 
			
			try{
				String sql12="select * from users where id='"+Id+"' ";
				st=conn.prepareStatement(sql12);
				rs=st.executeQuery();
				while(rs.next()){
					borrowedbooks=rs.getString("bbook");
					try{
				
						String sql8="select * from books where bookid='"+bId+"' ";
						st=conn.prepareStatement(sql8);
						rs=st.executeQuery();
						while(rs.next()){
							bookTitle=rs.getString("bookname");
							copies=rs.getInt("copy");
							if(copies>0)
							{
								if(borrowedbooks.equals(""))
								{
									try{
									String sql7="update users set bbook=? where id='"+Id+"' ";
									st=conn.prepareStatement(sql7);
									st.setString(1,bookTitle);
									st.execute();
									
									}catch(Exception em){
									System.out.println(em);
									}
									
									copies=copies-1;
									try{
									String sql11="update books set copy=? where bookid='"+bId+"' ";
									st=conn.prepareStatement(sql11);
									st.setInt(1,copies);
									st.execute();
									
									}catch(Exception xx){
									System.out.println(xx);
									}
									alert.setTitle("Message");
									alert.setHeaderText("Success!");
									alert.setContentText("The book is successfully issued.");
									alert.showAndWait();
								}else{
									alert.setTitle("Message");
									alert.setHeaderText("The user already have a book to submit.");
									alert.setContentText("");
									alert.showAndWait();
								}
							}else
							{
								alert.setTitle("Message");
								alert.setHeaderText("Book is not available!");
								alert.setContentText("");
								alert.showAndWait();
							}
						}
				
					}catch(Exception emmm){
						System.out.println(emmm);
					}
				}
				rs.close();
				st.close();
			}catch(Exception en){
				System.out.println(en);
			}
			
			
		});
		
		submit.setOnAction(e->{
			String Id=t16.getText();
			String bId=t15.getText();
			int copies;
			String Bbook;
			Alert alert = new Alert(AlertType.INFORMATION); 
			try{
				String sql22="select * from users where id='"+Id+"' "; 
				st=conn.prepareStatement(sql22);
				rs=st.executeQuery();
				while(rs.next()){
					
					Bbook=rs.getString("bbook");
					if(!Bbook.equals("")){
						try{
							String sql13="select * from books where bookid='"+bId+"' ";
							st=conn.prepareStatement(sql13);
							rs=st.executeQuery();
							while(rs.next()){
								copies=rs.getInt("copy");
								copies=copies+1;
								try{
									String sql14="update books set copy=? where bookid='"+bId+"' ";
									st=conn.prepareStatement(sql14);
									st.setInt(1,copies);
									st.execute();
								}catch(Exception enn){
									System.out.println(enn);
								}
								
								try{
									String sql15="update users set bbook=? where id='"+Id+"' ";
									st=conn.prepareStatement(sql15);
									st.setString(1,"");
									st.execute();
								}catch(Exception ennn)
								{
									System.out.println(ennn);
								}
							}
							alert.setTitle("Message");
							alert.setHeaderText("Book is submitted successfully!");
							alert.setContentText("");
							alert.showAndWait();
						}catch(Exception el){
							System.out.println(el);
						}
					}else{
						alert.setTitle("Message");
						alert.setHeaderText("The user has no book to submit!");
						alert.setContentText("");
						alert.showAndWait();
					}
					
					
				}
			}catch(Exception ll){
				System.out.println(ll);
			}
			
		});
		
	
		
		
		//for searching user
		
		FilteredList<User> filteredusers = new FilteredList<>(data, e -> true);
		search_user.setOnKeyReleased(e ->{
			
			search_user.textProperty().addListener((observableValue, oldValue, newValue) ->{
				
				filteredusers.setPredicate((Predicate<? super User> ) user->{
					if(newValue==null || newValue.isEmpty()){
						return true;
					}
					String lowerCaseFilter = newValue.toLowerCase();
					if(user.getFirstname().toLowerCase().contains(lowerCaseFilter)){
						return true;
					}else if(user.getLastname().toLowerCase().contains(lowerCaseFilter)){
						return true;
					}
					return false;
				});
			});
			
			SortedList<User> sorteduser = new SortedList<>(filteredusers);
			sorteduser.comparatorProperty().bind(table.comparatorProperty());
			table.setItems(sorteduser);
		});
		
		//for searching book
		
		FilteredList<Books> filteredData = new FilteredList<>(data1, e -> true);
		search.setOnKeyReleased(e ->{
			
			search.textProperty().addListener((observableValue, oldValue, newValue) ->{
				
				filteredData.setPredicate((Predicate<? super Books> ) book->{
					if(newValue==null || newValue.isEmpty()){
						return true;
					}
					String lowerCaseFilter = newValue.toLowerCase();
					if(book.getBookname().toLowerCase().contains(lowerCaseFilter)){
						return true;
					}else if(book.getAuthor().toLowerCase().contains(lowerCaseFilter)){
						return true;
					}
					return false;
				});
			});
			
			SortedList<Books> sortedData = new SortedList<>(filteredData);
			sortedData.comparatorProperty().bind(table1.comparatorProperty());
			table1.setItems(sortedData);
		});
		
	
	}
	
	
}