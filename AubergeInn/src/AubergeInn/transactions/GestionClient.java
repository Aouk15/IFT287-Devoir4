package AubergeInn.transactions;
import AubergeInn.IFT287Exception;
import AubergeInn.bdd.ConnexionMongo;
import AubergeInn.tables.TableClient;
import AubergeInn.tuples.TupleClient;

public class GestionClient {
    private final TableClient clients;
    private final ConnexionMongo cxMongo;

    public GestionClient(TableClient clients)throws IFT287Exception {

        this.cxMongo = clients.getConnexion();
        this.clients = clients;
    }


    public void afficherClients() throws Exception{
        try
        {
            this.clients.afficher();

        }
        catch (Exception e){
            throw e;
        }
    }


    public void Create(int idclient,String prenom, String nom,int age)throws Exception{
        try{

            if(clients.Existe(idclient)) {
                throw new IFT287Exception("Client existe déja: " + idclient);
            }else{
                clients.Create(idclient,prenom,nom,age);
            }

        }
        catch (Exception e){

            throw e;
        }
    }

    public void Delete(int idclient)throws Exception{
        try {


            TupleClient tupleClient = clients.getClient(idclient);
            if (tupleClient == null) throw new IFT287Exception("Le Client " + idclient + "n'existe pas");

            clients.Delete(idclient);

        }catch (Exception e){
            throw e;
        }
    }


}
