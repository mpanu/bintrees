package bintrees;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        ScrollPane sp = new ScrollPane();
        Group root = new Group();
        sp.setContent(root);

        primaryStage.setTitle("Proper trees");
        primaryStage.setScene(new Scene(sp, 800, 800));


        // Generate trees, print tree-count
        int TREE_HEIGHT = 3;
        ProperTrees ptrees = new ProperTrees(TREE_HEIGHT);
        System.out.println(ptrees.getR().size());

        // Visualize trees
        if(TREE_HEIGHT < 5) {
            root.getChildren().add(new TreeDrawer(ptrees.getR()));
            primaryStage.show();
        }
        else
            primaryStage.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}



