package com.fly.flycanfly.services.Impl;

import com.fly.flycanfly.dto.UserAccountDto;
import com.fly.flycanfly.entities.UserAccountEntity;
import com.fly.flycanfly.repositories.UserAccountRepo;
import com.fly.flycanfly.services.UserAccountService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
@Transactional
@Setter
@Getter
public class UserAccountServiceImpl implements UserAccountService {
    private final PasswordEncoder passwordEncoder;

    private final UserAccountRepo userAccountRepo;

    @Autowired
    public UserAccountServiceImpl(UserAccountRepo userAccountRepo) {
        this.userAccountRepo = userAccountRepo;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }


    @Override
    public UserAccountEntity addUserAccountEntity(UserAccountDto userAccountDto) {
        UserAccountEntity userAccountEntity = new UserAccountEntity();
        userAccountEntity.setUserName(userAccountDto.getUsername());
        userAccountEntity.setEmail(userAccountDto.getEmail());
        userAccountEntity.setPassword(passwordEncoder.encode(userAccountDto.getPassword()));
        userAccountEntity.setConfirmPassword(passwordEncoder.encode(userAccountDto.getConfirmPassword()));

        return userAccountRepo.save(userAccountEntity);
    }

    @Override
    public UserAccountEntity findByUsername(String userName) {
        return userAccountRepo.findUserAccountEntityByUserName(userName);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        UserAccountEntity userAccountEntity = userAccountRepo.findUserAccountEntityByUserName(username);
        if (userAccountEntity == null) {
            throw new UsernameNotFoundException("Username " + username + " not found");
        }
        return new User(username, userAccountEntity.getPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, new ArrayList<>());
    }
}
