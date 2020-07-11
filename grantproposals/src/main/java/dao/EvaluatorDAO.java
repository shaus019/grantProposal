/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Evaluator;

/**
 *
 * @author Fangzhou Zhou
 */
public interface EvaluatorDAO {
    
    Evaluator getEvaluator(String Id);
    
    void save(Evaluator evaluator);
    void delete(Evaluator evaluator);
}

