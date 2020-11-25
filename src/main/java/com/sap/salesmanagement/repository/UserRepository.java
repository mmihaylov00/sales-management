package com.sap.salesmanagement.repository;

import com.sap.salesmanagement.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    UserEntity findFirstByEmail(String email);
}
