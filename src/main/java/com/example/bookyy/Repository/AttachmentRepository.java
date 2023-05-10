package com.example.bookyy.Repository;

import com.example.bookyy.Entites.Attachment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Repository
public interface AttachmentRepository  extends JpaRepository<Attachment, Long> {

}
