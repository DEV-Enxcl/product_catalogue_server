package com.enxcl.cataloguemanagement.server.inventory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.enxcl.cataloguemanagement.server.inventory.model.InventoryMaster;
import com.enxcl.cataloguemanagement.server.inventory.model.InventoryMasterPK;

public interface InventoryRepository extends JpaRepository<InventoryMaster, InventoryMasterPK> {
    
    @Query("SELECT b FROM InventoryMaster b WHERE b.companyCode = :companyCode")
    List<InventoryMaster> findAllInventoryItems(@Param("companyCode") String companyCode);
    
    @Query("SELECT b FROM InventoryMaster b WHERE b.companyCode = :companyCode AND b.category=:category ")
    List<InventoryMaster> findAllInventoryItemsForCategory(@Param("companyCode") String companyCode,@Param("category") String category);
    
    @Query("SELECT b FROM InventoryMaster b WHERE b.companyCode = :companyCode AND b.sku IN (:skus)")
    List<InventoryMaster> findInventoryForItems(@Param("companyCode") String companyCode,@Param("skus") List<String> skus);
    

}
