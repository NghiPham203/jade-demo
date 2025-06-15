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

        Button sendBtn = new Button("Gửi giao dịch");
        sendBtn.setOnAction(e -> {
            String json = String.format(
                "{\"from\":\"%s\", \"to\":\"%s\", \"price\":%s, \"product\":\"%s\", \"note\":\"%s\", \"orderId\":\"%s\", \"threshold\":%s}",
                fromInput.getText(), toInput.getText(), priceInput.getText(),
                productDropdown.getValue(), noteInput.getText(), orderIdInput.getText(), thresholdInput.getText()
            );
            BuyerAgent.sendJson(json);
        });

        VBox layout = new VBox(10,
            new Label("Người gửi:"), fromInput,
            new Label("Người nhận:"), toInput,
            new Label("Sản phẩm:"), productDropdown,
            new Label("Giá đề xuất:"), priceInput,
            new Label("Ngưỡng giá chấp nhận (threshold):"), thresholdInput,
            new Label("Ghi chú:"), noteInput,
            new Label("Mã đơn hàng:"), orderIdInput,
            sendBtn,
            new Label("Lịch sử giao dịch:"),
            logArea
        );
        layout.setPadding(new Insets(10));
        stage.setScene(new Scene(layout, 500, 600));
        stage.setTitle("JADE Market UI");
        stage.show();
    }

    public static void addLog(String msg) {
        Platform.runLater(() -> logArea.appendText(msg + "\n"));
    }
}
