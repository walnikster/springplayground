package com.nikirocks.user;

import lombok.experimental.Delegate;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<UserEntity, Long> {
    void deleteByUsername(String username);
}
