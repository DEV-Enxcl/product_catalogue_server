package com.enxcl.cataloguemanagement.server.inventory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.enxcl.cataloguemanagement.server.inventory.model.CategoryMaster;
import com.enxcl.cataloguemanagement.server.inventory.model.CategoryMasterPK;

public interface CategoryRepository extends JpaRepository<CategoryMaster, CategoryMasterPK> {
    
    @Query("SELECT b FROM CategoryMaster b WHERE b.companyCode = :companyCode")
    List<CategoryMaster> findAllCategories(@Param("companyCode") String companyCode);
    

}
