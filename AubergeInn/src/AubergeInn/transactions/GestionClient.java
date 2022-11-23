package AubergeInn.transactions;
import AubergeInn.IFT287Exception;
import AubergeInn.bdd.ConnexionODB;
import AubergeInn.bdd.IConnexion;
import AubergeInn.tables.TableClient;
import AubergeInn.tuples.TupleClient;

import java.util.List;


public class GestionClient {
    private final TableClient clients;
    private final ConnexionODB cxODB;

    public GestionClient(TableClient clients)throws IFT287Exception {
        this.cxODB = clients.getConnexion();
        this.clients = clients;
    }


    public List<TupleClient> afficherClients() throws Exception{
        try
        {
            cxODB.demarreTransaction();

            List<TupleClient> clients = this.clients.afficher();

            cxODB.commit();
            return clients;
        }
        catch (Exception e){
            cxODB.rollback();
            throw e;
        }
    }


    public void Create(int idclient,String prenom, String nom,int age)throws Exception{
        try{
            cxODB.demarreTransaction();

            TupleClient client = new TupleClient(idclient,prenom,nom,age);
            if(clients.Existe(idclient))
                throw new IFT287Exception("Client existe déja: " + idclient);

            clients.Create(client);

            cxODB.commit();
        }
        catch (Exception e){
            cxODB.rollback();
            throw e;
        }
    }

    public void Delete(int idclient)throws Exception{
        try {
            cxODB.demarreTransaction();

            TupleClient tupleClient = clients.getClient(idclient);
            if (tupleClient == null) throw new IFT287Exception("Le Client " + idclient + "n'existe pas");

            clients.Delete(idclient);
            cxODB.commit();
        }catch (Exception e){
            cxODB.rollback();
            throw e;
        }
    }


}
