package web.controller;

import web.model.User;
import web.dao.UserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.service.UserService;

@Controller
public class UserController {


    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/")
    public String showUserTable(Model model) {
        model.addAttribute("users", userService.getAll());
        return "index";
    }

    @GetMapping("/add")
    public String showNewUserForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "add";
    }

    @GetMapping("/{id}/edit")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        User user = userService.getById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user id :" + id));
        model.addAttribute("user", user);
        return "edit";
    }

    @GetMapping("/remove/{id}")
    public String deleteUser(@PathVariable(value = "id") long id) {
        userService.delete(id);
        return "redirect:/";
    }

    @PostMapping("/add")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/";
    }

    @PostMapping("/{id}/edit")
    public String updateSelectedUser(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/";
    }
}