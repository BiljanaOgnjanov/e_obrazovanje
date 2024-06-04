package com.example.backend.db;

import java.util.List;
import com.example.backend.models.MojiPredmetiDTO;

public interface PredmetiRepoInterface {

    public List<MojiPredmetiDTO> predmeti(String username);
}
