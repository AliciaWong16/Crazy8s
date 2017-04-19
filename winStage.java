import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Scanner;
import java.io.*;
import javafx.scene.text.Text;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.geometry.Insets;

public class winStage extends Application {
		 @Override //tell the compiler that you are overriding the start
		 //method in the Application class
		 public void start(Stage primaryStage) throws IOException{
		 GridPane pane = new GridPane();
		 pane.setPadding(new Insets(20,20,20,20));
		 Scene scene = new Scene(pane, 500, 300);
		 pane.setHgap(20);
		 pane.setVgap(10);
		 File winners = new File("winnersP.txt");
		 Scanner win = new Scanner(winners);//new Scanner to read file
		 Label name = new Label("Name:");
		 pane.add(name, 0, 0);
		 Label wins = new Label("# of Wins:");
		 pane.add(wins, 2, 0);
		 for(int i = 1; win.hasNext(); i++){
			 Text nameN = new Text(win.nextLine());
			 pane.add(nameN, 0, i);
			 Text winsN = new Text(win.nextLine());
			 pane.add(winsN, 2, i);
		 }
		 
		 primaryStage.setTitle("Winners!");
		 primaryStage.setScene(scene);
		 primaryStage.show();
		 
		 }
		 
		 
		 public static void main(String[] args){
		 Application.launch(args);
		 }

}
