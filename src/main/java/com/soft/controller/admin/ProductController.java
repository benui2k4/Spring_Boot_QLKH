package com.soft.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.soft.models.Category;
import com.soft.models.Product;

import com.soft.service.CategoryService;
import com.soft.service.ProductService;
import com.soft.service.StorageService;

@Controller
@RequestMapping("/admin")
public class ProductController {

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private StorageService storageService;
	@Autowired
	private ProductService productService;

	@GetMapping("/product")
	public String index(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword,
			@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {

		Page<Product> listProduct = this.productService.getAll(pageNo);

		if (keyword != null) {
			listProduct = this.productService.searchProduct(keyword, pageNo);
		}
		model.addAttribute("totalPage", listProduct.getTotalPages());
		model.addAttribute("keyword", keyword);
		model.addAttribute("currentPage", pageNo);

		model.addAttribute("listProduct", listProduct);
		return "admin/product/index";
	}

	@GetMapping("/product-add")
	public String add(Model model) {
		Product product = new Product();

		model.addAttribute("product", product);
		List<Category> listCate = this.categoryService.getAll();
		model.addAttribute("listCate", listCate);

		return "admin/product/add";
	}

	@PostMapping("/product-add")
	public String save(@ModelAttribute("product") Product product, @RequestParam("fileImage") MultipartFile file) {

		this.storageService.store(file);
		String fileName = file.getOriginalFilename();
		product.setImage(fileName);
		if (this.productService.create(product)) {
			return "redirect:/admin/product";

		}
		return "admin/product/add";
	}

	@GetMapping("/edit-product/{id}")
	public String edit(Model model, @PathVariable("id") Integer id) {
		Product product = this.productService.findById(id);
		if (product == null) {
			model.addAttribute("error", "Product not found");
			return "redirect:/admin/product";
		}
		List<Category> listCate = this.categoryService.getAll();
		model.addAttribute("product", product);
		model.addAttribute("listCate", listCate);
		return "admin/product/edit";
	}

	@PostMapping("/edit-product")
	public String update(@ModelAttribute("product") Product product, @RequestParam("fileImage") MultipartFile file,
			Model model) {
		if (!file.isEmpty()) {
			try {
				String fileName = file.getOriginalFilename();
				this.storageService.store(file);
				product.setImage(fileName);
			} catch (Exception e) {
				model.addAttribute("error", "Failed to store file " + file.getOriginalFilename());
				model.addAttribute("product", product);
				return "admin/product/edit";
			}
		} else {
			Product existingProduct = this.productService.findById(product.getId());
			if (existingProduct != null) {
				product.setImage(existingProduct.getImage());
			} else {
				model.addAttribute("error", "Product not found");
				model.addAttribute("product", product);
				return "admin/product/edit";
			}
		}

		if (this.productService.update(product)) {
			return "redirect:/admin/product";
		} else {
			model.addAttribute("error", "Failed to update product");
			model.addAttribute("product", product);
			return "admin/product/edit";
		}
	}

	@GetMapping("/delete-product/{id}")
	public String delete(@PathVariable("id") Integer id) {
		if (this.productService.delete(id)) {

			return "redirect:/admin/product";

		} else {
			return "redirect:/admin/product";
		}
	}
}
