/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.ApplicantJdbcDAO;
import dao.ApplicantDAO;
import domain.Applicant;
import org.jooby.Jooby;
import org.jooby.Status;
import dao.*;
import org.jooby.Result;


/**
 *
 * @author rofth173
 */
public class ApplicantModule extends Jooby {
    
    ApplicantDAO applicantDao = new ApplicantJdbcDAO();
    
     public ApplicantModule(ApplicantDAO dao) {
        get("/api/applicant/:username", (req) -> {
            String username = req.param("username").value();
            String password = req.param("password").value();
            Applicant applicant = applicantDao.getApplicant(username,password);
            if(applicant == null) {
                return new Result().status(Status.NOT_FOUND);
            } else {
                return applicantDao.getApplicant(username, password);
            }
        });
        
        post("/api/register", (req, rsp) -> {
            Applicant applicant = req.body().to(Applicant.class);
            applicantDao.save(applicant);
            rsp.status(Status.CREATED);
        });
        
    }
}
