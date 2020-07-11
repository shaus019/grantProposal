/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;
 
import java.util.concurrent.CompletableFuture;
import org.jooby.Jooby;
import org.jooby.json.Gzon;
import dao.*;
 
/**
 *
 * @author rofth173
 */
public class Server extends Jooby {
    
    EvaluatorDAO evaluatorDao = new EvaluatorJdbcDAO();
    ApplicantDAO applicantDao = new ApplicantJdbcDAO();
    ApplicationDAO applicationDao = new ApplicationJdbcDAO();
    GrantDAO grantDAO = new GrantJdbcDAO();
    ReceiptDAO receiptDAO = new ReceiptJdbcDAO();
    ProposalDAO proposalDAO = new ProposalJdbcDAO();
    
    public Server() {
        port(8080);
        use(new Gzon());
        use(new ApplicantModule(applicantDao));
        use(new EvaluatorModule(evaluatorDao));
        use(new ApplicationModule(applicationDao));
        use(new GrantModule(grantDAO));
        use(new ReceiptModule(receiptDAO));
        use(new ProposalModule(proposalDAO));
        use(new AssetModule());
    }
    
    public static void main(String[] args) throws Exception {
        System.out.println("\nStarting Server.");
        Server server = new Server();
        
        CompletableFuture.runAsync(() -> {
            server.start();
        });
        
        server.onStarted(() -> {
            System.out.println("\nPress Enter to stop the server.");
        });
        
        // wait for user to hit the Enter key
        System.in.read();
        System.exit(0);
    }   
}
