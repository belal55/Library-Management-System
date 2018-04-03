package User;
import Main.*;
import Admin.*;
import DbConnector.*;
import Books.*;
import Login.*;

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
import java.util.*;
import javafx.collections.transformation.*;
import java.util.function.*;

/**
* Library Mangement System Using JavaFx.

* @author  Md. Belal Hosen
* @author  Md. Mahadi Sazzadur Rahman
* @version 1.0
* @since   2016-12-27 
*/
public class Welcome extends Application{
	Connection conn = DbConnect.connectdb();
	PreparedStatement st = null;
	ResultSet rs = null;
	String fname,lname,uname,password,contactno,email,borrowedbook;
	
	public TableView<Books> table = new TableView<Books>();
	ObservableList<Books> data = FXCollections.observableArrayList();
    public static Stage classStage = new Stage();
	
	public void setuname(String name){
		this.uname=name;
	}
	public void setpass(String pass){
		this.password=pass;
	}	
	public void loadusersinfo(){
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
					borrowedbook=rs.getString("bbook");
					contactno=rs.getString("contactno");
					email=rs.getString("email");
				
	
				}
				else{
					System.out.println("unseuccess");
				}
			}catch(Exception ex){
				System.out.println("error: "+ex);
			}
	}
	
	public void loadbooks(){
		data.clear();
		String sql2 = "select * from books";
		try{
			st=conn.prepareStatement(sql2);
			rs=st.executeQuery(sql2);
			while(rs.next()){
				data.add(
				new Books(
					rs.getInt("bookid"),
					rs.getString("bookname"),
					rs.getString("author"),
					rs.getInt("copy"),
					rs.getString("shelfno"),
					rs.getInt("courseId"),
					rs.getString("dept")
				 
				));
				table.setItems(data);}
			}catch(Exception exc){
				System.out.println(exc);
			}
	}
	
	public void start(Stage primaryStage)
	{
		Login log = new Login();
		loadusersinfo();
		
		//welcome labels
		Label l = new Label("Welcome "+uname);
		l.setPadding(new Insets(50));
		l.setTextFill(Color.web("#FFF"));
		l.setFont(Font.font("Arial", 16));
		l.setTextAlignment(TextAlignment.RIGHT);
		
		//menu
		Label l2 = new Label("Menu");
		l2.setPadding(new Insets(50,50,20,50));
		l2.setTextFill(Color.web("#FFF"));
		l2.setFont(Font.font("Arial BLACK", 16));
		l2.setTextAlignment(TextAlignment.CENTER);
		
		Button profile = new Button("Profile");
		profile.setPadding(new Insets(10,0,10,0));
		profile.setPrefWidth(130.0);
		profile.setTextFill(Color.web("#000"));
		
		Button books = new Button("Books");
		books.setPadding(new Insets(10,0,10,0));
		books.setPrefWidth(130.0);
		books.setTextFill(Color.web("#000"));
		
		Button logout = new Button("Logout");
		logout.setPadding(new Insets(10,0,10,0));
		logout.setPrefWidth(130.0);
		logout.setTextFill(Color.web("#000"));
		
		//users information
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
		
		Label l8 = new Label("Borrowed Book: "+borrowedbook);
		l8.setPadding(new Insets(10,10,10,100));
		l8.setTextFill(Color.web("#000"));
		l8.setFont(Font.font("Arial BLACK", 16));
		l8.setTextAlignment(TextAlignment.CENTER);
		
		//for search
		Label l7 = new Label("Search Book: ");
		l7.setPadding(new Insets(10,10,20,100));
		l7.setFont(Font.font("Arial", 16));
		
		TextField search = new TextField();
		search.setPadding(new Insets(10));
		search.setPrefWidth(500);
		search.setPromptText("Enter book's name or author's name...");
	
		// show books
		table.setEditable(true);
		TableColumn<Books, String> idcol = new TableColumn<Books, String>("Book Id");
		idcol.setMinWidth(100);
		idcol.setCellValueFactory(new PropertyValueFactory<Books, String>("bookid"));
		
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
		
		table.getColumns().add(idcol);
		table.getColumns().add(ncol);
		table.getColumns().add(acol);
		table.getColumns().add(ccol);
		table.getColumns().add(scol);
		table.getColumns().add(cidcol);
		table.getColumns().add(dcol);
		
		//vbox, hbox, panes
		BorderPane root = new BorderPane();
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(table);
		scrollPane.setPannable(true);
		scrollPane.setPrefSize(300, 300);
		HBox h1 = new HBox();
		HBox h2 = new HBox();
		VBox v1 = new VBox();
		VBox v2 = new VBox();
		VBox v3 = new VBox();
		v3.setSpacing(5);
		v3.setPadding(new Insets(10,0,0,10));
		
		h1.setAlignment(Pos.CENTER);
		v1.setAlignment(Pos.BASELINE_CENTER);
		h1.setStyle("-fx-background-color: #336699;");
		
		v1.setStyle("-fx-background-color: #03a53e;");
		v2.setStyle("-fx-background-color: #FFFFFF;");
		v1.setMargin(books, new Insets(10, 0, 0, 0));
		v1.setMargin(logout, new Insets(10, 0, 0, 0));
		v3.setMargin(table, new Insets(10, 50, 0, 50));
		v3.setMargin(scrollPane, new Insets(10, 50, 0, 50));
		v3.setMargin(h2, new Insets(20, 50, 0, 50));
	
		root.setCenter(v2);
		root.setTop(h1);
		root.setLeft(v1);
		
		h1.getChildren().add(l);
		h2.getChildren().addAll(l7,search);
		v1.getChildren().addAll(l2,profile,books,logout);
		v2.getChildren().addAll(l3,l4,l5,l6,l8);
		v3.getChildren().addAll(table,scrollPane,h2);
		
		Scene s = new Scene(root,1200,600);
		primaryStage.setTitle("Library Mangement System");
		primaryStage.setScene(s);
		primaryStage.show();
		Welcome.classStage = primaryStage ;
		
		// Buttons events
		profile.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
               root.setCenter(v2);
            }
        });
		books.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
               root.setCenter(v3);
			   loadbooks();
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
		
		//for searching books
		FilteredList<Books> filteredData = new FilteredList<>(data, e -> true);
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
			sortedData.comparatorProperty().bind(table.comparatorProperty());
			table.setItems(sortedData);
		});
	}
}