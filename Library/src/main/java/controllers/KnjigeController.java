
package controllers;
import beans.Knjiga;
import beans.Korisnik;
import beans.Narudzbina;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import util.dao.KnjigeDAO;
import util.dao.KorisniciDAO;
import util.dao.NarudzbineDAO;
import java.sql.SQLException;
import java.io.Serializable;
import java.util.Collections;

@ManagedBean
@SessionScoped
public class KnjigeController implements Serializable{
    private ArrayList<Knjiga> knjige;
    private ArrayList<Korisnik> korisnici;
    private ArrayList<Narudzbina> narudzbine;
    private boolean Error = true;
    private String errorMessage;
    private String filter_by_author;
    private String filter_by_title;
    private boolean loggedin;
    private Korisnik korisnik;
    private String korisnik_ime;
    private String korisnik_lozinka;
    private boolean korisnik_tip;

    public String getKorisnik_ime(){
        return korisnik_ime;
    }
    
    public void setKorisnik_ime(String korisnik_ime){
        this.korisnik_ime = korisnik_ime;
    }

    public String getKorisnik_lozinka(){
        return korisnik_lozinka;
    }
    
    public void setKorisnik_lozinka(String korisnik_lozinka){
        this.korisnik_lozinka = korisnik_lozinka;
    }

    public boolean getKorisnik_tip(){
        return korisnik_tip;
    }
    
    public void setKorisnik_tip(boolean korisnik_tip){
        this.korisnik_tip = korisnik_tip;
    }
    
    public KnjigeController(){
        try{
            knjige = KnjigeDAO.fetchKnjige();
            korisnici = KorisniciDAO.fetchKorisnici();
            narudzbine = NarudzbineDAO.fetchNarudzbine();
        }catch(SQLException ex){
            Error = true;
            errorMessage = "Error";
        }
    }

    public ArrayList<Knjiga> getKnjige(){
        return knjige;
    }

    public void setKnjige(ArrayList<Knjiga> knjige){
        this.knjige = knjige;
    }

    public ArrayList<Narudzbina> getNarudzbine(){
        return narudzbine;
    }

    public void setNarudzbine(ArrayList<Narudzbina> narudzbina){
        this.narudzbine = narudzbine;
    }

    public ArrayList<Korisnik> getKorisnici(){
        return korisnici;
    }

    public void setKorisnici(ArrayList<Korisnik> korisnici){
        this.korisnici = korisnici;
    }
    
    public boolean getError(){
        return Error;
    }
    
    public void setError(boolean Error){
        this.Error = Error;
    }

    public Korisnik getKorisnik(){
        return korisnik;
    }
    
    public void setKorisnik(Korisnik korisnik){
        this.korisnik = korisnik;
    }
        
    public String getFilter_by_author(){
        return filter_by_author;
    }
    
    public void setFilter_by_author(String filter_by_author){
        this.filter_by_author = filter_by_author;
    }

    public String getFilter_by_title(){
        return filter_by_title;
    }

    public void setLoggedin(boolean loggedin){
        this.loggedin = loggedin;
    }

    public boolean getLoggedin(){
        return loggedin;
    }

    public void setFilter_by_title(String filter_by_title){
        this.filter_by_title = filter_by_title;
    }

    public String getErrorMessage(){
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage){
        this.errorMessage = errorMessage;
    }
    
    public void onRefresh(){
        errorMessage = "";
        //errorMessage += "wow";
        //System.out.println("Refresh called-----------------------------------------------");
    }

    public void filter(){
        //if()
        //System.out.println("Sort called-----------------------------------------------");
    }
    
    public void desc_sort_by_year(){
        //System.out.println("desc_sort_by_year called-----------------------------------------------");
        for(int i=0;i<knjige.size();i++){
            for(int j=i;j<knjige.size();j++){
                if(Integer.parseInt(knjige.get(i).getGodina())<Integer.parseInt(knjige.get(j).getGodina())){
                    Knjiga k = knjige.get(i);
                    knjige.set(i,knjige.get(j));
                    knjige.set(j,k);
                    //errorMessage += "switch";
                }
            }
        }
    }
    public void deleteKnjiga(Knjiga k){
        knjige.remove(k);
    }

    public void buy(Knjiga k){
        if(loggedin == false){
            errorMessage = "User not logged in";
            return;
        }
        else{
            errorMessage = "";
        }
    }

    public String login(){
        if(korisnik_ime.equals("") || korisnik_lozinka.equals("")){
            errorMessage = "Both name and password are required fields";
            return null;
        }
        String tip = "";
        if(korisnik_tip==true){
            tip = "radnik";
        }else{
            tip = "kupac";
        }
        errorMessage = "";
        for(int i=0;i<korisnici.size();i++){
            if(korisnici.get(i).getKorime().equals(korisnik_ime) && korisnici.get(i).getLozinka().equals(korisnik_lozinka) 
                && korisnici.get(i).getTip().equals(tip)){
                loggedin = true;
                korisnik = korisnici.get(i);
                if(tip.equals("kupac")){
                    return "index";
                }
                else{
                    return "worker";
                }
            }
        }
        errorMessage = "Wrong credentials";
        return null;
    }
    
    public void acceptNarudzbina(Narudzbina narudzbina){
        try{
            NarudzbineDAO.accept(narudzbina);
            narudzbine = NarudzbineDAO.fetchNarudzbine();

        }catch(SQLException ex){
            System.out.println(ex);
        }
        
    }
    public String logout(){
        loggedin = false;
        korisnik = null;
        return "index";
    }
}










