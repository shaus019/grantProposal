/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.Clob;
import java.util.ArrayList;

/**
 *
 * @author Gorilla Rig
 */

public class Application {
    private String applicationId;
    private Grant grant;
    private Applicant applicant;
    private String status;
    private String date;
    private String projectTitle;
    private String projectAbstract;
    private String description;
    private String uniqueness;
    private String undertakenWork; 
    private String conceptProof;
    private String soldOrUsed;
    private String opinion;
    private String whyBuy;
    private String estimation;
            
//     

//    public Application(String applicationId, Grant grant, Applicant applicant, ArrayList<Evaluator> evaluators, String status, String date, String projectTitle, String projectAbstract, String description, String uniqueness, String undertakenWork, String conceptProof, String soldOrUsed, String opinion, String whyBuy, String estimation) {
//        this.applicationId = applicationId;
//        this.grant = grant;
//        this.applicant = applicant;
//        this.evaluators = evaluators;
//        this.status = status;
//        this.date = date;
//        this.projectTitle = projectTitle;
//        this.projectAbstract = projectAbstract;
//        this.description = description;
//        this.uniqueness = uniqueness;
//        this.undertakenWork = undertakenWork;
//        this.conceptProof = conceptProof;
//        this.soldOrUsed = soldOrUsed;
//        this.opinion = opinion;
//        this.whyBuy = whyBuy;
//        this.estimation = estimation;
//    }
    
        public Application(String applicationId, Grant grant, Applicant applicant,String date, String projectTitle, String projectAbstract, String description, String uniqueness, String undertakenWork, String conceptProof, String soldOrUsed, String opinion, String whyBuy, String estimation,String status) {
        this.applicationId = applicationId;
        this.grant = grant;
        this.applicant = applicant;        
        this.date = date;
        this.projectTitle = projectTitle;
        this.projectAbstract = projectAbstract;
        this.description = description;
        this.uniqueness = uniqueness;
        this.undertakenWork = undertakenWork;
        this.conceptProof = conceptProof;
        this.soldOrUsed = soldOrUsed;
        this.opinion = opinion;
        this.whyBuy = whyBuy;
        this.estimation = estimation;
        this.status = status;
    }

    public Application(String applicationId) {
        this.applicationId = applicationId;
    }

    Application() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    public String getApplicationId() {
        return applicationId;
    }

    public Grant getGrant() {
        return grant;
    }

    public Applicant getApplicant() {
        return applicant;
    }

//    public ArrayList<Evaluator> getEvaluators() {
//        return evaluators;
//    }

    public String getDate() {
        return date;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public String getProjectAbstract() {
        return projectAbstract;
    }

    public String getDescription() {
        return description;
    }

    public String getUniqueness() {
        return uniqueness;
    }

    public String getUndertakenWork() {
        return undertakenWork;
    }

    public String getConceptProof() {
        return conceptProof;
    }

    public String getSoldOrUsed() {
        return soldOrUsed;
    }

    public String getOpinion() {
        return opinion;
    }

    public String getStatus() {
        return status;
    }

    public String getWhyBuy() {
        return whyBuy;
    }

    public String getEstimation() {
        return estimation;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public void setGrant(Grant grant) {
        this.grant = grant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

//    public void setEvaluators(ArrayList<Evaluator> evaluators) {
//        this.evaluators = evaluators;
//    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public void setProjectAbstract(String projectAbstract) {
        this.projectAbstract = projectAbstract;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUniqueness(String uniqueness) {
        this.uniqueness = uniqueness;
    }

    public void setUndertakenWork(String undertakenWork) {
        this.undertakenWork = undertakenWork;
    }

    public void setConceptProof(String conceptProof) {
        this.conceptProof = conceptProof;
    }

    public void setSoldOrUsed(String soldOrUsed) {
        this.soldOrUsed = soldOrUsed;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public void setWhyBuy(String whyBuy) {
        this.whyBuy = whyBuy;
    }

    public void setEstimation(String estimation) {
        this.estimation = estimation;
    }

 
    
    
    
    
}






