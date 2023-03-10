package ru.kata.spring.boot_security.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserDetailServiceImp;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserDetailServiceImp userDetailServiceImp;
    private final RoleService roleService;

    public AdminController(UserDetailServiceImp userDetailServiceImp, RoleService roleService) {
        this.userDetailServiceImp = userDetailServiceImp;
        this.roleService = roleService;
    }


    @GetMapping("/")
    public String listUser(Model model) {
        List<User> allUser = userDetailServiceImp.allUser();
        model.addAttribute("allUser", allUser);
        return "admin";
    }

    @GetMapping("/new")
    public String newUserEntity(Model model) {
        model.addAttribute("user", new User());
        List<Role> roles = roleService.findAllRoles();
        model.addAttribute("allRoles", roles);
        return "new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userDetailServiceImp.saveUser(user);
        return "redirect:/admin/";

    }

    @GetMapping("/edit/{id}")
    public ModelAndView editUser(@PathVariable(name = "id") Long id) {
        User user = userDetailServiceImp.findUserById(id);
        ModelAndView model = new ModelAndView("edit");
        model.addObject("user", user);
        List<Role> roles = roleService.findAllRoles();
        model.addObject("allRoles", roles);

        return model;
    }

    @PatchMapping ("/{id}")
    public String update(@ModelAttribute("user") User user) {
        userDetailServiceImp.update(user);
        return "redirect:/admin/";

    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userDetailServiceImp.deleteUser(id);
        return "redirect:/admin/";
    }
}

