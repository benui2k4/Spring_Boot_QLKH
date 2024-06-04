package com.soft.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft.models.Product;
import com.soft.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
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

		return productRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Khong ton tai product co id " + id));
	}

	@Override
	public Boolean update(Product product) {
		try {
			this.productRepository.save(product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean delete(Integer id) {
		try {
			this.productRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
