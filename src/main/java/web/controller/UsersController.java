package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "index";
    }

    @GetMapping(value = "/add")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "add";
    }

    @GetMapping("/edit")
    public String editUser(@RequestParam("id") int id, Model model) {
        model.addAttribute("users", userService.getUserById(id));
        return "edit";
    }

    @GetMapping("/remove")
    public String removeUser(@RequestParam("id") int id, Model model) {
        model.addAttribute("users", userService.getUserById(id));
        return "remove";
    }

    @PostMapping
    public String FormAction(
            @ModelAttribute("user") User user,
            @RequestParam("action") String action
    ) {
        action = action.toLowerCase().trim();
        if ("create".equals(action)) {
            userService.save(user);
        } else if ("update".equals(action)) {
            userService.update(user, user.getId());
        } else if ("delete".equals(action)) {
            System.out.println(user.getId());
            userService.removeUserWithId(user.getId());
        } else {
            throw new IllegalArgumentException("Unknown action: " + action);
        }
        return "redirect:/";
    }
}