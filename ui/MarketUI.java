
package ui;

import agents.BuyerAgent;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MarketUI extends Application {
    private static ListView<String> logList;

    public static void addLog(String message) {
        Platform.runLater(() -> logList.getItems().add(message));
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JADE Market UI");

        logList = new ListView<>();
        TextField priceInput = new TextField();
        priceInput.setPromptText("Nhập giá đề xuất");

        Button sendBtn = new Button("Gửi giá");
        sendBtn.setOnAction(e -> {
            String price = priceInput.getText();
            if (!price.isEmpty()) {
                BuyerAgent.sendPrice(price);
                priceInput.clear();
            }
        });

        VBox root = new VBox(10, priceInput, sendBtn, logList);
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.show();
    }
}
