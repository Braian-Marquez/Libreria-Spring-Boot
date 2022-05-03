package edu.egg.spring.service;

import edu.egg.spring.entity.User;
import edu.egg.spring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Collections.emptyList;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Transactional
    public void create(User dto) {
        if (userRepository.existsByEmail(dto.getEmail()))
            throw new IllegalArgumentException("There is already a user associated with the email entered");

        User user = new User();

        user.setEmail(dto.getEmail());
        user.setPassword(encoder.encode(dto.getPassword()));

        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("There is no user associated with the email entered"));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), emptyList());
    }
}
