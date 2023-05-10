package com.example.bookyy.Controllers;

import com.example.bookyy.Entites.Attachment;
import com.example.bookyy.Entites.Book;
import com.example.bookyy.Entites.Categories;

import com.example.bookyy.Entites.Rating;
import com.example.bookyy.Repository.AttachmentRepository;
import com.example.bookyy.Repository.BookRepo;
import com.example.bookyy.ServicesImpl.AttService;
import com.example.bookyy.ServicesImpl.BookImpl;
import com.example.bookyy.ServicesImpl.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("Book")
@CrossOrigin("*")
public class BookController {

   final private BookImpl bookService;

    private RatingService ratingService;
    @Autowired
    BookRepo bookRepo;
    @Autowired
    AttService attService;
    @Autowired
    AttachmentRepository attachmentRepository;

    public BookController(BookImpl bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/all")
    public List<Book> affichertous()
    {
        return bookService.getAll();
    }


    @GetMapping("/allApprouved")
    public List<Book> getAllApprouvedBook()
    {
        return bookService.getAllApprouvedBook();
    }
    @GetMapping("/allNotApprouved")
    public List<Book> getAllNotApprouvedBook()
    {
        return bookService.getAllNotApprouvedBook();
    }
    @GetMapping("/allOnHold")
    public List<Book> getAllallOnHoldBook()
    {
        return bookService.getAllallOnHoldBook();
    }
    @GetMapping("/find/{cat}")
    public List<Book> afficherparcategories(@PathVariable("cat") Categories Cat ){
        return bookService.getbycat(Cat);
    }
    @GetMapping("/find/id/{id}")
    public Book afficherbook(@PathVariable("id") int id ){
        return bookService.getBybookId(id);
    }
    @PostMapping("/add")
    public Book addbook(@RequestBody Book book){
        return bookService.add(book);
    }
    @PutMapping("/update/{id}")
    public  Book updatebook (@RequestBody Book book , @PathVariable("id") int id){
        return bookService.update(book,id);
    }
    @DeleteMapping("/delete/{id}")
    public void deletebook(@PathVariable("id") int id){
        bookService.remove(id);
    }

    @GetMapping("/approuve/{id}")
    public void approuveBook(@PathVariable("id") int id){
        bookService.approuveBook(id);
    }
    @GetMapping("/NotApprouve/{id}")
    public void NotApprouveBook(@PathVariable("id") int id){
        bookService.NotApprouveBook(id);
    }
    @PostMapping(path = "/file/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Attachment addfile(@RequestParam(required = false) MultipartFile file, @PathVariable int id){
        Book book = bookRepo.findById(id).get();
        Attachment attachment=new Attachment();
        if(file!=null)
        {
            attachment= attService.addAttachmentToReclamation(file);
            attachment.setBook(book);
        }

        return attachmentRepository.save(attachment);
    }

    @GetMapping("getAllFile")
    public List<Attachment> getAll(){
        return attachmentRepository.findAll();
    }
    @GetMapping("rate/{score}/{id}")
    public void ratebook(@PathVariable("score") int score ,@PathVariable("id") int id){
        Book book = new Book();
        book= bookRepo.findById(id).get();
        book.setRate(score);
        bookRepo.save(book);

    }
}
