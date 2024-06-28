package com.soft.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.soft.models.Inventory;



public interface InventoryService {
	List<Inventory> getAll();

	Boolean create(Inventory inventory);

	Inventory findById(Integer id);

	Boolean update(Inventory inventory);
	List<Inventory> searchInventory(String keyword);
	Boolean delete(Integer id);
	Page<Inventory> getAll(Integer pageNo);
	Page<Inventory> searchInventory(String keyword , Integer pageNo);
}
