package AubergeInn.tables;

import AubergeInn.bdd.ConnexionMongo;
import AubergeInn.tuples.TupleClient;
import AubergeInn.tuples.TupleReserver;


import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.sql.*;

public class TableClient {

    private final ConnexionMongo cxMongo;
    private MongoCollection<Document> clientCollection;


    public TableClient(ConnexionMongo cxMongo) {
        this.cxMongo = cxMongo;
        this.clientCollection = cxMongo.getDatabase().getCollection("Clients");
    }


    public ConnexionMongo getConnexion(){
        return cxMongo;
    }

    public boolean Existe(int id) throws  SQLException{

        return this.clientCollection.find(Filters.eq("idClient",id)).first() != null;
    }

    public TupleClient getClient(int id) throws SQLException{

        Document d = (Document)this.clientCollection.find(Filters.eq("id",id)).first();
        return d != null ? new TupleClient(d) : null;
    }

    public void Create(int id,String prenom, String nom,int age) throws SQLException{
        TupleClient c = new TupleClient(id,prenom,nom,age);
        this.clientCollection.insertOne(c.toDocument());
    }

    public void Update(int idclient,String prenom,String nom,int age)throws SQLException{
       this.clientCollection.updateOne(Filters.eq("idClient",idclient),
               Updates.combine(new Bson[]{
                       Updates.set("idClient",idclient),
                       Updates.set("prenom",prenom),
                       Updates.set("nom",nom),
                       Updates.set("age",age)}));

    }

    public boolean Delete(int idclient)throws SQLException{
        return this.clientCollection.deleteOne(Filters.eq("idClient",idclient)).getDeletedCount() > 0L;
    }

    public void afficher() throws SQLException{
            MongoCursor<Document> clients = this.clientCollection.find().iterator();

            try{
                while (clients.hasNext()){
                    TupleClient c = new TupleClient((Document)clients.next());
                    System.out.println(c.toString());
                }

            }finally {
                clients.close();
            }

    }


}
