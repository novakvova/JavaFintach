package org.example.seeder;

import lombok.AllArgsConstructor;
import org.example.entities.RoleEntity;
import org.example.repository.RoleRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class RoleSeeder {
    private final RoleRepository roleRepository;

    public void seed() {
        if(roleRepository.count()==0) {
            RoleEntity admin = new RoleEntity(null, "ADMIN");
            RoleEntity user = new RoleEntity(null, "USER");
            roleRepository.saveAll(List.of(admin, user));
            System.out.println("---Roles seeded---");
        }
    }
}
