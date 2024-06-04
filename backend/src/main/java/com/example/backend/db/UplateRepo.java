package com.example.backend.db;


import java.util.List;

import com.example.backend.models.Dokument;
import com.example.backend.models.Uplate;


public class UplateRepo implements UplateRepoInterface {

    @Override
    public List<Uplate> mojeUplate(String student) {
        /*try (Connection conn = DB.source().getConnection();
                PreparedStatement stmt = conn.prepareStatement(
                        "select * from uplate where korisnicko_ime = ?")) {

            stmt.setString(1, student);
            List<Uplate> sveUplate = new ArrayList<>();
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                
                sveUplate.add(new Uplate(
                    rs.getInt("id_uplate"),
                    rs.getString("svrha_uplate"),
                    rs.getString("korisnicko_ime"),
                    rs.getInt("iznos"),
                    rs.getString("datum")));
 
            }
            return sveUplate;

        } catch (SQLException e) {
            e.printStackTrace();
        }
*/
        return null;
    }

    @Override
    public List<Dokument> mojiDokumenti(String student) {
       /* try (Connection conn = DB.source().getConnection();
                PreparedStatement stmt = conn.prepareStatement(
                        "select * from dokumenta where korisnicko_ime = ?")) {

            stmt.setString(1, student);
            List<Dokument> sviDokumenti = new ArrayList<>();
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                
                sviDokumenti.add(new Dokument(
                    rs.getInt("id_dokumenta"),
                    rs.getString("korisnicko_ime"),
                    rs.getString("naziv_dokumenta"),
                    rs.getString("tip_dokumenta"), 
                    rs.getString("putanja")));
 
            }
            return sviDokumenti;

        } catch (SQLException e) {
            e.printStackTrace();
        }
*/
        return null;
    }
    
    
}
