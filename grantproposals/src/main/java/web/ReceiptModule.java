/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.ReceiptDAO;
import dao.ReceiptJdbcDAO;
import dao.*;
import domain.Applicant;
import domain.Receipt;
import java.util.concurrent.CompletableFuture;
import org.apache.commons.mail.EmailException;
import org.jooby.Jooby;
import org.jooby.Status;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author villa
 */
public class ReceiptModule extends Jooby {
    
    ReceiptDAO receiptDao = new ReceiptJdbcDAO();

    public ReceiptModule(ReceiptDAO receiptDao) {
        
        this.receiptDao = receiptDao;
    
    
    post("/api/receipts", (req, rsp) -> {
            Receipt receipt = req.body().to(Receipt.class);
            receiptDao.save(receipt);
            rsp.status(Status.CREATED);
            CompletableFuture.runAsync(() -> {
                 try {
                    sendConfirmationEmail(receipt);
                }catch(EmailException e) {
                    e.printStackTrace();
                    rsp.status(Status.SERVER_ERROR);
                }
            }); 
       
        });
    
    
}

public void sendConfirmationEmail(Receipt a) throws EmailException {
            Applicant applicant = a.getApplication().getApplicant();
            System.out.println("Sending email");
            Email email = new SimpleEmail();
            email.setHostName("localhost");
            email.setSmtpPort(2525);
            email.setFrom("grantmanagement@otago.com");
            email.setSubject("Decision on your Grant Proposal Application");
            email.setMsg("test");
            email.addTo(applicant.getEmail());
            email.send();
        }
}





