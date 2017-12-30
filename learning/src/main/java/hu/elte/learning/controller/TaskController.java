/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.learning.controller;
import hu.elte.learning.entity.Task;
import hu.elte.learning.entity.User;
import hu.elte.learning.repository.TaskRepository;
import hu.elte.learning.repository.UserRepository;
import java.security.Principal;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
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
    
    
    @GetMapping("/solve/{id}")
    public String solve(/*Task task, */@PathVariable long id, Model model) {
        Task task = taskRepository.findOne(id);
        model.addAttribute("task", task);
        return "form"; //form.html
    }
    
    @PostMapping("/solve/{id}")
    public String solveTask(
            @Valid Task task,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Principal principal) {
        if (bindingResult.hasErrors()) {
            return "form";
        }
        
        User user = userRepository.findByUsername(principal.getName());
        List<User> updateusers = task.getUsers();
        updateusers.add(user);
        task.setUsers(updateusers);
        taskRepository.save(task);

        redirectAttributes.addFlashAttribute("message", "Recipe successfully added!");
        return "redirect:/";
    }
    
}
