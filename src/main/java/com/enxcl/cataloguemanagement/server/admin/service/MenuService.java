package com.enxcl.cataloguemanagement.server.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enxcl.cataloguemanagement.server.admin.model.Menu;
import com.enxcl.cataloguemanagement.server.admin.repository.MenuRepository;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public List<Menu> findAllMenu(String companyCode) {
        return menuRepository.findByCompanyCode(companyCode);
    }
}
