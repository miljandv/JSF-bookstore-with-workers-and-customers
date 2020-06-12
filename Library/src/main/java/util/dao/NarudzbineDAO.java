

package util.dao;
import beans.Narudzbina;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import util.DB;

public class NarudzbineDAO {
    
        public static ArrayList<Narudzbina> fetchNarudzbine() throws SQLException {

        ArrayList<Narudzbina> lista = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DB.getInstance().getConnection();
            if (con == null) {
                System.out.println("*** Error in database connection ***");
                return null;
            }
            ps = con.prepareStatement("SELECT idN, kupac, knjiga, kolicina, status FROM narudzbine");

            ResultSet rezultat = ps.executeQuery();
            while (rezultat.next()) {

                Narudzbina next = new Narudzbina();

                next.setIdN(rezultat.getString("idN"));
                next.setKupac(rezultat.getString("kupac"));
                next.setKnjiga(rezultat.getString("knjiga"));
                next.setKolicina(rezultat.getString("kolicina"));
                next.setStatus(rezultat.getString("status"));

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

    public static void accept(Narudzbina narudzbina) throws SQLException {

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DB.getInstance().getConnection();
            if (con == null) {
                System.out.println("*** Error in database connection ***");
                return;
            }
            ps = con.prepareStatement("update narudzbine set status='prihvaceno' where idN=?");
            ps.setString(1,narudzbina.getIdN());

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQL error -->" + ex.getMessage());
            return;
        } finally {
            if (ps != null) {
                ps.close();
            }
            DB.getInstance().putConnection(con);
        }
    }
}
