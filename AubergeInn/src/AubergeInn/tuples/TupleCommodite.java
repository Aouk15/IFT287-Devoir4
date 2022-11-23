package AubergeInn.tuples;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TupleCommodite {
    @Id
    @GeneratedValue
    private long id;

   private int idCommodite;
   private String description;
   private int surplus_prix;

   public TupleCommodite(int idcommodite,String desc,int prix){
       this.setIdCommodite(idcommodite);
       this.setDescription(desc);
       this.setSurplus_prix(prix);

   }

    public int getIdCommodite() {
        return idCommodite;
    }

    public void setIdCommodite(int idCommodite) {
        this.idCommodite = idCommodite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSurplus_prix() {
        return surplus_prix;
    }

    public void setSurplus_prix(int surplus_prix) {
        this.surplus_prix = surplus_prix;
    }
}
