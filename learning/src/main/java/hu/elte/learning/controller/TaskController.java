/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.learning.controller;
import hu.elte.learning.entity.Solution;
import hu.elte.learning.entity.Task;
import hu.elte.learning.entity.User;
import hu.elte.learning.repository.SolutionRepository;
import hu.elte.learning.repository.TaskRepository;
import hu.elte.learning.repository.UserRepository;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author sofia
 */
@Controller
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private SolutionRepository solutionRepository;
    
    @GetMapping("/")
    public String index(Model model) {
        Iterable<Task> tasks = taskRepository.findAll();
        model.addAttribute("tasks", tasks);
        return "tasks";  //tasks.html
    }
    @PostMapping("/")
    public String search(
            Model model,
            @RequestParam("searchString") String searchString) {
        Iterable<Task> tasks = taskRepository.findAll();
        List<Task> tasksFiltered = new ArrayList<>();
        for(Task task:tasks){
            if(task.getTask_name().contains(searchString)){
                tasksFiltered.add(task);
            }
        }
        model.addAttribute("tasks", tasksFiltered);
        return "tasks";  //tasks.html
    } 
    
    @RequestMapping("/success/{id}")
    public String success(@PathVariable long id, Model model) {
        Task task = taskRepository.findOne(id);
        model.addAttribute("task", task);
        List<Solution> solutions = task.getSolutions();
        solutions.forEach((solution) -> {
            System.out.println("AAAAAAAAAA"+solution.getSolution_text());
        });
        Iterable<Solution> solutions2 = solutionRepository.findAll();
        solutions2.forEach((solution) -> {
            System.out.println("BBBBBBB"+solution.getSolution_text());
        });
        model.addAttribute("solutions", solutions);        
        return "success";  //success.html
    }
    
    @GetMapping("/show/{id}")
    public String showRecipe(@PathVariable long id, Model model) {
        Task task = taskRepository.findOne(id);
        model.addAttribute("task", task);
        return "show";
    }
    
    
    @GetMapping("/solve/{id}")
    public String solve(Solution solution,@PathVariable long id, Model model) {
        Task task = taskRepository.findOne(id);
        model.addAttribute("task", task);
        return "form"; //form.html
    }
    
    @PostMapping("/solve/{id}")
    public String solveTask(
            @Valid Solution solution,
            @PathVariable long id,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Principal principal) {
        if (bindingResult.hasErrors()) {
            return "form";
        }
        Task task = taskRepository.findOne(id);
        User user = userRepository.findByUsername(principal.getName());
        System.out.println("CCCCCCCCCCCCCC"+task.getTask_name());
        System.out.println("DDDDDDDDDDDDD"+user.getUsername());
        //salvo la nuova soluzione
        solution.setTask(task);
        solution.setUser(user);
        solutionRepository.save(solution);
        //aggiungo il nuovo user al task
        List<User> updatedusers = task.getUsers();
        updatedusers.add(user);
        task.setUsers(updatedusers);
        //aggiungo la nuova solution al task        
        List<Solution> updatedsolutions = task.getSolutions();
        updatedsolutions.add(solution);
        task.setSolutions(updatedsolutions); 
        //aggiungo la nuova solution allo user        
        List<Solution> updatedsolutions2 = user.getSolutions();
        updatedsolutions2.add(solution);
        user.setSolutions(updatedsolutions2);      
        //aggiungo il nuovo task allo user
        List<Task> updatedtasks = user.getTasks();
        updatedtasks.add(task);
        user.setTasks(updatedtasks);

        redirectAttributes.addFlashAttribute("message", "Solution successfully added!");
        return "redirect:/success/{id}";
    }
    
}
