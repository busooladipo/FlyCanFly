package com.fly.flycanfly.repositories;

import com.fly.flycanfly.entities.UserAccountEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepo extends CrudRepository<UserAccountEntity, Long> {

    UserAccountEntity findUserAccountEntityByUserName(String username);


}
