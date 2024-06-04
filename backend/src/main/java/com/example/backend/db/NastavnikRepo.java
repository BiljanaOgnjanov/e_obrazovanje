package com.example.backend.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.models.Nastavnik;


@Repository
public interface NastavnikRepo extends JpaRepository<Nastavnik, String> {}




    //@Override
    //public Nastavnik prijava(Korisnik korisnik) {
    /*    try (Connection conn = DB.source().getConnection();
                PreparedStatement stmt = conn.prepareStatement(
                        "select * from korisnici inner join nastavnici using(korisnicko_ime) where korisnicko_ime = ? and lozinka = ?")) {

            stmt.setString(1, korisnik.getKorisnicko_ime());
            stmt.setString(2, korisnik.getLozinka());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Nastavnik(
                        rs.getString("korisnicko_ime"),
                        rs.getString("lozinka"),
                        rs.getString("ime"),
                        rs.getString("prezime"), 
                        rs.getString("pol"),
                        rs.getString("telefon"),
                        rs.getString("profilna_slika"),
                        rs.getString("status"),
                        rs.getString("katedra"), 
                        rs.getString("uloga"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
*/
       // return null;
    //}
    