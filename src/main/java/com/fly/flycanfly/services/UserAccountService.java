package com.fly.flycanfly.services;

import com.fly.flycanfly.dto.UserAccountDto;
import com.fly.flycanfly.entities.UserAccountEntity;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserAccountService extends UserDetailsService {
    UserAccountEntity addUserAccountEntity(UserAccountDto userAccountDto);

    UserAccountEntity findByUsername(String userName);
}
