/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 
/**
 *
 * @author thomasroff
 */
    public class ProposalJdbcDAO implements ProposalDAO {
    
        private String URI = DbConnection.getDefaultConnectionUri();
 
        public ProposalJdbcDAO() {
        }
 
        @Override
        public void save(String proposal, String applicationId) {
            String sql = "insert into Proposal (proposal, applicationId) values (?,?)";
                            
        try ( 
            Connection con = DbConnection.getConnection(URI);
 
            PreparedStatement insertOrderStmt = con.prepareStatement(sql);
                
       ) {
           
            insertOrderStmt.setString(1, proposal);
            insertOrderStmt.setInt(2, Integer.parseInt(applicationId));
            
            insertOrderStmt.executeUpdate();
            
            
        } catch (SQLException ex) {
        throw new DAOException(ex.getMessage(), ex);
        }
    }
 
        @Override
        public String getProposal(Integer applicationId) {
            String sql = "select * from proposal where applicationId = ?";
 
            try (
                    // get connection to database
                    Connection connection = DbConnection.getConnection(URI);
                    // create the statement
                    PreparedStatement stmt = connection.prepareStatement(sql);) {
                // set the parameter
                stmt.setInt(1, applicationId);
 
                // execute the query
                ResultSet rs = stmt.executeQuery();
 
                // query only returns a single result, so use 'if' instead of 'while'
                if (rs.next()) {
                    String proposal = rs.getString("proposal");
                    return proposal;
 
                } else {
                    // no applicant matches given username so return null
                    return null;
                }
 
            } catch (SQLException ex) {  // we are forced to catch SQLException
                // don't let the SQLException leak from our DAO encapsulation
                throw new DAOException(ex.getMessage(), ex);
            }
        }
    }
