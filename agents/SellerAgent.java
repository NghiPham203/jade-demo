
package agents;

import db.DatabaseHelper;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import ui.MarketUI;

public class SellerAgent extends Agent {
    @Override
    protected void setup() {
        MarketUI.addLog("Seller đã sẵn sàng.");
        addBehaviour(new jade.core.behaviours.CyclicBehaviour() {
            public void action() {
                ACLMessage msg = receive(MessageTemplate.MatchPerformative(ACLMessage.PROPOSE));
                if (msg != null) {
                    String content = msg.getContent();
                    String replyText;
                    try {
                        int price = Integer.parseInt(content);
                        replyText = (price >= 95000) ? "Đồng ý bán" : "Từ chối, giá thấp";
                    } catch (Exception e) {
                        replyText = "Giá không hợp lệ";
                    }

                    MarketUI.addLog("Seller phản hồi: " + replyText);
                    DatabaseHelper.insertLog("seller", msg.getSender().getLocalName(), replyText);
                } else {
                    block();
                }
            }
        });
    }
}
