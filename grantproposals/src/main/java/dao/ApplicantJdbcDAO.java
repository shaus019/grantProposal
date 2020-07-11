/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Applicant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Fangzhou Zhou
 */
public class ApplicantJdbcDAO implements ApplicantDAO {
    
    private String uri = DbConnection.getDefaultConnectionUri();

    public ApplicantJdbcDAO() {
    }
    
    public ApplicantJdbcDAO(String uri) {
        this.uri = uri;
    }

    /**
     *This method will take
     * @param userName
     * @param password
     * @return applicant
     */
    @Override
    public Applicant getApplicant(String userName, String password) {
        String sql = "select * from Applicant where username = ? AND password = ?";

        try (
                // get connection to database
                Connection connection = DbConnection.getConnection(uri);
                // create the statement
                PreparedStatement stmt = connection.prepareStatement(sql);) {
            // set the parameter
            stmt.setString(1, userName);
            stmt.setString(2, password);

            // execute the query
            ResultSet rs = stmt.executeQuery();

            // query only returns a single result, so use 'if' instead of 'while'
            if (rs.next()) {
                Integer staffId = rs.getInt("staffId");
                String username = rs.getString("username");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String email = rs.getString("email");
                String telephone = rs.getString("telephone");
                String department = rs.getString("department");
                String qualification = rs.getString("qualification");
                return new Applicant(staffId, username, firstname, lastname, email, telephone, department, qualification);

            } else {
                // no applicant matches given username so return null
                return null;
            }

        } catch (SQLException ex) {  // we are forced to catch SQLException
            // don't let the SQLException leak from our DAO encapsulation
            throw new DAOException(ex.getMessage(), ex);
        }
    }
    
    @Override
    public Applicant getApplicantById(Integer id) {
        String sql = "select * from Applicant where staffId = ?";

        try (
                // get connection to database
                Connection connection = DbConnection.getConnection(uri);
                // create the statement
                PreparedStatement stmt = connection.prepareStatement(sql);) {
            // set the parameter
            stmt.setInt(1, id);

            // execute the query
            ResultSet rs = stmt.executeQuery();

            // query only returns a single result, so use 'if' instead of 'while'
            if (rs.next()) {
                Integer staffId = rs.getInt("staffId");
                String username = rs.getString("username");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String email = rs.getString("email");
                String telephone = rs.getString("telephone");
                String department = rs.getString("department");
                String qualification = rs.getString("qualification");
                return new Applicant(staffId, username, firstname, lastname, email, telephone, department, qualification);

            } else {
                // no applicant matches given username so return null
                return null;
            }

        } catch (SQLException ex) {  // we are forced to catch SQLException
            // don't let the SQLException leak from our DAO encapsulation
            throw new DAOException(ex.getMessage(), ex);
        }
    }
    
    /**
     *This method will save an applicant.
     * @param applicant
     */
    @Override
    public void save(Applicant applicant) {
        String sql = "insert into Applicant (staffId, username, firstname, lastname, email, telephone, department, qualification) values (?,?,?,?,?,?,?,?)";
        try (
                // get connection to database
                Connection dbCon = DbConnection.getConnection(uri);
                // create the statement
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            // copy the data from the student domain object into the SQL parameters
            stmt.setInt(1, applicant.getStaffId());
            stmt.setString(2, applicant.getUsername());
            stmt.setString(3, applicant.getFirstname());
            stmt.setString(4, applicant.getLastname());
            stmt.setString(5, applicant.getEmail());
            stmt.setString(6, applicant.getTelephone());
            stmt.setString(7, applicant.getDepartment());
            stmt.setString(8, applicant.getQualification());

            stmt.executeUpdate();  // execute the statement

        } catch (SQLException ex) {  // we are forced to catch SQLException
            // don't let the SQLException leak from our DAO encapsulation
            throw new DAOException(ex.getMessage(), ex);
        }
    }
    

    /**
     *This method will delete an applicant from the database.
     * @param applicant
     */
    @Override
    public void delete(Applicant applicant) {

        String sql = "delete from applicant where staffId = ?";
        try (
                // get a connection to the database
                Connection dbCon = DbConnection.getConnection(uri);
                // create the statement
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            // execute the query
            stmt.setInt(1, applicant.getStaffId());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }

    }

}


