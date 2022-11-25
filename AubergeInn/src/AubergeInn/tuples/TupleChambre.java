package AubergeInn.tuples;


import org.bson.Document;

public class TupleChambre {
    private int idChambre;
    private String nom_chambre;
    private String type;
    private int prix_base;


    public TupleChambre(Document d) {
        this.idChambre = d.getInteger("idChambre");
        this.nom_chambre = d.getString("nom_chambre");
        this.type = d.getString("type");
        this.prix_base = d.getInteger("prix_base");
    }

    public TupleChambre(int idchambre, String nom_chambre, String type_Lit, int prix) {
        this.setIdChambre(idchambre);
        this.setNom_chambre(nom_chambre);
        this.setType(type_Lit);
        this.setPrix_base(prix);
    }


    public int getIdChambre() {
        return idChambre;
    }

    public void setIdChambre(int idChambre) {
        this.idChambre = idChambre;
    }

    public String getNom_chambre() {
        return nom_chambre;
    }

    public void setNom_chambre(String nom_chambre) {
        this.nom_chambre = nom_chambre;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrix_base() {
        return prix_base;
    }

    public void setPrix_base(int prix_base) {
        this.prix_base = prix_base;
    }

    public Document toDocument() {
        return (new Document()).append("idChambre", this.idChambre).append("nom_chambre", this.nom_chambre).append("type", this.type).append("prix_base", this.prix_base);
    }
}