/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package web;
import org.jooby.Jooby;
import org.jooby.Status;
import dao.*;
import domain.*;
import domain.Application;
import org.jooby.Result;
import java.util.concurrent.CompletableFuture;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author thomasroff
 */
public class ApplicationModule extends Jooby {
    
     ApplicationDAO applicationDao = new ApplicationJdbcDAO();
    
     public ApplicationModule(ApplicationDAO applicationDao) {
         
         this.applicationDao = applicationDao;
         
        get("/api/application/:id", (req) -> {
            String id = req.param("id").value();
            Application application = applicationDao.getApplication(id);
            if(application == null) {
                return new Result().status(Status.NOT_FOUND);
            } else {
                return applicationDao.getApplication(id);
            }
        });
        
        get("/api/application_form", () -> applicationDao.getAllApplications());
        
        get("/api/application_form/:grantId", (req) -> {
         String grantId = req.param("grantId").value();
         return applicationDao.getApplicationsByGrantId(grantId);
         
     });
        
        
        post("/api/application_form", (req, rsp) -> {
            Application application = req.body().to(Application.class);
            applicationDao.save(application);
            rsp.status(Status.CREATED);
            CompletableFuture.runAsync(() -> {
                 try {
                    sendConfirmationEmail(application);
                }catch(EmailException e) {
                    e.printStackTrace();
                    rsp.status(Status.SERVER_ERROR);
                }
            }); 
       
        });
        
        
   
    }
     
     public void sendConfirmationEmail(Application a) throws EmailException {
            Applicant applicant = a.getApplicant();
            System.out.println("Sending email");
            Email email = new SimpleEmail();
            email.setHostName("localhost");
            email.setSmtpPort(2525);
            email.setFrom("grantmanagement@otago.com");
            email.setSubject("Thank you for your application");
            email.setMsg("test");
            email.addTo(applicant.getEmail());
            email.send();
        }
}




