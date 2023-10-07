package org.Aome.repositories;

import org.Aome.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorsRepository extends JpaRepository<Author, Integer> {
}
