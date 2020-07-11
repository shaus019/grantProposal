/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Grant;
import java.util.Collection;

/**
 *
 * @author Fangzhou Zhou
 */
public interface GrantDAO {
    
    Collection<Grant> getFilteredList(String departmentFilter);
    
    Collection<Grant> getAllGrants();
    
    Collection<String> getAllDepartments();
    
    Grant  getGrant(Integer grantId);
    
    void save(Grant grant);
    void delete(Grant grant);
}

