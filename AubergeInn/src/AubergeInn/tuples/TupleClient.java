package AubergeInn.tuples;

import org.bson.Document;

public class TupleClient {
    private int idClient;
    private String prenom;
    private String nom;
    private int age;


    public TupleClient(Document d){
        this.idClient = d.getInteger("idClient");
        this.prenom = d.getString("prenom");
        this.nom = d.getString("nom");
        this.age = d.getInteger("age");
    }
    public TupleClient (int idClient,String prenom,String nom,int age){
        this.setIdClient(idClient);
        this.setPrenom(prenom);
        this.setNom(nom);
        this.setAge(age);
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Document toDocument(){
        return (new Document()).append("idClient",this.idClient).append("prenom",this.prenom).append("nom",this.nom).append("age",this.age);
    }

}