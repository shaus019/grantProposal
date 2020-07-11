/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Applicant;

/**
 *
 * @author Fangzhou Zhou
 */
public interface ApplicantDAO {
    
    Applicant getApplicant(String userName, String password);
    Applicant getApplicantById(Integer id);
    
    void save(Applicant applicant);
    void delete(Applicant applicant);
}

