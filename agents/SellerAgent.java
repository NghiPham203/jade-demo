package agents;

import db.DatabaseHelper;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.core.behaviours.CyclicBehaviour;
import org.json.JSONObject;
import ui.MarketUI;

public class SellerAgent extends Agent {
    @Override
    protected void setup() {
        addBehaviour(new CyclicBehaviour() {
            public void action() {
                ACLMessage msg = receive();
                if (msg != null) {
                    try {
                        JSONObject obj = new JSONObject(msg.getContent());
                        int price = obj.getInt("price");
                        int threshold = obj.getInt("threshold");
                        String product = obj.getString("product");
                        String buyer = obj.getString("from");

                        String reply = price >= threshold
                            ? "✅ Agree sell " + product + " with " + price
                            : "❌ Reject, too lower price (" + price + " < " + threshold + ")";

                        MarketUI.addLog("🧑‍💼 Seller response: " + reply);
                        DatabaseHelper.insertLog("seller", buyer, reply);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    block();
                }
            }
        });
    }
}
