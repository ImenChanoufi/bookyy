package com.example.bookyy.Repository;

import com.example.bookyy.Entites.Book;
import com.example.bookyy.Entites.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepo extends JpaRepository <Book,Integer> {
@Query(value = "select b from Book  b  where b.categories= :cat")
 List<Book> findBookparcategories(@Param("cat") Categories cat);

 @Query( value = "select avg(rating) from rating where idbook=:id", nativeQuery = true)
 double avgRateOfBook(int id);


}
