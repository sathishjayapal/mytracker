package me.sathish.garmindatainitializer.config;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class SecurityUserDetailsService implements UserDetailsService {
    /**
     * This method is used to load user details by username.
     *
     * @param username the username of the user
     * @return UserDetails object containing user information
     */
    @Override
    public UserDetails loadUserByUsername(String username) {

        Optional<UserDetails> userDetails = Optional.of(new User(
                username,
                "{noop}" + System.getenv("USER_PASSWORD"),
                true,
                true,
                true,
                true,
                List.of(new SimpleGrantedAuthority("ROLE_USER"))));
        return userDetails.get();
    }
}
