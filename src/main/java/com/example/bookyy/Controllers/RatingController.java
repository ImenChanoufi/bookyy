package com.example.bookyy.Controllers;

import com.example.bookyy.Entites.Book;
import com.example.bookyy.Entites.Rating;
import com.example.bookyy.Entites.Response;
import com.example.bookyy.Entites.User;
import com.example.bookyy.Repository.BookRepo;
import com.example.bookyy.Repository.RatingRepository;
import com.example.bookyy.ServicesImpl.BookImpl;
import com.example.bookyy.ServicesImpl.RatingService;
import com.example.bookyy.ServicesImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rating")
@CrossOrigin("*")
public class RatingController {

    @Autowired
    private RatingService ratingService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private BookImpl bookimp;

    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    BookRepo bookRepo;

    @PostMapping("/User/{idUser}/addRating/book/{idBook}")
    @ResponseBody
    public String addRatingBook(@RequestBody Rating rating, @PathVariable("idUser") Long idUser, @PathVariable("idBook") int idBook)
    {
        Book ba = bookimp.getBybookId(idBook);
        User user = userService.getById(idUser);
        rating.setBook(ba);
        rating.setUser(user);
        rating = ratingService.saveRating(rating);
        return "Rating add successful";
    }
// Average rating of the book

  /*  @GetMapping("book/avgrate")
    public ResponseEntity<Response> avgRatingOfBook(@RequestParam int idBook){
        double rate = bookimp.avgRatingOfBook(idBook);
        if(rate>0.0)
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Avg rate", (int) rate));
        else

            return ResponseEntity.status(HttpStatus.OK).body(new Response("Avg rate", 0 ));

    }*/

//Books sorted by rating
    /* @GetMapping("books/sortbyrate")
     public  Collection<Book> sortBookByRate(){
     List<Rating> ratings = ratingRepository.findAll();
         Map<Integer, List<Float>> ratingbook=  ratings.stream().collect(Collectors.groupingBy(o->o.getBook().getIdbook(), Collectors.mapping(Rating::getNote, Collectors.toList())));
         Map<Integer, Float> averageRatingsMap = new HashMap<>();

         for (Map.Entry<Integer, List<Float>> entry : ratingbook.entrySet()) {
             int bookId = entry.getKey();
             List<Float> rtngs = entry.getValue();
             float sum = 0;
             for (Float rating : rtngs) {
                 sum += rating;
             }
             float average = sum / rtngs.size();
             averageRatingsMap.put(bookId, average);
         }
         List<Map.Entry<Integer, Float>> entries = new ArrayList<>(averageRatingsMap.entrySet());

         Collections.sort(entries, new Comparator<Map.Entry<Integer, Float>>() {
             @Override
             public int compare(Map.Entry<Integer, Float> e1, Map.Entry<Integer, Float> e2) {
                 return e2.getValue().compareTo(e1.getValue()); // Sort in descending order
             }
         });

         Map<Integer, Float> sortedMap = new LinkedHashMap<>();
         for (Map.Entry<Integer, Float> entry : entries) {
             sortedMap.put(entry.getKey(), entry.getValue());


}
         LinkedHashMap<Integer, Book> bookMap = new LinkedHashMap<>();

         for (int idBook : sortedMap.keySet()) {
             // Retrieve the book object from the database using the idBook
             Book book = bookRepo.findById(idBook).orElse(null);

             if (book != null) {
                 // Add the book to the LinkedHashMap, preserving the order of the keys from the original TreeMap
                 bookMap.put(idBook, book);
             }
         }

         Collection<Book> books = bookMap.values();
         return books;
     }
*/
           @GetMapping("books/sortbyrate")
       public Collection<Book> sortBookByRate() {
           List<Rating> ratings = ratingRepository.findAll();
              Map<Integer, Double> averageRatingsMap = ratings.stream().collect(Collectors.groupingBy(r -> r.getBook().getIdbook(), Collectors.averagingDouble(Rating::getNote)
            ));
              LinkedHashMap<Integer, Book> bookMap = averageRatingsMap.entrySet().stream().sorted(Map.Entry.<Integer, Double>comparingByValue().reversed())
            .map(e -> bookRepo.findById(e.getKey()).orElse(null))
            .filter(Objects::nonNull)
            .collect(Collectors.toMap(Book::getIdbook, b -> b, (b1, b2) -> b1, LinkedHashMap::new));
              return bookMap.values();
}

}
