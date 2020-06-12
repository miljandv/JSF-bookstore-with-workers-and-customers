

package util.dao;
import beans.Korisnik;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import util.DB;

public class KorisniciDAO {
    
        public static ArrayList<Korisnik> fetchKorisnici() throws SQLException {

        ArrayList<Korisnik> lista = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DB.getInstance().getConnection();
            if (con == null) {
                System.out.println("*** Error in database connection ***");
                return null;
            }
            ps = con.prepareStatement("SELECT idK, ime, prezime, korime, lozinka, tip FROM korisnici");

            ResultSet rezultat = ps.executeQuery();
            while (rezultat.next()) {

                Korisnik next = new Korisnik();

                next.setIdK(rezultat.getString("idK"));
                next.setIme(rezultat.getString("ime"));
                next.setPrezime(rezultat.getString("prezime"));
                next.setKorime(rezultat.getString("korime"));
                next.setLozinka(rezultat.getString("lozinka"));
                next.setTip(rezultat.getString("tip"));

                lista.add(next);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println("SQL error -->" + ex.getMessage());
            return null;
        } finally {
            if (ps != null) {
                ps.close();
            }
            DB.getInstance().putConnection(con);
        }
    }
}
