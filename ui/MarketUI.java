package ui;

import agents.BuyerAgent;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MarketUI {
    private static TextArea logArea = new TextArea();

    public void start(Stage stage) {
        TextField fromInput = new TextField("buyer");
        TextField toInput = new TextField("seller");
        ComboBox<String> productDropdown = new ComboBox<>(FXCollections.observableArrayList(
            "Laptop", "Smartphone", "Tablet", "TV", "Headphone"
        ));
        productDropdown.getSelectionModel().selectFirst();

        TextField priceInput = new TextField();
        TextField thresholdInput = new TextField("95000");
        TextField noteInput = new TextField();
        TextField orderIdInput = new TextField();

        Button sendBtn = new Button("Send transaction");
        sendBtn.setOnAction(e -> {
            String json = String.format(
                "{\"from\":\"%s\", \"to\":\"%s\", \"price\":%s, \"product\":\"%s\", \"note\":\"%s\", \"orderId\":\"%s\", \"threshold\":%s}",
                fromInput.getText(), toInput.getText(), priceInput.getText(),
                productDropdown.getValue(), noteInput.getText(), orderIdInput.getText(), thresholdInput.getText()
            );
            BuyerAgent.sendJson(json);
        });

        VBox layout = new VBox(10,
            new Label("Sender:"), fromInput,
            new Label("Receiver:"), toInput,
            new Label("Products:"), productDropdown,
            new Label("Suggested price:"), priceInput,
            new Label("Threshold:"), thresholdInput,
            new Label("Note:"), noteInput,
            new Label("Order code:"), orderIdInput,
            sendBtn,
            new Label("Transaction history:"),
            logArea
        );
        layout.setPadding(new Insets(10));
        stage.setScene(new Scene(layout, 500, 800));
        stage.setTitle("JADE Market UI");
        stage.show();
    }

    public static void addLog(String msg) {
        Platform.runLater(() -> logArea.appendText(msg + "\n"));
    }
}
