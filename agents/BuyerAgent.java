
package agents;

import db.DatabaseHelper;
import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import ui.MarketUI;

public class BuyerAgent extends Agent {
    private static BuyerAgent instance;

    @Override
    protected void setup() {
        instance = this;
        MarketUI.addLog(getLocalName() + " đã sẵn sàng.");
    }

    public static void sendPrice(String price) {
        if (instance != null) {
            ACLMessage msg = new ACLMessage(ACLMessage.PROPOSE);
            msg.setContent(price);
            msg.addReceiver(new AID("seller", AID.ISLOCALNAME));
            instance.send(msg);

            MarketUI.addLog(instance.getLocalName() + " gửi giá: " + price);
            DatabaseHelper.insertLog(instance.getLocalName(), "seller", "Gửi giá: " + price);
        }
    }
}
