package com.soft.controller.admin;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.soft.models.Role;
import com.soft.models.User;
import com.soft.models.UserRole;
import com.soft.repository.RoleRepository;
import com.soft.repository.UserRoleRepository;
import com.soft.service.UserService;

@Controller
public class UserController {
	private UserService userService;
	private RoleRepository roleRepository;
	private UserRoleRepository userRoleRepository;
	private PasswordEncoder passwordEncoder;

	public UserController(UserService userService, RoleRepository roleRepository, UserRoleRepository userRoleRepository,
			PasswordEncoder passwordEncoder) {
		this.userService = userService;
		this.roleRepository = roleRepository;
		this.userRoleRepository = userRoleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@GetMapping("/login")
	public String logon() {
		return "admin/login";
	}

	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("user", new User());
		return "admin/register";
	}

	@PostMapping("/register")
	public String registerSubmit(@ModelAttribute("user") User user) {

		if (userService.findByUserName(user.getUserName()) != null) {

			return "redirect:/register?error"; //
		}
		user.setEnabled(true);
		user.setPassWord(passwordEncoder.encode(user.getPassWord()));
		userService.save(user);

		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(roleRepository.findByName("admin"));

		userRoleRepository.save(userRole);
		return "redirect:/login";
	}

}
