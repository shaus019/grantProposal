/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import org.jooby.Jooby;
import org.jooby.Status;
import dao.*;
import domain.Evaluator;
import org.jooby.Result;


/**
 *
 * @author rofth173
 */
public class EvaluatorModule extends Jooby {
    
    EvaluatorDAO evaluatorDao = new EvaluatorJdbcDAO();
    
     public EvaluatorModule(EvaluatorDAO dao) {
        get("/api/evaluator/:username", (req) -> {
            String username = req.param("username").value();
            Evaluator evaluator = evaluatorDao.getEvaluator(username);
            if(evaluator == null) {
                return new Result().status(Status.NOT_FOUND);
            } else {
                return evaluatorDao.getEvaluator(username);
            }
        });
        
        post("/api/register", (req, rsp) -> {
            Evaluator evaluator = req.body().to(Evaluator.class);
            evaluatorDao.save(evaluator);
            rsp.status(Status.CREATED);
        });
        
    }
}
