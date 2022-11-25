package AubergeInn.tables;
import AubergeInn.bdd.ConnexionMongo;
import AubergeInn.tuples.TupleChambre;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.conversions.Bson;


import java.sql.*;
import java.util.List;

public class TableChambre {

    private final ConnexionMongo cxMongo;
    MongoCollection<Document> chambresCollection;

    public TableChambre(ConnexionMongo cxMongo) {
        this.cxMongo = cxMongo;
        this.chambresCollection = cxMongo.getDatabase().getCollection("Chambres");

    }

    public ConnexionMongo getConnexion(){
        return cxMongo;
    }

    public boolean Existe(int id) throws  SQLException{
        return this.chambresCollection.find(Filters.eq("idChambre",id)).first() != null;
    }

    public TupleChambre getChambre(int id) throws SQLException{
        Document c = (Document)this.chambresCollection.find(Filters.eq("idChambre",id)).first();
        return c != null ? new TupleChambre(c) : null;


    }

    public void Create(int idchambre,String nom, String type, int prix_base) throws SQLException{
        TupleChambre c = new TupleChambre(idchambre,nom,type,prix_base);
        this.chambresCollection.insertOne(c.toDocument());
    }

    public void Update(int idchambre,String nom_chambre,String type_lit,int prix_base)throws SQLException{
        this.chambresCollection.updateOne(Filters.eq("idChambre",idchambre),
                Updates.combine(new Bson[]{
                        Updates.set("idChambre",idchambre),
                        Updates.set("nom_chambre",nom_chambre),
                        Updates.set("type",type_lit),
                        Updates.set("prix_base",prix_base)
                }));
    }

    public boolean Delete(int idchambre)throws SQLException{
        return this.chambresCollection.deleteOne(Filters.eq("idChambre",idchambre)).getDeletedCount() > 0L;
    }

    public void afficher() throws SQLException{
        MongoCursor<Document> chambres = this.chambresCollection.find().iterator();
        try{
            while (chambres.hasNext()){
                TupleChambre c = new TupleChambre((Document)chambres.next());
                System.out.println(c.toString());
            }
        }finally {
            chambres.close();
        }
    }
}
