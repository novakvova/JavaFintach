package org.example.seeder;

import lombok.RequiredArgsConstructor;
import org.example.entities.RoleEntity;
import org.example.entities.UserEntity;
import org.example.entities.UserRoleEntity;
import org.example.repository.RoleRepository;
import org.example.repository.UserRepository;
import org.example.repository.UserRoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserSeeder {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public void seed() {
        if(userRepository.count()==0) {
            var admin = new UserEntity();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));

            admin = userRepository.save(admin);

            RoleEntity adminRole = roleRepository.findByName("ADMIN").orElseThrow();
            UserRoleEntity ur = new UserRoleEntity(null, admin, adminRole);
            userRoleRepository.save(ur);
            System.out.println("---Admin user seeded---");
        }
    }
}
