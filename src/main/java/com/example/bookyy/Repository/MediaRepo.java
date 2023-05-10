package com.example.bookyy.Repository;

import com.example.bookyy.Entites.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MediaRepo extends JpaRepository<Media,Long> {
    Media findByDocName(String doc);
    @Query(value = "select * from Media m join Book b on m.book_idbook = :id",nativeQuery = true)
    Media findByBookidone(@Param("id") int id);
}
