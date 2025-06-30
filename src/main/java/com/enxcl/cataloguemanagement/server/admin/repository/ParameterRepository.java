package com.enxcl.cataloguemanagement.server.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enxcl.cataloguemanagement.server.admin.model.Parameter;
import com.enxcl.cataloguemanagement.server.admin.model.ParameterPK;

@Repository
public interface ParameterRepository extends JpaRepository<Parameter, ParameterPK> {
    
    List<Parameter> findByCompanyCode(String companyCode);
    
}
