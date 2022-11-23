package AubergeInn.tuples;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TupleDetient {

    @Id
    @GeneratedValue
    private long id;

    private int idChambre;
    private int idCommodite;

    public TupleDetient(int idchambre,int idcommodite){
        this.setIdChambre(idchambre);
        this.setIdCommodite(idcommodite);
    }

    public int getIdChambre() {
        return idChambre;
    }

    public void setIdChambre(int idChambre) {
        this.idChambre = idChambre;
    }

    public int getIdCommodite() {
        return idCommodite;
    }

    public void setIdCommodite(int idCommodite) {
        this.idCommodite = idCommodite;
    }
}
