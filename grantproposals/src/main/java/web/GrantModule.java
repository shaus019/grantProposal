/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.GrantDAO;
import org.jooby.Jooby;
/**
 *
 * @author fangzhou
 */
public class GrantModule extends Jooby{
    private GrantDAO dao;

    public  GrantModule(GrantDAO dao) {
        
        get("/api/grants", () -> dao.getAllGrants());
        get("/api/grants/:grantId", (req) -> {
            String grantId = req.param("grantId").value();
            return dao.getGrant(Integer.parseInt(grantId));
        });
       
        get("/api/departments/", () -> dao.getAllDepartments());
        
        get("/api/departments/:department", (req) -> {
        String department = req.param("department").value();
        return dao.getFilteredList(department);
        });
        
        this.dao=dao;
    }
    
    
}


