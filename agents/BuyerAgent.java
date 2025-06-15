package agents;

import db.DatabaseHelper;
import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import ui.MarketUI;

public class BuyerAgent extends Agent {
    static BuyerAgent instance;

    @Override
    protected void setup() {
        instance = this;
    }

    public static void sendJson(String json) {
        if (instance != null) {
            ACLMessage msg = new ACLMessage(ACLMessage.PROPOSE);
            msg.setContent(json);
            msg.addReceiver(new AID("seller", AID.ISLOCALNAME));
            instance.send(msg);

            MarketUI.addLog("👤 Buyer gửi: " + json);
            DatabaseHelper.insertLog("buyer", "seller", json);
        }
    }
}
