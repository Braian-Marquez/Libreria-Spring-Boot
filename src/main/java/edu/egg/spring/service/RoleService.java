package edu.egg.spring.service;

import edu.egg.spring.entity.Role;
import edu.egg.spring.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    @Transactional
    public void create(Role roleDto) {
        if (roleRepository.existsByName(roleDto.getName())) throw new IllegalArgumentException("Message");
        Role role = new Role();
        role.setName(roleDto.getName());
        roleRepository.save(role);
    }

    @Transactional(readOnly = true)
    public List<Role> getAll() {
        return roleRepository.findAll();
    }
}
