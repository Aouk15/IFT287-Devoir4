package AubergeInn.tables;

import AubergeInn.bdd.ConnexionMongo;
import AubergeInn.tuples.TupleReserver;
import com.mongodb.client.MongoCollection;
import org.bson.Document;


import java.sql.SQLException;
import java.util.Date;

public class TableReserver {
    private final ConnexionMongo cxMongo;

    private MongoCollection<Document> reserverCollection;

    public TableReserver(ConnexionMongo cxMongo)throws SQLException {
        this.cxMongo = cxMongo;
        this.reserverCollection = cxMongo.getDatabase().getCollection("Reserver");
    }

    public void Create(int idclient, int idchambre, Date debut, Date fin) throws SQLException{
        TupleReserver r = new TupleReserver(idclient, idchambre, debut, fin);
        this.reserverCollection.insertOne(r.toDocument());
    }

    public ConnexionMongo getConnexion(){
        return cxMongo;
    }
}
