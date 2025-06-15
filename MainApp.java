
import agents.BuyerAgent;
import agents.SellerAgent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.core.Runtime;
import javafx.application.Application;
import javafx.stage.Stage;
import ui.MarketUI;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        MarketUI ui = new MarketUI();
        ui.start(primaryStage);
    }

    public static void main(String[] args) {
        jade.Boot.main(new String[]{"-gui", "buyer:BuyerAgent; seller:SellerAgent"});
        launch(args);
    }
}
