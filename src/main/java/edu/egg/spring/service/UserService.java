package edu.egg.spring.service;

import edu.egg.spring.entity.User;
import edu.egg.spring.repository.RoleRepository;
import edu.egg.spring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final RoleRepository roleRepository;

    @Transactional
    public void create(User dto) {
        if (userRepository.existsByEmail(dto.getEmail()))
            throw new IllegalArgumentException("There is already a user associated with the email entered");

        User user = new User();

        user.setEmail(dto.getEmail());
        user.setPassword(encoder.encode(dto.getPassword()));

        if (dto.getRole() == null)
            user.setRole(roleRepository.findByName("USER").orElseThrow(() -> new IllegalArgumentException("Error")));

        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("There is no user associated with the email entered"));
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getRole().getName());

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attributes.getRequest().getSession(true);

        session.setAttribute("id", user.getId());
        session.setAttribute("email", user.getEmail());
        session.setAttribute("role", user.getRole().getName());

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), singletonList(authority));
    }
}

