package com.gabriel.pokems.service;
import com.gabriel.pokems.model.Inventory;
public interface InventoryService {
	Inventory[] getAll() throws Exception;
	Inventory get(Integer id) throws Exception;
	Inventory create(Inventory inventory) throws Exception;
	Inventory update(Inventory inventory) throws Exception;
	void delete(Integer id) throws Exception;
}
