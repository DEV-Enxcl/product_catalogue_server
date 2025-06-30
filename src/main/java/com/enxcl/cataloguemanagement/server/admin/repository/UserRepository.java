package com.enxcl.cataloguemanagement.server.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enxcl.cataloguemanagement.server.admin.model.User;
import com.enxcl.cataloguemanagement.server.admin.model.UserPK;

@Repository
public interface UserRepository extends JpaRepository<User, UserPK> {
    

}
