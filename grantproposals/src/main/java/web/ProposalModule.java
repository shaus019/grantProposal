/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;
import dao.ProposalDAO;
import dao.ProposalJdbcDAO;
import domain.Proposal;
import org.jooby.Jooby;
import org.jooby.Result;
import org.jooby.Status;
 
/**
 *
 * @author thomasroff
 */
public class ProposalModule extends Jooby {
    
    ProposalDAO proposalDao = new ProposalJdbcDAO();
    
    public ProposalModule(ProposalDAO proposalDao) {
         
        this.proposalDao = proposalDao;
        
        post("/api/proposal", (req, rsp) -> {
           Proposal proposal = req.body().to(Proposal.class);
           proposalDao.save(proposal.getEncodedProposal(), proposal.getApplicationId()); 
           rsp.status(Status.CREATED);
        });
        
        
        get("/api/proposal/:applicationId", (req) -> {
            Integer applicationId = Integer.parseInt(req.param("applicationId").value());
            String proposal = proposalDao.getProposal(applicationId);
            if(proposal == null) {
                return new Result().status(Status.NOT_FOUND);
            } else {
                return proposal;
            }
        });
    
    
    }
}
