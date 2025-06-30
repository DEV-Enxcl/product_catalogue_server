package com.enxcl.cataloguemanagement.server.inventory.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.enxcl.cataloguemanagement.server.inventory.model.CategoryMaster;
import com.enxcl.cataloguemanagement.server.inventory.model.InventoryMaster;
import com.enxcl.cataloguemanagement.server.inventory.service.InventoryService;


@RestController
@RequestMapping("/productcatalogue/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/{company}/findAllInventoryItems")
    public ResponseEntity<List<InventoryMaster>> findAllInventoryItems(@PathVariable String company) {
        return ResponseEntity.of(Optional.ofNullable(inventoryService.findAllInventoryItems(company)));
    } 

    @GetMapping("/{company}/findAllInventoryItemsForCategory/{category}")
    public ResponseEntity<List<InventoryMaster>> findAllInventoryItemsForCategory(@PathVariable String company,@PathVariable String category) {
        return ResponseEntity.of(Optional.ofNullable(inventoryService.findAllInventoryItemsForCategory(company,category)));
    } 

    @PostMapping("/{company}/findInventoryForItems")
    public ResponseEntity<List<InventoryMaster>> findInventoryForItems(@PathVariable String company,@RequestBody List<String> skus) {
        return ResponseEntity.of(Optional.ofNullable(inventoryService.findInventoryForItems(company,skus)));
    } 

    @GetMapping("/{company}/findAllCategories")
    public ResponseEntity<List<CategoryMaster>> findAllCategories(@PathVariable String company) {
        return ResponseEntity.of(Optional.ofNullable(inventoryService.findAllCategories(company)));
    } 

    @PostMapping(
        value = "/saveCategory",
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<CategoryMaster> saveCategory(
            @RequestPart("category") CategoryMaster categoryMaster,
            @RequestPart(value = "image", required = false) MultipartFile imageFile
    ) {
        try {
            if (imageFile != null && !imageFile.isEmpty()) {
                categoryMaster.imageData = imageFile.getBytes();
            }
            CategoryMaster saved = inventoryService.savecategory(categoryMaster);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    //Method to delete a category
    @PostMapping("/{company}/deleteCategory/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable String company, @PathVariable String categoryId) {
        try {
            inventoryService.deleteCategory(company, categoryId);
            return ResponseEntity.ok().build();    
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    //save inventory item
    @PostMapping(
        value = "/saveInventoryItem",
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<InventoryMaster> saveInventoryItem(
            @RequestPart("inventory") InventoryMaster inventoryMaster,
            @RequestPart(value = "image", required = false) MultipartFile imageFile,
            @RequestPart(value = "additionalImages", required = false) List<MultipartFile> additionalImages
    ) {
        try {
            if (imageFile != null && !imageFile.isEmpty()) {
                inventoryMaster.imageData = imageFile.getBytes();   
                inventoryMaster.additionalImageData= new byte[additionalImages.size()][];
                for (int i = 0; i < additionalImages.size(); i++) {
                    MultipartFile addImg = additionalImages.get(i);
                    if (addImg != null && !addImg.isEmpty()) {
                        inventoryMaster.additionalImageData[i] = addImg.getBytes();
                    } else {
                        inventoryMaster.additionalImageData[i] = null; // Handle empty additional images        
                    }
                }
            }
            InventoryMaster saved = inventoryService.saveInventoryItem(inventoryMaster);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
