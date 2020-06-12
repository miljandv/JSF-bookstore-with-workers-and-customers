

package util.dao;
import beans.Knjiga;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import util.DB;

public class KnjigeDAO {
    
        public static ArrayList<Knjiga> fetchKnjige() throws SQLException {

        ArrayList<Knjiga> lista = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DB.getInstance().getConnection();
            if (con == null) {
                System.out.println("*** Error in database connection ***");
                return null;
            }
            ps = con.prepareStatement("SELECT idK, naslov, slika, autor, brStr, godina, naStanju FROM knjige");

            ResultSet rezultat = ps.executeQuery();
            while (rezultat.next()) {

                Knjiga next = new Knjiga();

                next.setIdK(rezultat.getString("idK"));
                next.setNaslov(rezultat.getString("naslov"));
                next.setSlika(rezultat.getString("slika"));
                next.setAutor(rezultat.getString("autor"));
                next.setBrStr(rezultat.getString("brStr"));
                next.setGodina(rezultat.getString("godina"));
                next.setNaStanju(rezultat.getString("naStanju"));

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
