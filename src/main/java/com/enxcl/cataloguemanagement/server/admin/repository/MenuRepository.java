package com.enxcl.cataloguemanagement.server.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enxcl.cataloguemanagement.server.admin.model.Menu;
import com.enxcl.cataloguemanagement.server.admin.model.MenuPK;

@Repository
public interface MenuRepository extends JpaRepository<Menu, MenuPK> {
    
    List<Menu> findByCompanyCode(String companyCode);
    
}
