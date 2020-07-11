/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Application;
import domain.Evaluator;
import domain.Receipt;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author villa
 */
public class ReceiptJdbcDAO implements ReceiptDAO{
    
        private String uri = DbConnection.getDefaultConnectionUri();

    public ReceiptJdbcDAO() {
    }

      public ReceiptJdbcDAO(String uri) {
          this.uri = uri;
    }   
        

    @Override
    public void save(Receipt receipt) {
         

    try ( 
            
            Connection con = DbConnection.getConnection(uri); 
            
            PreparedStatement insertOrderStmt = con.prepareStatement(
                "insert into Receipt(evaluatorId, applicationId, comments, dateOfEvaluation) values(?,?,?,?)");
 
        PreparedStatement updateApplicationStatus = con.prepareStatement(
                
                "update Application set status =? where applicationId = ?");  
              
        
        
                ){
        
        if(receipt.getDateOfEvaluation() == null){
            receipt.setDateOfEvaluation(LocalDate.now());
             
        }
        
//        ArrayList <String> evaluators = new ArrayList<>();
//        
//        
//        for(Evaluator evaluator : receipt.getEvaluator()){
//            evaluators.add(evaluator.getStaffId());
            
        Evaluator evaluator = receipt.getEvaluator();
        
        Application application = receipt.getApplication();
        LocalDate date = receipt.getDateOfEvaluation();
        Timestamp timestamp = Timestamp.valueOf(date.atStartOfDay());
        
        insertOrderStmt.setString(1, evaluator.getStaffId());
        insertOrderStmt.setString(2, application.getApplicationId());
        insertOrderStmt.setString(3, receipt.getComments());
        insertOrderStmt.setTimestamp(4, timestamp);
        
        insertOrderStmt.executeUpdate();
                                        
                   
        /**
         * To update Status
         */
        
        String applicationStatus = receipt.getApplication().getStatus();
        updateApplicationStatus.setString(1, applicationStatus);
        updateApplicationStatus.setString(2, application.getApplicationId());
        
        
         updateApplicationStatus.executeUpdate();
        
        
        
	
         } catch (SQLException ex) {
        throw new DAOException(ex.getMessage(), ex);
        }
    
}
}









        
       

















