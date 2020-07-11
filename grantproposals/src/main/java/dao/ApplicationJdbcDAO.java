/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
 
import domain.Applicant;
import domain.Application;
import domain.Evaluator;
import domain.Grant;
import java.sql.Array;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
 
/**
 *
 * @author Fangzhou Zhou
 */
public class ApplicationJdbcDAO implements ApplicationDAO {
 
    private static final String URI = DbConnection.getDefaultConnectionUri();
    GrantDAO grantDao = new GrantJdbcDAO();
    ApplicantDAO applicantDao = new ApplicantJdbcDAO();
 
    /**
     *This will save an application to the database.
     * some datafields are not included but have to be added 
     * gotten rid of try catch statements except for DAOException
     * @param application
     */
    
    @Override
    public void save(Application application) {
 
        String sql = "insert into Application ( grantId, applicantId, date, projectTitle, projectAbstract, "
                                    + "description, uniqueness, undertakenWork, conceptProof, "
                + "soldOrUsed, opinion, whyBuy, estimation, status) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                            
        try ( 
            Connection con = DbConnection.getConnection(URI);
 
            PreparedStatement insertOrderStmt = con.prepareStatement(sql);
                
       ) {
           
            Grant g = application.getGrant();
            Applicant a = application.getApplicant();
            insertOrderStmt.setString(1, Integer.toString(g.getGrantId()));
            insertOrderStmt.setString(2, Integer.toString(a.getStaffId()));
            insertOrderStmt.setString(3, application.getDate());
            insertOrderStmt.setString(4, application.getProjectTitle());
            insertOrderStmt.setString(5, application.getProjectAbstract());
            insertOrderStmt.setString(6, application.getDescription());
            insertOrderStmt.setString(7, application.getUniqueness());
            insertOrderStmt.setString(8, application.getUndertakenWork());
            insertOrderStmt.setString(9, application.getConceptProof());
            insertOrderStmt.setString(10, application.getSoldOrUsed());
            insertOrderStmt.setString(11, application.getOpinion());
            insertOrderStmt.setString(12, application.getWhyBuy());
            insertOrderStmt.setString(13, application.getEstimation());
            insertOrderStmt.setString(14, application.getStatus());
            
            insertOrderStmt.executeUpdate();
            
            
        } catch (SQLException ex) {
        throw new DAOException(ex.getMessage(), ex);
        }
    }
    
    public Application getApplication(String id) {
        String sql = "select * from Application where id = ?";
 
        try (
                // get connection to database
                Connection connection = DbConnection.getConnection(URI);
                // create the statement
                PreparedStatement stmt = connection.prepareStatement(sql);) {
            // set the parameter
            stmt.setString(1, id);
             // execute the query
            ResultSet rs = stmt.executeQuery();
             // query only returns a single result, so use 'if' instead of 'while'
            if (rs.next()) {
                int grantId = rs.getInt("grantId");
                Grant grant = grantDao.getGrant(grantId);
                int applicantId = rs.getInt("applicantId");
                Applicant applicant = applicantDao.getApplicantById(applicantId); 
                String applicationId = rs.getString("applicationId");
//                Array evaluatorIdx = rs.getArray("evaluatorId");
//                String[] a = (String[])evaluatorIdx.getArray();
               

                String status = rs.getString("status");
                String date = rs.getString("date");
             
                String projectTitle = rs.getString("projectTitle");
                String projectAbstract = rs.getString("projectAbstract");
                String description = rs.getString("description");
                String uniqueness = rs.getString("uniqueness");
                String undertakenWork = rs.getString("undertakenWork");
                String conceptProof = rs.getString("conceptProof");
                String soldOrUsed = rs.getString("soldOrUsed");
                String opinion = rs.getString("opinion");
                String whyBuy = rs.getString("whyBuy");
                String estimation = rs.getString("estimation");
         //public Application(String applicationId, Grant grant, Applicant applicant,String date, String projectTitle, String projectAbstract, String description, String uniqueness, String undertakenWork, String conceptProof, String soldOrUsed, String opinion, String whyBuy, String estimation,String status) {

                return new Application(applicationId, grant, applicant,date, projectTitle, projectAbstract, description, uniqueness, undertakenWork, conceptProof, soldOrUsed, opinion, whyBuy, estimation, status);
 
            } else {
                // no evaluator matches given username so return null
                return null; 
            }
 
        } catch (SQLException ex) {  // we are forced to catch SQLException
            // don't let the SQLException leak from our DAO encapsulation
            throw new DAOException(ex.getMessage(), ex);
        }
    }
 
    /**
     * This method will delete an application from the database.
     *
     * @param application
     */
    @Override
    public void delete(Application application) {
 
        String sql = "delete from applicant where applicantId = ?";
        try (
                // get a connection to the database
                Connection dbCon = DbConnection.getConnection(URI);
                // create the statement
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            // execute the query
            stmt.setString(1, application.getApplicationId());
            stmt.executeUpdate();
 
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
 
    }

    @Override
    public Collection<Application> getAllApplications() {
        
        String sql = "select * from Application order by applicationId";
    
    try (
                // get a connection to the database
                Connection dbCon = DbConnection.getConnection(URI);
                // create the statement
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {

            // execute the query
            ResultSet rs = stmt.executeQuery();

            List<Application> applications = new ArrayList<>();
            
            while (rs.next()) {
                int grantId = rs.getInt("grantId");
                Grant grant = grantDao.getGrant(grantId);
                
                
                int applicantId = rs.getInt("applicantId");
                Applicant applicant = applicantDao.getApplicantById(applicantId);
 
                String applicationId = rs.getString("applicationId");
 
//                Array evaluatorIdx = rs.getArray("evaluatorId");
//                String[] a = (String[])evaluatorIdx.getArray();
//               
 
                String status = rs.getString("status");
 
                String date = rs.getString("date");
                
                String projectTitle = rs.getString("projectTitle");
                String projectAbstract = rs.getString("projectAbstract");
                String description = rs.getString("description");
                String uniqueness = rs.getString("uniqueness");
                String undertakenWork = rs.getString("undertakenWork");
                String conceptProof = rs.getString("conceptProof");
                String soldOrUsed = rs.getString("soldOrUsed");
                String opinion = rs.getString("opinion");
                String whyBuy = rs.getString("whyBuy");
                String estimation = rs.getString("estimation");
          //public Application(String applicationId, Grant grant, Applicant applicant,String date, String projectTitle, String projectAbstract, String description, String uniqueness, String undertakenWork, String conceptProof, String soldOrUsed, String opinion, String whyBuy, String estimation,String status) {

                Application app = new Application(applicationId, grant, applicant, date, projectTitle, projectAbstract, description, uniqueness, undertakenWork, conceptProof, soldOrUsed, opinion, whyBuy, estimation, status);
 
                applications.add(app);
            }
            // no evaluator matches given username so return null
            return applications;

        } catch (SQLException ex) {  // we are forced to catch SQLException
            // don't let the SQLException leak from our DAO encapsulation
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public Collection<Application> getApplicationsByGrantId(String grantId) {
        String sql = "select * from application where grantId = ? order by grantId";
        try (
                // get a connection to the database
                Connection dbCon = DbConnection.getConnection(URI);
                // create the statement
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            stmt.setString(1, grantId);

            // execute the query
            ResultSet rs = stmt.executeQuery();
            
            List<Application> applications = new ArrayList<>();
            
            while (rs.next()) {
                int grantID = rs.getInt("grantId");
                Grant grant = grantDao.getGrant(grantID);
                
                
                int applicantId = rs.getInt("applicantId");
                Applicant applicant = applicantDao.getApplicantById(applicantId);
 
                String applicationId = rs.getString("applicationId");
 
//                Array evaluatorIdx = rs.getArray("evaluatorId");
//                String[] a = (String[])evaluatorIdx.getArray();
//               
 
                String status = rs.getString("status");
 
                String date = rs.getString("date");
                
                String projectTitle = rs.getString("projectTitle");
                String projectAbstract = rs.getString("projectAbstract");
                String description = rs.getString("description");
                String uniqueness = rs.getString("uniqueness");
                String undertakenWork = rs.getString("undertakenWork");
                String conceptProof = rs.getString("conceptProof");
                String soldOrUsed = rs.getString("soldOrUsed");
                String opinion = rs.getString("opinion");
                String whyBuy = rs.getString("whyBuy");
                String estimation = rs.getString("estimation");
          //public Application(String applicationId, Grant grant, Applicant applicant,String date, String projectTitle, String projectAbstract, String description, String uniqueness, String undertakenWork, String conceptProof, String soldOrUsed, String opinion, String whyBuy, String estimation,String status) {

                Application app = new Application(applicationId, grant, applicant, date, projectTitle, projectAbstract, description, uniqueness, undertakenWork, conceptProof, soldOrUsed, opinion, whyBuy, estimation, status);
 
                applications.add(app);
            }
            // no evaluator matches given username so return null
            return applications;

        } catch (SQLException ex) {  // we are forced to catch SQLException
            // don't let the SQLException leak from our DAO encapsulation
            throw new DAOException(ex.getMessage(), ex);
        }
    }
    

            
}




    










