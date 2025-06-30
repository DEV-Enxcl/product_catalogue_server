package com.enxcl.cataloguemanagement.server.admin.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enxcl.cataloguemanagement.server.admin.model.Parameter;
import com.enxcl.cataloguemanagement.server.admin.service.AdminService;

@RestController
@RequestMapping("/productcatalogue/api/admin/general")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/{company}/findAllParameters")
    public ResponseEntity<List<Parameter>> findAllParameters(@PathVariable String company) {
        return ResponseEntity.of(Optional.ofNullable(adminService.findAllParameters(company)));
    } 
}
