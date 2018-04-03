package Login;
import User.*;
import Admin.*;
import DbConnector.*;
import Books.*;
import Main.*;

import javafx.application.Application;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.paint.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.geometry.*;
import java.util.*;
import java.sql.*;

/**
* Library Mangement System Using JavaFx.

* @author  Md. Belal Hosen
* @author  Md. Mahadi Sazzadur Rahman
* @version 1.0
* @since   2016-12-27 
*/

public class Login extends Application 
{	
	Connection conn = DbConnect.connectdb();
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	public static Stage classStage = new Stage();

	@Override
	public void start(Stage primaryStage)
	{
		//labels and textfields
		Text t = new Text("Welcome To Our Library");
		t.setFont(Font.font("Arial Black", 24.0));
		t.setFill(Color.WHITE);
		
		Label l1 = new Label("User Name: ");
		l1.setTextFill(Color.web("#FFF"));
		l1.setFont(Font.font("Arial", 16));
		l1.setPadding(new Insets(10));
		
		TextField t1 = new TextField();
		t1.setPromptText("Your Username");
		t1.selectAll();
		t1.setPadding(new Insets(10,10,10,50));
		
		Label l2 = new Label("Password: ");
		l2.setTextFill(Color.web("#FFF"));
		l2.setFont(Font.font("Arial", 16));
		l2.setPadding(new Insets(10));
		
		PasswordField p = new PasswordField();
		p.setPromptText("Your Password");
		p.setPadding(new Insets(10,10,10,50));
		
		//buttons
		Button reset = new Button("Reset");
		reset.setPadding(new Insets(10,50,10,50));
		reset.setStyle("-fx-font-size: 14px; ");
		
		Button login = new Button("Login");
		login.setStyle("-fx-font-size: 14px; ");
		login.setPadding(new Insets(10,50,10,50));
	
		VBox root = new VBox();
		HBox h = new HBox();
		HBox h1 = new HBox();
		HBox h2 = new HBox();
		HBox h3 = new HBox();
		h3.setSpacing(35.0);
		
		h.setPadding(new Insets(100,100,10,100));
		h1.setPadding(new Insets(50,100,10,100));
		h2.setPadding(new Insets(20,100,10,110));
		h3.setPadding(new Insets(20,80,10,80));

		h.getChildren().addAll(t);
		h1.getChildren().addAll(l1,t1);
		h2.getChildren().addAll(l2,p);
		h3.getChildren().addAll(reset,login);
		
		h.setAlignment(Pos.CENTER);
		h1.setAlignment(Pos.CENTER);
		h2.setAlignment(Pos.CENTER);
		h3.setAlignment(Pos.CENTER);

		root.getChildren().addAll(h,h1,h2,h3);
		
		Scene s = new Scene(root,1200,600);
		primaryStage.setTitle("Library Mangement System");
		primaryStage.setScene(s);
		primaryStage.show();
		Login.classStage = primaryStage ;
		
		String image = Login.class.getResource("../image.jpg").toExternalForm();
		root.setStyle("-fx-background-image: url('" + image + "'); " +"-fx-background-position: center; " +"-fx-background-repeat: stretch;" + "-fx-background-size: cover;");
	
		//button events
		reset.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                t1.setText("");
				p.setText("");
            }
        });
		
		login.setOnAction(new EventHandler<ActionEvent>() {
            
           
			@Override
            public void handle(ActionEvent event) {
                
			String sql = "select * from users where uname=? and  password=?";
			try{
				pst=conn.prepareStatement(sql);
				pst.setString(1,t1.getText());
				pst.setString(2,p.getText());
				rs=pst.executeQuery();
				if(t1.getText().equals("admin") && p.getText().equals("admin123")){
					Admin a = new Admin();
					a.setuname(t1.getText());
					a.setpass(p.getText());
					a.start(Admin.classStage);
					primaryStage.hide();
				}
				else if(rs.next()){
					Welcome l = new Welcome();
					l.setuname(t1.getText());
					l.setpass(p.getText());
					l.start(Welcome.classStage);
					
					primaryStage.hide();
				}
				else{
					System.out.println("unseuccess");
				}
				pst.close();
				rs.close();
			}catch(Exception ex){
				System.out.println("error: "+ex);
			}
					
				
            }
        });
	}
	
}