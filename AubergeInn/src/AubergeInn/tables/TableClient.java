package AubergeInn.tables;

import AubergeInn.bdd.ConnexionODB;
import AubergeInn.tuples.TupleClient;
import AubergeInn.tuples.TupleReserver;

import javax.persistence.TypedQuery;
import java.sql.*;
import java.util.List;

public class TableClient {


    private TypedQuery<TupleClient> stmtAffichage;
    private TypedQuery<TupleClient> stmtExiste;
    private TypedQuery<TupleClient> stmtUpdate;
    private TypedQuery<TupleClient> stmtDelete;
    private TypedQuery<TupleReserver> stmtDeleteReservation;

    private final ConnexionODB cxODB;


    public TableClient(ConnexionODB cxODB) {
        this.cxODB = cxODB;

        stmtExiste = cxODB.getConnection()
                .createQuery("select c from TupleClient c where c.idClient = :idClient", TupleClient.class);

        stmtAffichage = cxODB.getConnection().createQuery("select c from TupleClient c", TupleClient.class);

        stmtUpdate = cxODB.getConnection()
                .createQuery("update TupleClient c set c.prenom = :prenom, c.nom = :nom, c.age = :age where c.idClient = :idClient", TupleClient.class);

        stmtDelete = cxODB.getConnection().createQuery("delete from TupleClient where idClient = :idClient", TupleClient.class);

        stmtDeleteReservation = cxODB.getConnection().createQuery("delete from TupleReserver where idClient = :idClient", TupleReserver.class);

    }


    public ConnexionODB getConnexion(){
        return cxODB;
    }

    public boolean Existe(int id) throws  SQLException{

        stmtExiste.setParameter("idClient", id);
        return !stmtExiste.getResultList().isEmpty();
    }

    public TupleClient getClient(int id) throws SQLException{

        stmtExiste.setParameter("idClient", id);
        List<TupleClient> clients = stmtExiste.getResultList();
        if(!clients.isEmpty())
        {
            return clients.get(0);
        }
        else
        {
            return null;
        }
    }

    public TupleClient Create(TupleClient client) throws SQLException{

        cxODB.getConnection().persist(client);
        return client;
    }

    public int Update(int idclient,String prenom,String nom,int age)throws SQLException{

        stmtUpdate.setParameter("prenom",prenom);
        stmtUpdate.setParameter("nom",nom);
        stmtUpdate.setParameter("age",age);
        stmtUpdate.setParameter("idClient",idclient);

        return stmtUpdate.executeUpdate();
    }

    public int Delete(int idclient)throws SQLException{

        stmtDelete.setParameter("idClient", idclient);
        //stmtDeleteReservation.setParameter("idClient", idclient);
        //stmtDeleteReservation.executeUpdate();

        return stmtDelete.executeUpdate();
    }

    public List<TupleClient> afficher() throws SQLException{

        return stmtAffichage.getResultList();
    }

    public ConnexionODB getConnexion(){
        return cxODB;
    }

}
