package com.enxcl.cataloguemanagement.server.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enxcl.cataloguemanagement.server.admin.model.User;
import com.enxcl.cataloguemanagement.server.admin.model.UserPK;
import com.enxcl.cataloguemanagement.server.admin.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User validateUser(String companyCode, String userID, String password) {
        User user = userRepository.findById(new UserPK(userID, companyCode)).orElse(null);
        return (user != null && user.password.equals(password)) ? user : null;
    }
}
