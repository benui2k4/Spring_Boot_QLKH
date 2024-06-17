package com.soft.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.soft.models.User;
import com.soft.repository.CategoryRepository;
import com.soft.repository.ProductRepository;
import com.soft.repository.RoleRepository;
import com.soft.repository.UserRepository;
import com.soft.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private UserService userService; // Thay vì `UserService`, phải là `userService` để đúng tên biến

    @RequestMapping
    public String index() {
        return "redirect:/admin/";
    }

    @RequestMapping("/")
    public String admin(Model model) {
        long countCategory = categoryRepository.count();
        long countProduct = productRepository.count();
        long countUser = userRepository.count();

        model.addAttribute("countCategory", countCategory);
        model.addAttribute("countProduct", countProduct);
        model.addAttribute("countUser", countUser);

        return "admin/index";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/account/management")
    public String accountManagement(Model model) {
        List<User> listUsers = userService.findAll(); 
        model.addAttribute("listUsers", listUsers);
        return "admin/account/management";
    }
}
