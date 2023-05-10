package com.example.bookyy.ServicesImpl;

import com.example.bookyy.Entites.Book;
import com.example.bookyy.Entites.Categories;
import com.example.bookyy.Entites.Rating;
import com.example.bookyy.Repository.BookRepo;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookImpl  {

   final private BookRepo repo;

    public BookImpl(BookRepo repo) {
        this.repo = repo;
    }


    public Book add(Book s) {
        s.setIsPublic(0);
        s.setStatus("On Hold");
        return repo.save(s);
    }


    public Book update(Book s , int id) {
        repo.deleteById(id);
        return repo.save(s);
    }
    public void approuveBook(int id ){
        Book book = repo.findById(id).get();
        book.setStatus("Approuved");
        book.setIsPublic(1);
        repo.save(book);
    }
    public void NotApprouveBook(int id ){
        Book book = repo.findById(id).get();
        book.setStatus("Not Approuved");
        book.setIsPublic(2);
        repo.save(book);
    }


    public List<Book> getAllApprouvedBook(){
        List<Book> books = new ArrayList<>();
        List<Book> booksApprouved = new ArrayList<>();
        books =  repo.findAll();
        for (Book book : books){
            if(book.getIsPublic()==1){
                booksApprouved.add(book);
            }
        }
        return booksApprouved;
    }

    public List<Book> getAllNotApprouvedBook(){
        List<Book> books = new ArrayList<>();
        List<Book> booksNotApprouved = new ArrayList<>();
        books =  repo.findAll();
        for (Book book : books){
            if(book.getIsPublic()==2){
                booksNotApprouved.add(book);
            }
        }
        return booksNotApprouved;
    }
   public List<Book> getAllallOnHoldBook(){
        List<Book> books = new ArrayList<>();
        List<Book> booksNotApprouved = new ArrayList<>();
        books =  repo.findAll();
        for (Book book : books){
            if(book.getIsPublic()==0){
                booksNotApprouved.add(book);
            }
        }
        return booksNotApprouved;
    }


    public List<Book> getAll() {
        return repo.findAll();
    }


    public Book getBybookId(int id) {
        return repo.findById(id).orElse(null);
    }


    public void remove(int id) {
                repo.deleteById(id);
    }
    public  List<Book> getbycat(Categories cat){
       return repo.findBookparcategories(cat);
    }


   public double avgRatingOfBook(int idbook){
        double rate=0.0;
        try {
            rate = repo.avgRateOfBook(idbook);
        }catch(Exception e)
        {
        }
        return rate;
    }

   public List<Book> sortBookByRate(){
       List<Book> books = getAll();
       System.out.println("all books:"+books);
       List<Book> sortBook = books.stream().sorted((book1,book2)->(avgRatingOfBook(book1.getIdbook())<avgRatingOfBook(book2.getIdbook()))?1:(avgRatingOfBook(book1.getIdbook())>avgRatingOfBook(book2.getIdbook()))?-1:0).collect(Collectors.toList());
       System.out.println("After sorting:"+sortBook);
       return sortBook;
   }

   }
