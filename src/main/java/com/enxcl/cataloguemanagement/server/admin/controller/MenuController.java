package com.enxcl.cataloguemanagement.server.admin.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enxcl.cataloguemanagement.server.admin.model.Menu;
import com.enxcl.cataloguemanagement.server.admin.service.MenuService;

@RestController
@RequestMapping("/productcatalogue/api/admin/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/{company}/findAllMenu")
    public ResponseEntity<List<Menu>> findAllMenu(@PathVariable String company) {
        return ResponseEntity.of(Optional.ofNullable(menuService.findAllMenu(company)));
    }   
}
