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
    public String addUserPage(Model model) {
        model.addAttribute("user", new User());
        return "add";
    }

    @GetMapping(value = "/edit")
    public String editUserPage(@RequestParam("id") int id, Model model) {
        model.addAttribute("users", userService.getUserById(id));
        return "edit";
    }

    @GetMapping(value = "/remove")
    public String removeUserPage(@RequestParam("id") int id, Model model) {
        model.addAttribute("users", userService.getUserById(id));
        return "remove";
    }

    @PostMapping("/add")
    public String addNewUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/";
    }

    @PostMapping("/edit")
    public String editSelectedUser(@ModelAttribute("user") User user,
                                   @RequestParam("id") int id) {
        userService.update(user, id);
        return "redirect:/";
    }

    @PostMapping("/remove")
    public String removeSelectedUser(@RequestParam("id") int id) {
        userService.removeUserWithId(id);
        return "redirect:/";
    }
}