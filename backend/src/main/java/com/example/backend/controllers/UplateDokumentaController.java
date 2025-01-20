package com.example.backend.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.backend.models.Dokument;
import com.example.backend.models.Placanja;
import com.example.backend.models.Uplate;
import com.example.backend.services.DokumentiService;
import com.example.backend.services.PlacanjaService;
import com.example.backend.services.UplateService;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "http://localhost:4200")
public class UplateDokumentaController {

    @Autowired
    private UplateService uplateService;

    @Autowired
    private PlacanjaService placanjaService;

    @Autowired
    private DokumentiService dokumentiService;

    @GetMapping("/uplate/{username}")
    public List<Uplate> uplateKorisnika(@PathVariable("username") String username) {
        System.out.println(username);
        return uplateService.getUplateByUsername(username);
    }
    
    @GetMapping("/placanja/{username}")
    public List<Placanja> placanjaKorisnika(@PathVariable("username") String username) {
        return placanjaService.getPlacanjaByUsername(username);
    }
    
    @GetMapping("/dokumenti/{username}")
    public List<Dokument> dokumentiKorisnika(@PathVariable("username") String username) {
        return  dokumentiService.mojiDokumenti(username);
    }

    @PostMapping("/overi-semestar")
    public ResponseEntity<String> overiSemestar(@RequestParam String korisnickoIme) {
        String result = placanjaService.overiSemestar(korisnickoIme);
        if (result.equals("Semestar uspešno overen.")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
    }
    
    
    @PostMapping("dokumenti/upload")
    public void uploadDocument(@RequestParam("file") MultipartFile file,
            @RequestParam("korisnickoIme") String korisnickoIme,
            @RequestParam("nazivDokumenta") String nazivDokumenta,
            @RequestParam("tipDokumenta") String tipDokumenta) {
        System.out.println(korisnickoIme);
        System.out.println(nazivDokumenta);
        System.out.println(tipDokumenta);

        dokumentiService.postaviDokument(file, korisnickoIme, nazivDokumenta, tipDokumenta);
    }

    @DeleteMapping("dokumenti/obrisi/{idDokumenta}")
    public ResponseEntity<String> obrisiDokument(@PathVariable int idDokumenta) {
        boolean isRemoved = dokumentiService.obrisiDokument(idDokumenta);
        if (isRemoved) {
            return ResponseEntity.ok("Dokument obrisan");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dokument nije pronadjen, ili ne moze biti obrisan");
        }
    }
}
