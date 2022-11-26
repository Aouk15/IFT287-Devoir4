package AubergeInn.tuples;

import org.bson.Document;

import java.util.Date;

public class TupleReserver {
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

    public TupleReserver(Document d){
        this.idClient = d.getInteger("idClient");
        this.idChambre = d.getInteger("idChambre");
        this.debut = d.getDate("debut");
        this.fin = d.getDate("fin");
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

    public Document toDocument(){
        return (new Document()).append("idClient",this.idClient).append("idChambre",this.idChambre).append("debut",this.debut).append("fin",this.fin);
    }
}
