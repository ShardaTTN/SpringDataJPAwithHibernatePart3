package com.tothenew.sharda.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tothenew.sharda.Entities.Author;



public interface AuthorRepository extends JpaRepository<Author, Integer> {
}