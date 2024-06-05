package com.example.backend.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.backend.db.DokumentiRepo;
import com.example.backend.models.Dokument;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class DokumentiService {

    private final String uploadDir = "../../../../../../../../dokumenti";

    @Autowired
    private DokumentiRepo dokumentiRepo;

    public List<Dokument> mojiDokumenti(String korisnickoIme) {
        return dokumentiRepo.findByKorisnickoIme(korisnickoIme);
    }

    public void postaviDokument(MultipartFile file, String korisnickoIme, String nazivDokumenta, String tipDokumenta) {

        try{
            Path putanja = Paths.get(uploadDir);
            if (!Files.exists(putanja)) {
                Files.createDirectories(putanja);
            }

            String fileName = file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);
            Files.write(filePath, file.getBytes());

            System.out.println("Upload Directory: " + putanja.toString());
            System.out.println("File Path: " + filePath.toString());

            Dokument dokument = new Dokument();
            dokument.setKorisnickoIme(korisnickoIme);
            dokument.setNazivDokumenta(nazivDokumenta);
            dokument.setTipDokumenta(tipDokumenta);
            dokument.setPutanja(filePath.toString());
            dokumentiRepo.save(dokument);

        } catch (IOException e) {
        e.printStackTrace();
        // Handle the exception
    }

        
    }
}
