/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author thomasroff
 */
public class Proposal {
    private String encodedProposal;
    private String applicationId;

    public Proposal(String encodedProposal, String applicationId) {
        this.encodedProposal = encodedProposal;
        this.applicationId = applicationId;
    }

    public String getEncodedProposal() {
        return encodedProposal;
    }

    public void setEncodedProposal(String encodedProposal) {
        this.encodedProposal = encodedProposal;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    @Override
    public String toString() {
        return "Proposal{" + "encodedProposal=" + encodedProposal + ", applicationId=" + applicationId + '}';
    }
    
    
}
