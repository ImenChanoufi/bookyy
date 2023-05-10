package com.example.bookyy.Repository;


import com.example.bookyy.Entites.ERole;
import com.example.bookyy.Entites.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role>  findByName(ERole name);
}
