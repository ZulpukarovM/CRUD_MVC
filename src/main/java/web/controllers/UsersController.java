package web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UsersController {
    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getAllUsers( Model model) {
            List<User> allUsers = userService.getAllUsers();
            model.addAttribute("allUsers", allUsers);
            return "user/users";
    }


    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "user/new";
    }

    @PostMapping(path = "/new")
    public String create(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/user";
    }


    @GetMapping("/delete")
    public String usersId() {
        return "user/delete";
    }
    @PostMapping("/delete")
    public String delete(@RequestParam("id") Long id) {
        userService.delete(id);
        return "redirect:/user";
    }


    @GetMapping("/update")
    public String page() {
        return "user/update";
    }
    @PostMapping("/update")
    public String update(@RequestParam("id") long id, @RequestParam("name") String name,
                         @RequestParam("lastName") String lastName, @RequestParam("age") int age) {
        userService.update(id, name, lastName, age);
        return "redirect:/user";

    }


}
