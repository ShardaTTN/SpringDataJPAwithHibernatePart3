package com.tothenew.sharda.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tothenew.sharda.Entities.Book;


public interface BookRepository extends JpaRepository<Book, Long> {

}
