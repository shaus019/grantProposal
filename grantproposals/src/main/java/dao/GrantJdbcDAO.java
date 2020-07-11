/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Grant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Fangzhou Zhou
 */
public class GrantJdbcDAO implements GrantDAO {
    private String uri = DbConnection.getDefaultConnectionUri();

    public GrantJdbcDAO() {
    }
    
    public GrantJdbcDAO(String uri) {
        this.uri=uri;
    }
    
    
    public Collection<Grant> getFilteredList(String departmentFilter) {
        
        String sql = "select * from Grant where department = ? order by grantId";
        
        try (
                // get connection to database
                Connection connection = DbConnection.getConnection(uri);
                // create the statement
                PreparedStatement stmt = connection.prepareStatement(sql);) {
            // set the parameter
            stmt.setString(1, departmentFilter);
            

            // execute the query
            ResultSet rs = stmt.executeQuery();
            
            List<Grant> filteredGrants = new ArrayList<>();

            // query only returns a single result, so use 'if' instead of 'while'
            while (rs.next()) {

                // get the data out of the query
                Integer grantId = rs.getInt("grantId");
                String name = rs.getString("name");
                String department = rs.getString("department");
                Integer amount = rs.getInt("amount");
                String date = rs.getString("dueDate");

                // use the data to create a student object
                Grant g = new Grant(grantId, name, department, amount, date);

                // and put it in the collection
                filteredGrants.add(g);
            }
            
            return filteredGrants;

        } catch (SQLException ex) {  // we are forced to catch SQLException
            // don't let the SQLException leak from our DAO encapsulation
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    
    public Collection<Grant> getAllGrants() {
        String sql = "select * from Grant order by grantId";

        try (
                // get a connection to the database
                Connection dbCon = DbConnection.getConnection(uri);
                // create the statement
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            // execute the query
            ResultSet rs = stmt.executeQuery();

            // Using a List to preserve the order in which the data was returned from the query.
            List<Grant> grants = new ArrayList<>();

            // iterate through the query results
            while (rs.next()) {

                // get the data out of the query
                Integer grantId = rs.getInt("grantId");
                String name = rs.getString("name");
                String department = rs.getString("department");
                Integer amount = rs.getInt("amount");
                String date = rs.getString("dueDate");

                // use the data to create a student object
                Grant g = new Grant(grantId, name, department, amount, date);

                // and put it in the collection
                grants.add(g);
            }

            return grants;

        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }
    
    
    public Collection<String> getAllDepartments() {
        String sql = "select distinct department from Grant order by department";

        try (
                // get a connection to the database
                Connection dbCon = DbConnection.getConnection(uri);
                // create the statement
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            
            
            // execute the query
            ResultSet rs = stmt.executeQuery();

            // Using a List to preserve the order in which the data was returned from the query.
            List<String> dep = new ArrayList<>();

            // iterate through the query results
            while (rs.next()) {

                // get the data out of the query
                String p = rs.getString("department");

                // and put it in the collection
                dep.add(p);
            }

            return dep;

        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }
    
    public Grant getGrant(Integer grantId){
        String sql = "select * from Grant where grantId = ?";
        
        try (
                Connection dbCon = DbConnection.getConnection(uri);
                PreparedStatement stmt = dbCon.prepareStatement(sql);){
            
            stmt.setInt(1, grantId);
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                Integer id = rs.getInt("grantId");
                String name = rs.getString("name");
                String department = rs.getString("department");
                Integer  amount = rs.getInt("amount");
                //String date = rs.getString("date");
                return new Grant(id, name, department, amount, "");
            }
            
            else{
                return null;
            }
        }catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
            
        
                
    }
    
    public void save(Grant grant) {
        String sql = "insert into Grant (grantId, name, department, amount, dueDate) values (?,?,?,?,?)";
        try (
                // get connection to database
                Connection dbCon = DbConnection.getConnection(uri);
                // create the statement
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            // copy the data from the student domain object into the SQL parameters
            stmt.setInt(1, grant.getGrantId());
            stmt.setString(2, grant.getName());
            stmt.setString(3, grant.getDepartment());
            stmt.setInt(4, grant.getAmount());
            stmt.setString(5, grant.getDueDate());


            stmt.executeUpdate();  // execute the statement

        } catch (SQLException ex) {  // we are forced to catch SQLException
            // don't let the SQLException leak from our DAO encapsulation
            throw new DAOException(ex.getMessage(), ex);
        }
    }
    /**
     * This method will delete a grant from the database.
     *
     * @param grant 
     */
    @Override
    public void delete(Grant grant) {

        String sql = "delete from Grant where grantId = ?";
        try (
                // get a connection to the database
                Connection dbCon = DbConnection.getConnection(uri);
                // create the statement
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            // execute the query
            stmt.setString(1, grant.getGrantId().toString());
            stmt.executeUpdate();
 
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }

    }
}

