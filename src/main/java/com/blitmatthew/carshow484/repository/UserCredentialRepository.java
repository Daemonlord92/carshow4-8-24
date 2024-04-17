package com.blitmatthew.carshow484.repository;

import com.blitmatthew.carshow484.entity.UserCredentials;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserCredentialRepository extends CrudRepository<UserCredentials, Long> {
    Optional<UserCredentials> findByEmail(String username);
}
