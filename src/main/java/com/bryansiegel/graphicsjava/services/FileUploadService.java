package com.bryansiegel.graphicsjava.services;

import com.bryansiegel.graphicsjava.models.CurrentEvaluationsModel;
import com.bryansiegel.graphicsjava.repositories.currentEvaluationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileUploadService {

    @Autowired
    private final currentEvaluationsRepository _currentEvaluationsRepository;

    public FileUploadService(currentEvaluationsRepository _currentEvaluationsRepository) {
        this._currentEvaluationsRepository = _currentEvaluationsRepository;
    }

    public CurrentEvaluationsModel saveEntity( String formName, MultipartFile file) throws IOException {
//       String uploadDir = "~/static/uploads";
//
//        Path uploadPath = Paths.get(uploadDir);
//
//        if (!Files.exists(uploadPath)) {
//            Files.createDirectories(uploadPath);
//        }
//
//        try (InputStream inputStream = file.getInputStream()) {
//            Path filePath = uploadPath.resolve(formName);
//            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
//        } catch (IOException ioe) {
//            throw new IOException("Could not save file: " + formName, ioe);
//        }


        String folder = "/uploads/";
        byte[] bytes = file.getBytes();
        Path path = Paths.get(folder + file.getOriginalFilename());
        Files.write(path, bytes);

        //Save
        CurrentEvaluationsModel currentEvaluationsModel = new CurrentEvaluationsModel();
        currentEvaluationsModel.setFormName(formName);
        currentEvaluationsModel.setFilePath(path.toString());
        return _currentEvaluationsRepository.save(currentEvaluationsModel);
    }

}

