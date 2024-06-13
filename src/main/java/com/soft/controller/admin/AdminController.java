package com.soft.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.soft.repository.CategoryRepository;
import com.soft.repository.ProductRepository;
import com.soft.repository.UserRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

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
}
