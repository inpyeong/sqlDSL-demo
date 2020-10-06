package me.tozl.querydsldemo.post;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Account2AuditorAware implements AuditorAware<Account2> {
    @Override
    public Optional<Account2> getCurrentAuditor() {
        System.out.println("looking for user");
        return Optional.empty();
    }
}
