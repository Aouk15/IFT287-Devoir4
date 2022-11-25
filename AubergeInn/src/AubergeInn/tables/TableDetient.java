package AubergeInn.tables;

import AubergeInn.bdd.ConnexionMongo;
import AubergeInn.tuples.TupleClient;
import AubergeInn.tuples.TupleDetient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.sql.SQLException;

import static com.mongodb.client.model.Filters.and;


public class TableDetient {

    private final ConnexionMongo cxMongo;
    private MongoCollection<Document> detientCollection;

    public TableDetient(ConnexionMongo cxMongo) {
        this.cxMongo = cxMongo;
        this.detientCollection = cxMongo.getDatabase().getCollection("Detient");
    }

    public ConnexionMongo getConnexion(){
        return cxMongo;
    }

    public boolean Existe(int idchambre, int idcommodite) throws  SQLException{
        return this.detientCollection.find(and(Filters.eq("idChambre",idchambre), Filters.eq("idCommodite",idcommodite))).first() != null;
    }

    public void Inclure(int idchambre, int idcommodite){
        TupleDetient d = new TupleDetient(idchambre, idcommodite);
        this.detientCollection.insertOne(d.toDocument());
    }

    public boolean Exclure(int idchambre, int idcommodite){
        return this.detientCollection.deleteOne(and(Filters.eq("idChambre",idchambre), Filters.eq("idCommodite",idcommodite))).getDeletedCount() > 0L;
    }
}
