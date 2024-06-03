package com.soft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft.models.Product;
import com.soft.repository.ProductRepository;
@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductRepository productRepository;
		
	@Override
	public List<Product> getAll() {
		
		return this.productRepository.findAll();
	}

	@Override
	public Boolean create(Product product) {
		try {
			this.productRepository.save(product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Product findById(Integer id) {
		
		return null;
	}

	@Override
	public Boolean update(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
