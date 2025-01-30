package web.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.server.ResponseStatusException;
import web.model.User;
import web.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/")
    public String showUserTable(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "index";
    }

    @GetMapping("/add")
    public String showNewUserForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "add";
    }

    @PostMapping("/add")
    public String saveUser(@ModelAttribute("user") User user) {
        userRepository.save(user);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user id :" + id));
        model.addAttribute("user", user);
        return "edit";
    }

    @GetMapping("/remove/{id}")
    public String deleteUser(@PathVariable(value = "id") long id) {
        userRepository.deleteById(id);
        return "redirect:/";
    }
}