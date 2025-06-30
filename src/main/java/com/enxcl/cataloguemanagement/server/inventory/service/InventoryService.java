package com.enxcl.cataloguemanagement.server.inventory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enxcl.cataloguemanagement.server.inventory.model.CategoryMaster;
import com.enxcl.cataloguemanagement.server.inventory.model.CategoryMasterPK;
import com.enxcl.cataloguemanagement.server.inventory.model.InventoryMaster;
import com.enxcl.cataloguemanagement.server.inventory.repository.CategoryRepository;
import com.enxcl.cataloguemanagement.server.inventory.repository.InventoryRepository;
import com.enxcl.cataloguemanagement.server.inventory.util.FileCreationUtil;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private CategoryRepository categoryRepository;
   
    public List<InventoryMaster> findAllInventoryItems(String company) {
        return inventoryRepository.findAllInventoryItems(company);
    }

    public List<InventoryMaster> findAllInventoryItemsForCategory(String company,String category) {
        return inventoryRepository.findAllInventoryItemsForCategory(company,category);
    }

    public List<InventoryMaster> findInventoryForItems(String company,List<String> skus) {
        return inventoryRepository.findInventoryForItems(company,skus);
    }
    //method fo save inventory item
    public InventoryMaster saveInventoryItem(InventoryMaster inventoryMaster) {
        if (inventoryMaster.imageData != null && inventoryMaster.imageData.length > 0)
        {
            inventoryMaster = FileCreationUtil.saveInventoryImage(inventoryMaster.imageData,inventoryMaster.additionalImageData, inventoryMaster);
            inventoryMaster.imageData = null; // Clear transient data
            inventoryMaster.additionalImageData = null; // Clear transient data
        }
        return inventoryRepository.save(inventoryMaster);
    }
    public List<CategoryMaster> findAllCategories(String company) {
        return categoryRepository.findAllCategories(company);
    }
    

    public CategoryMaster savecategory(CategoryMaster categoryMaster) {
        if (categoryMaster.imageData != null && categoryMaster.imageData.length > 0) {
            String filename = FileCreationUtil.saveCategoryImage(categoryMaster.imageData, categoryMaster.categoryID);
            categoryMaster.image = filename;
            categoryMaster.imageData = null; // Clear transient data
        }
        return categoryRepository.save(categoryMaster);
    }
    public void deleteCategory(String company, String categoryId) {
        CategoryMaster category = categoryRepository.findById(new CategoryMasterPK(categoryId, company)).orElse(null);
         // Corrected the findById method to use orElse(null) to avoid NullPointerException
        if (category != null) {
            categoryRepository.delete(category);
        }
    }
}
