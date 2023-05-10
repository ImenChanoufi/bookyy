package com.example.bookyy.ServicesImpl;

import com.example.bookyy.Entites.Rating;
import com.example.bookyy.Repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    public Rating saveRating(Rating rating) {
        return ratingRepository.save(rating);
    }
    public Rating modifRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    public void deleteRating(Long id) {
        try {
            ratingRepository.deleteById(id);
        }
        catch(
                EmptyResultDataAccessException e)

        {
            System.out.println("doesn't exist");
        }

    }
}
