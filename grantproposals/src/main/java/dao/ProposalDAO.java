/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
 
/**
 *
 * @author thomasroff
 */
public interface ProposalDAO {
  
    String getProposal(Integer applicationId);
    void save(String proposal, String applicationId);
   
}
