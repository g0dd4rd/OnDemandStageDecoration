package cz.dredwerkz.ondemandstagedecoration;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Jiri Prajzner <jprajzne@redhat.com>
 */
public class OnDemandStageDecoration extends Application {
  private final String hw = "Hello, world!";
  private final String huw = "Hello, undecorated World!";
  private final String hdw = "Hello, decorated World!";
  
  @Override
  public void start(Stage stage) {
    Button btn = new Button();
    btn.setText("Say "+ hw);
    btn.setOnAction(eh -> {
        System.out.println(hw);
    });

    StackPane root = new StackPane();
    root.getChildren().add(btn);

    Stage stageDecorated = new Stage(StageStyle.DECORATED);
    Scene scene = new Scene(root, 300, 250);
    scene.setOnKeyPressed(kp -> {
      if (kp.isAltDown()) {
        System.out.println("Alt pressed. "+ hdw);
        stage.hide();
        stage.setScene(null);
        stageDecorated.setTitle(hdw);
        stageDecorated.setScene(scene);
        stageDecorated.setX(stage.getX());
        stageDecorated.setY(stage.getY());
        stageDecorated.show();
      }
    });

    scene.setOnKeyReleased(kr -> {
      if (!kr.isAltDown()) {
        System.out.println("Alt released. "+ huw);
        stageDecorated.hide();
        stageDecorated.setScene(null);
        stage.setScene(scene);
        stage.setX(stageDecorated.getX());
        stage.setY(stageDecorated.getY());
        stage.show();
      }
    });

    stage.setTitle(huw);
    stage.setScene(scene);
    stage.initStyle(StageStyle.UNDECORATED);
    stage.show();
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }

}
