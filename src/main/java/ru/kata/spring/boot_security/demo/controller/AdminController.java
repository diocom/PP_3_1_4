package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@RequestMapping("/admin")
@Controller
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostMapping("/process_register")
    public String processRegister(@ModelAttribute("user") User user, Model model) {
        userService.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> listUsers = userService.findAll();
        List<Role> listRoles = roleService.listRoles();
        model.addAttribute("listUsers", listUsers);
        model.addAttribute("listRoles", listRoles);

        return "/admin/users";
    }

    @PostMapping("/edit/{id}")
    public String editUser(@ModelAttribute("user") User user, @PathVariable("id") Long id, Model model) {
        List<Role> listRoles = roleService.listRoles();
        model.addAttribute("listRoles", listRoles);
        userService.update(id, user);
        return "redirect:/admin/users";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin/users";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model) {
        List<Role> listRoles = roleService.listRoles();
        model.addAttribute("listRoles", listRoles);
        userService.delete(id);
        return "redirect:/admin/users";
    }
}
