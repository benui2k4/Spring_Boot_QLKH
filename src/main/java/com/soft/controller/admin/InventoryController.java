package com.soft.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.soft.models.Category;
import com.soft.models.Inventory;
import com.soft.models.Product;
import com.soft.service.InventoryService;
import com.soft.service.ProductService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/admin")
public class InventoryController {
	@Autowired
	private InventoryService inventoryService;
	@Autowired
	private ProductService productService;
	@GetMapping("/inventory")
	public String index(Model model, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
		Page<Inventory> listInventory = this.inventoryService.getAll(pageNo);
		model.addAttribute("totalPage", listInventory.getTotalPages());
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("listInventory", listInventory);
		return "admin/inventory/index";
	}
	
	@GetMapping("/inventory-add")
	public String add(Model model) {
		Inventory inventory = new Inventory();
		model.addAttribute("inventory", inventory);
		List<Product> listProduct = this.productService.getAll();
		model.addAttribute("listProduct", listProduct);
		return "admin/inventory/add";
	}
	
	@PostMapping("/inventory-add")
	public String save(@ModelAttribute("inventory") Inventory inventory, Model model) {
		//TODO: process POST request
		if(this.inventoryService.create(inventory)) {
			return "redirect:/admin/inventory";
		}
		else {
			List<Product> listProduct = this.productService.getAll();
			model.addAttribute("listProduct", listProduct);
			return "admin/product/add";
		}
	}
	
	@GetMapping("/delete-inventory/{id}")
	public String delete(@PathVariable("id") Integer id, Model model) {
		if(this.inventoryService.delete(id)) {
			return "redirect:/admin/inventory";
		}else {
			return "redirect:/admin/inventory";
		}
	}
	
	@GetMapping("/edit-inventory/{id}")
	public String edit(Model model, @PathVariable("id") Integer id) {
		Inventory inventory = this.inventoryService.findById(id);
		if (inventory == null) {
			model.addAttribute("error", "inventory not found");
			return "redirect:/admin/inventory";
		}
		model.addAttribute("inventory", inventory);
		List<Product> listProduct = this.productService.getAll();
		model.addAttribute("listProduct", listProduct);
		
		return "admin/inventory/edit";
	}
	
	
	@PostMapping("/edit-inventory")
	public String update(@ModelAttribute("inventory") Inventory inventory,  Model model) {
	  
	        

	    if (this.inventoryService.update(inventory)) {
	        return "redirect:/admin/inventory";
	    } else {
	        model.addAttribute("error", "Failed to update inventory");
	        model.addAttribute("inventory", inventory);
	        return "admin/inventory/edit";
	    }
	}
}
