/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Evaluator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Fangzhou Zhou
 */
public class EvaluatorJdbcDAO implements EvaluatorDAO {

    private String uri = DbConnection.getDefaultConnectionUri();

    public EvaluatorJdbcDAO() {
    }

    public EvaluatorJdbcDAO(String uri) {
        this.uri = uri;
    }

    @Override
    public Evaluator getEvaluator(String userName) {
        String sql = "select * from Evaluator where username = ?";

        try (
                // get connection to database
                Connection connection = DbConnection.getConnection(uri);
                // create the statement
                PreparedStatement stmt = connection.prepareStatement(sql);) {
            // set the parameter
            stmt.setString(1, userName);

            // execute the query
            ResultSet rs = stmt.executeQuery();

            // query only returns a single result, so use 'if' instead of 'while'
            if (rs.next()) {
                String staffId = rs.getString("staffId");
                String username = rs.getString("username");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String email = rs.getString("email");
                String telephone = rs.getString("telephone");
                String department = rs.getString("department");
                return new Evaluator(staffId, username, firstname, lastname, email, telephone, department);

            } else {
                // no evaluator matches given username so return null
                return null;
            }

        } catch (SQLException ex) {  // we are forced to catch SQLException
            // don't let the SQLException leak from our DAO encapsulation
            throw new DAOException(ex.getMessage(), ex);
        }
    }
    
 
    public Evaluator getbyIdEvaluator(int id) {
        String sql = "select * from Evaluator where id = ?";

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
                String staffId = rs.getString("staffId");
                String username = rs.getString("username");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String email = rs.getString("email");
                String telephone = rs.getString("telephone");
                String department = rs.getString("department");
                return new Evaluator(staffId, username, firstname, lastname, email, telephone, department);

            } else {
                // no evaluator matches given username so return null
                return null;
            }

        } catch (SQLException ex) {  // we are forced to catch SQLException
            // don't let the SQLException leak from our DAO encapsulation
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    public void save(Evaluator evaluator) {
        String sql = "insert into Evaluator (staffId, username, firstname, lastname, email, telephone, department) values (?,?,?,?,?,?,?)";
        try (
                // get connection to database
                Connection dbCon = DbConnection.getConnection(uri);
                // create the statement
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            // copy the data from the student domain object into the SQL parameters
            stmt.setString(1, evaluator.getStaffId());
            stmt.setString(2, evaluator.getUsername());
            stmt.setString(3, evaluator.getFirstname());
            stmt.setString(4, evaluator.getLastname());
            stmt.setString(5, evaluator.getEmail());
            stmt.setString(6, evaluator.getTelephone());
            stmt.setString(7, evaluator.getDepartment());

            stmt.executeUpdate();  // execute the statement

        } catch (SQLException ex) {  // we are forced to catch SQLException
            // don't let the SQLException leak from our DAO encapsulation
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    /**
     * This method will delete an evaluator from the database.
     *
     * @param evaluator
     */
    @Override
    public void delete(Evaluator evaluator) {

        String sql = "delete from evaluator where staffId = ?";
        try (
                // get a connection to the database
                Connection dbCon = DbConnection.getConnection(uri);
                // create the statement
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            // execute the query
            stmt.setString(1, evaluator.getStaffId());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }

    }

}
