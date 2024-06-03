package com.soft.service;

import java.util.List;

import com.soft.models.Product;

public interface ProductService {
	List<Product> getAll();

	Boolean create(Product product);

	Product findById(Integer id);

	Boolean update(Product product);

	Boolean delete(Integer id);
}
