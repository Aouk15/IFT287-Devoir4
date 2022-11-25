package AubergeInn.tables;


import AubergeInn.bdd.ConnexionMongo;
import AubergeInn.tuples.TupleClient;
import AubergeInn.tuples.TupleCommodite;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.sql.SQLException;
import java.util.List;

public class TableCommodite {
    private final ConnexionMongo cxMongo;
    private MongoCollection<Document> commoditeCollection;

    public TableCommodite(ConnexionMongo cxMongo) {
        this.cxMongo = cxMongo;

        this.commoditeCollection = cxMongo.getDatabase().getCollection("Commodites");
    }

    public ConnexionMongo getConnexion(){
        return cxMongo;
    }

    public boolean Existe(int id) throws  SQLException{

        return this.commoditeCollection.find(Filters.eq("idCommodite",id)).first() != null;
    }

    public TupleCommodite getCommodite(int id) throws SQLException{

        Document d = (Document)this.commoditeCollection.find(Filters.eq("id",id)).first();
        return d != null ? new TupleCommodite(d) : null;
    }


    public void Create(int idcommodite,String description,int surplus_prix) throws SQLException{
        TupleCommodite commodite = new TupleCommodite(idcommodite,description,surplus_prix);
        this.commoditeCollection.insertOne(commodite.toDocument());
    }

    public void Update(int idcommodite,String description,int surplus_prix)throws SQLException{
        this.commoditeCollection.updateOne(Filters.eq("idCommodite",idcommodite),
                Updates.combine(new Bson[]{
                        Updates.set("idCommodite",idcommodite),
                        Updates.set("description",description),
                        Updates.set("surplus_prix",surplus_prix)}));
    }

    public boolean Delete(int idcommodite)throws SQLException{
        return this.commoditeCollection.deleteOne(Filters.eq("idCommodite",idcommodite)).getDeletedCount() > 0L;
    }

    public void afficher() throws SQLException {
        MongoCursor<Document> commodites = this.commoditeCollection.find().iterator();

        try {
            while (commodites.hasNext()) {
                TupleCommodite c = new TupleCommodite((Document) commodites.next());
                System.out.println(c.toString());
            }

        } finally {
            commodites.close();
        }

    }


}
