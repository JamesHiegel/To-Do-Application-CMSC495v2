import static java.util.Collections.list;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 *
 * @author coryl
 */
public class YETI extends Application {
    
     ObservableList<String> data = FXCollections.observableArrayList(
            "chocolate", "salmon", "gold", "coral", "darkorchid",
            "darkgoldenrod", "lightsalmon", "black", "rosybrown", "blue",
            "blueviolet", "brown");
    
    @Override
    public void start(Stage primaryStage) {
        
        DatePicker datePicker = new DatePicker();
        ListView listView = new ListView();
        final TextField textField = new TextField();
        textField.setPromptText("Input new item to add to list here");
                
        Button addBtn = new Button();
        addBtn.setText("Add Item");
        listView.setItems(data);
        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                if (textField.getText().equals("")) System.out.println("Input field blank");
                else {
                    data.add(textField.getText());
                    System.out.println("Added Item to List");
                    listView.setItems(data);
                }
            }
        });
        
        SplitPane root = new SplitPane();
        root.setDividerPositions(0.1);
        root.setOrientation(Orientation.VERTICAL);
        root.getItems().add(new FlowPane(addBtn, datePicker, textField));
        root.getItems().add(listView);
        
        Scene scene = new Scene(root);
        primaryStage.setTitle("YETI by SAD Software, Inc.");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}