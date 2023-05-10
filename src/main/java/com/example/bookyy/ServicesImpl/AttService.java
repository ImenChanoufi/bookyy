package com.example.bookyy.ServicesImpl;

import com.example.bookyy.Entites.Attachment;
import com.example.bookyy.Repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class AttService {
    @Autowired
    private AttachmentRepository attachmentRepository;

    public Attachment addAttachmentToReclamation(MultipartFile file)  {
        try {
            Attachment attachment = new Attachment();
            attachment.setData(file.getBytes());
            attachment.setFileName(file.getOriginalFilename());
            attachment.setContentType(file.getContentType());
            attachment.setSize(file.getSize());
            return attachmentRepository.save(attachment);
        }catch (IOException e){
            return null;
        }
    }
}
