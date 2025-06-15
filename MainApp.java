
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
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            // Start JADE
            Runtime rt = Runtime.instance();
            Profile p = new ProfileImpl();
            AgentContainer container = rt.createMainContainer(p);

            container.createNewAgent("seller", SellerAgent.class.getName(), null).start();
            container.createNewAgent("buyer1", BuyerAgent.class.getName(), null).start();

            // Start JavaFX UI
            MarketUI ui = new MarketUI();
            ui.start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
