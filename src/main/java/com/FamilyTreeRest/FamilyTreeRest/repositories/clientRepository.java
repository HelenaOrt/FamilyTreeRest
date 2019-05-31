package com.FamilyTreeRest.FamilyTreeRest.repositories;

import com.FamilyTreeRest.FamilyTreeRest.entities.WebUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface clientRepository extends JpaRepository<WebUser, Long> {

	Optional<WebUser> findByNameIgnoreCase(String username);
}
