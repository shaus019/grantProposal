/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Application;
import java.util.Collection;

/**
 *
 * @author Fangzhou Zhou
 */
public interface ApplicationDAO {
    
    void save(Application application);
    void delete(Application application);
    Application getApplication(String id);
    
    Collection<Application> getAllApplications();
   
    Collection<Application> getApplicationsByGrantId(String grantId);
    
//    Collection<Application> getFilteredList(String grantDepartment);
//    
//    Collection<String> getAllApplicationDepartments();
    
    
    
}







