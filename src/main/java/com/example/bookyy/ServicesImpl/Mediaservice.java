package com.example.bookyy.ServicesImpl;

import com.example.bookyy.Entites.Media;
import com.example.bookyy.Repository.MediaRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Mediaservice {
    final private MediaRepo repo;



    public Mediaservice(MediaRepo repo) {
        this.repo = repo;
    }




    public Media update(Media s) {
        return repo.save(s);
    }


    public List<Media> getAll() {
        return repo.findAll();
    }


    public Media getById(long id) {
        return repo.findById(id).orElse(null);
    }


    public void remove(long id) {
        repo.deleteById(id);
    }

    public  Media findByDocName(String doc){
        return repo.findByDocName(doc);
    }
    public Media findByBookid(int id){return repo.findByBookidone(id);}
}
