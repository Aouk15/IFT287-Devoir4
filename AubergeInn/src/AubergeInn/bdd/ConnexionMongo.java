package AubergeInn.bdd;

import AubergeInn.IFT287Exception;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class ConnexionMongo {

    private MongoClient client;
    private MongoDatabase database;

    public ConnexionMongo(String serveur, String bd, String user, String pass) throws IFT287Exception {

        if (serveur.equals("local")) {
            this.client = new MongoClient();
        } else {
            if (!serveur.equals("dinf")) {
                throw new IFT287Exception("Serveur inconnu");
            }

            MongoClientURI uri = new MongoClientURI("mongodb://" + user + ":" + pass + "@bd-info2.dinf.usherbrooke.ca:27017/" + bd + "?authsource=admin&ssl=false");
            this.client = new MongoClient(uri);
        }

        this.database = this.client.getDatabase(bd);
        System.out.println("Ouverture de la connexion :\nConnecté sur la BD MongoDB " + bd + " avec l'utilisateur " + user);

    }

    public void fermer() {
        this.client.close();
        System.out.println("Connexion fermée");
    }

    public MongoClient getConnection() {
        return this.client;
    }

    public MongoDatabase getDatabase() {
        return this.database;
    }



}
