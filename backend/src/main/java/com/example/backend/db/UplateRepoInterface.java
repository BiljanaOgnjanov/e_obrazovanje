package com.example.backend.db;

import com.example.backend.models.Uplate;

import java.util.List;

import com.example.backend.models.Dokument;

public interface UplateRepoInterface {
    
    public List<Uplate> mojeUplate(String username);

    public List<Dokument> mojiDokumenti(String username);
}
