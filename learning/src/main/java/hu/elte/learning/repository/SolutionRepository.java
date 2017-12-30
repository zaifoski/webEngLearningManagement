/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.learning.repository;

import hu.elte.learning.entity.Solution;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author sofia
 */
public interface SolutionRepository extends CrudRepository<Solution, Long> {
    
}

