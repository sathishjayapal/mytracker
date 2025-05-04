package me.sathish.trackgarmin.audit;

import java.math.BigInteger;
import java.util.Optional;

import me.sathish.trackgarmin.entities.User;
import me.sathish.trackgarmin.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
//TODO : Make changes to work of User Object
@Component("auditAwareImpl")
public class GarminMSAuditorImpl implements AuditorAware<User> {
    @Autowired
    UserRepository userRepository;
    public GarminMSAuditorImpl() {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getCurrentAuditor() {
        return userRepository.findById(Long.valueOf(1L)) ;
    }
}
