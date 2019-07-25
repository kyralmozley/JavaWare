package RansomeWare;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


public class TakeOver extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaWare");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setStyle("-fx-background-color:#ff0000; -fx-opacity:1;");
        Scene scene = new Scene(grid, 800, 600);
        primaryStage.setScene(scene);

        Text scenetitle = new Text("Your files have been encrypted!");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 40));
        scenetitle.setTextAlignment(TextAlignment.CENTER);
        grid.add(scenetitle, 0,1);

        Image lock = new Image("File:C:\\Users\\Admin\\IdeaProjects\\JavaWare\\src\\RansomeWare\\lock.png");
        ImageView lockView = new ImageView();
        lockView.setFitHeight(100);
        lockView.setFitWidth(100);
        lockView.setImage(lock);

        grid.add(lockView, 0, 0);

        grid.setAlignment(Pos.CENTER);
        primaryStage.show();
    }
}
