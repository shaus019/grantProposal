/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author villa
 */
public class Receipt {
    
    
    private Evaluator evaluator;    
    private Application application;
    private String comments;
    private LocalDate dateOfEvaluation;

    public Receipt(Evaluator evaluator, Application application, String comments, LocalDate dateOfEvaluation) {
        this.evaluator = evaluator;
        this.application = application;
        this.comments = comments;
        this.dateOfEvaluation = dateOfEvaluation;
    }

    public Receipt() {
    }

    public Evaluator getEvaluator() {
        return evaluator;
    }

    public Application getApplication() {
        return application;
    }

    public String getComments() {
        return comments;
    }

    public LocalDate getDateOfEvaluation() {
        return dateOfEvaluation;
    }

    public void setEvaluator(Evaluator evaluator) {
        this.evaluator = evaluator;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setDateOfEvaluation(LocalDate dateOfEvaluation) {
        this.dateOfEvaluation = dateOfEvaluation;
    }

    @Override
    public String toString() {
        return "Receipt{" + "evaluators=" + evaluator + ", application=" + application + ", comments=" + comments + ", dateOfEvaluation=" + dateOfEvaluation + '}';
    }
    
    
    
    
    
    
    
}




