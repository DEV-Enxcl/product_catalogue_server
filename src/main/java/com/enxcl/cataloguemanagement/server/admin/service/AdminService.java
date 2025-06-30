package com.enxcl.cataloguemanagement.server.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enxcl.cataloguemanagement.server.admin.model.Parameter;
import com.enxcl.cataloguemanagement.server.admin.repository.ParameterRepository;

@Service
public class AdminService {

    @Autowired
    private ParameterRepository parameterRepository;

    public List<Parameter> findAllParameters(String companyCode) {
        return parameterRepository.findByCompanyCode(companyCode);
    }
}
