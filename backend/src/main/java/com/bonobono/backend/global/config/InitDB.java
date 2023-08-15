package com.bonobono.backend.global.config;

import com.bonobono.backend.member.domain.Authority;
import com.bonobono.backend.member.domain.enumtype.Role;
import com.bonobono.backend.member.repository.AuthorityRepository;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * INSERT INTO role (role) values ('USER');
 * INSERT INTO role (role) values ('ADMIN')
 */

@Component
@RequiredArgsConstructor
public class InitDB {

    private final InitService initService;

    @PostConstruct
    public void init() {

        initService.dbInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final AuthorityRepository authorityRepository;

        public void dbInit() {

            authorityRepository.save(new Authority(Role.ADMIN));
            authorityRepository.save(new Authority(Role.USER));
        }
    }
}