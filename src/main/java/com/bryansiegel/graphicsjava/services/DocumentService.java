package com.bryansiegel.graphicsjava.services;

import com.bryansiegel.graphicsjava.models.CurrentEvaluationsModel;
import com.bryansiegel.graphicsjava.repositories.currentEvaluationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class DocumentService {

@Autowired
    private final currentEvaluationsRepository currentEvaluationsRepository;

    public DocumentService(com.bryansiegel.graphicsjava.repositories.currentEvaluationsRepository currentEvaluationsRepository) {
        this.currentEvaluationsRepository = currentEvaluationsRepository;
    }

    public CurrentEvaluationsModel saveFile(MultipartFile file) throws IOException {
        CurrentEvaluationsModel doc = new CurrentEvaluationsModel();
        doc.setFormName(file.getOriginalFilename());
        doc.setType(file.getContentType());
        doc.setData(file.getBytes());
        return currentEvaluationsRepository.save(doc);
    }

    public List<CurrentEvaluationsModel> getFiles() {
        return currentEvaluationsRepository.findAll();
    }

    public CurrentEvaluationsModel getFile(Long id) {
        return currentEvaluationsRepository.findById(id).orElse(null);
    }

    public void deleteFile(Long id) {
        currentEvaluationsRepository.deleteById(id);
    }
}
