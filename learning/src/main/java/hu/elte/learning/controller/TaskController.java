/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.learning.controller;
import hu.elte.learning.entity.Task;
import hu.elte.learning.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author sofia
 */
@Controller
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;
    
    @RequestMapping("/")
    public String index(Model model) {
        Iterable<Task> tasks = taskRepository.findAll();
        model.addAttribute("tasks", tasks);
        return "tasks";  //tasks.html
    }
    
    @GetMapping("/show/{id}")
    public String showRecipe(@PathVariable long id, Model model) {
        Task task = taskRepository.findOne(id);
        model.addAttribute("task", task);
        return "show";
    }
}
