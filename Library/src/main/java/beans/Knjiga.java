
package beans;

public class Knjiga {
    private String idK;
    private String slika;
    private String naslov;
    private String autor;
    private String brStr;
    private String godina;
    private String naStanju;

    public String getIdK(){
        return idK;
    }
    public void setIdK(String idK){
        this.idK = idK;
    }

    public String getSlika(){
        return slika;
    }
    public void setSlika(String slika){
        this.slika = slika;
    }
    
    public String getNaslov(){
        return naslov;
    }
    public void setNaslov(String naslov){
        this.naslov = naslov;
    }

    public String getAutor(){
        return autor;
    }
    public void setAutor(String autor){
        this.autor = autor;
    }

    public String getBrStr(){
        return brStr;
    }
    public void setBrStr(String brStr){
        this.brStr = brStr;
    }

    public String getGodina(){
        return godina;
    }
    public void setGodina(String godina){
        this.godina = godina;
    }

    public String getNaStanju(){
        return naStanju;
    }
    public void setNaStanju(String naStanju){
        this.naStanju = naStanju;
    }

}
