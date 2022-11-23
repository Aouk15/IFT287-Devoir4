package AubergeInn.tuples;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class TupleReserver {
    @Id
    @GeneratedValue
    private long id;
    private int idClient;
    private int idChambre;
    private Date debut;
    private Date fin;

    public TupleReserver(int idclient,int idchambre,Date datedebut,Date datefin){
        this.setIdClient(idclient);
        this.setIdChambre(idchambre);
        this.setDebut(datedebut);
        this.setFin(datefin);
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdChambre() {
        return idChambre;
    }

    public void setIdChambre(int idChambre) {
        this.idChambre = idChambre;
    }

    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }
}
