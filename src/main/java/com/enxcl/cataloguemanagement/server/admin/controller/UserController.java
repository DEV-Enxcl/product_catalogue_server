package com.enxcl.cataloguemanagement.server.admin.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enxcl.cataloguemanagement.server.admin.model.User;
import com.enxcl.cataloguemanagement.server.admin.service.UserService;

@RestController
@RequestMapping("/productcatalogue/api/admin/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/{company}/validate/{userID}/{password}")
    public ResponseEntity<User> validateUser(@PathVariable String company, @PathVariable String userID, @PathVariable String password) {
        return ResponseEntity.of(Optional.ofNullable(userService.validateUser(company, userID, password)));
    }   
}
