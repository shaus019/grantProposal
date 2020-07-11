/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Gorilla Rig
 */
public class Grant {
    private Integer grantId;
    private String name;
    private String department;
    private Integer amount;
    private String dueDate;

    public Grant(Integer grantId, String name, String department, Integer amount, String dueDate) {
        this.grantId = grantId;
        this.name = name;
        this.department = department;
        this.amount = amount;
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "Grant{" + "grantId= " + grantId + ", name= " + name + ", department= " + department + ", amount= " + amount + ", dueDate= " + dueDate + '}';
    }

    public Grant() {
    }

    public Integer getGrantId() {
        return grantId;
    }

    public void setGrantId(Integer grantId) {
        this.grantId = grantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
            
            
    
}
