package com.xventure.xpay.auth.repository;

import java.util.Optional;

import com.xventure.xpay.auth.models.ERole;
import com.xventure.xpay.auth.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {
 Optional<Role> findByName(ERole name);

}
