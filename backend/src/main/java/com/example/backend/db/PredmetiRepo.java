package com.example.backend.db;


import java.util.List;

import com.example.backend.models.MojiPredmetiDTO;


public class PredmetiRepo implements PredmetiRepoInterface {

    @Override
    public List<MojiPredmetiDTO> predmeti(String username) {
       /* try (Connection conn = DB.source().getConnection();
                PreparedStatement stmt = conn.prepareStatement(
                        "select * from pracenje_predmeta inner join polaganje_ispita using(id_polaganja_ispita) inner join predmeti using(id_predmeta) where korisnicko_ime =?")) {

            stmt.setString(1, username);
            List<MojiPredmetiDTO> svaPracenja = new ArrayList<>();
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                
                svaPracenja.add(new MojiPredmetiDTO(
                    rs.getInt("id_predmeta"),
                    rs.getInt("id_polaganja_ispita"),
                    rs.getInt("id_pracenja_predmeta"),
                    rs.getString("korisnicko_ime"),
                    rs.getInt("skolska_godina"),
                    rs.getString("rezultati_predispitnih_obaveza"),
                    rs.getInt("ukupno_osvojeno_poena"),
                    rs.getInt("polozen_ispit"),
                    rs.getInt("ocena"),
                    rs.getString("ime_predmeta"),
                    rs.getInt("godina"),
                    rs.getInt("broj_kredita"),
                    rs.getString("opis_predmeta"),
                    rs.getString("predispitne_obaveze")));
 
            }
            return svaPracenja;

        } catch (SQLException e) {
            e.printStackTrace();
        }
*/
        return null;
    }
    
}
