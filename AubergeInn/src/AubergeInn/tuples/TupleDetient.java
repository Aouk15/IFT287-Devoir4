package AubergeInn.tuples;

import org.bson.Document;

public class TupleDetient {

    private int idChambre;
    private int idCommodite;

    public TupleDetient(int idchambre,int idcommodite){
        this.setIdChambre(idchambre);
        this.setIdCommodite(idcommodite);
    }

    public TupleDetient(Document d){
        this.idChambre = d.getInteger("idChambre");
        this.idCommodite = d.getInteger("idCommodite");
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

    public Document toDocument(){
        return (new Document()).append("idChambre",this.idChambre).append("idCommodite",this.idCommodite);
    }
}
