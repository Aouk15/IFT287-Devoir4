package AubergeInn.transactions;

import AubergeInn.IFT287Exception;
import AubergeInn.bdd.ConnexionMongo;
import AubergeInn.tables.TableChambre;
import AubergeInn.tables.TableClient;
import AubergeInn.tables.TableReserver;
import AubergeInn.tuples.TupleChambre;
import AubergeInn.tuples.TupleClient;
import AubergeInn.tuples.TupleReserver;

import java.sql.Date;

public class GestionReserver {

    private final TableClient clients;
    private final TableChambre chambres;
    private final TableReserver reserver;

    private final ConnexionMongo cxMongo;

    public GestionReserver(TableReserver reserver,TableClient clients,TableChambre chambres)throws IFT287Exception {
        if(reserver.getConnexion() != chambres.getConnexion() || chambres.getConnexion() != clients.getConnexion()){
            throw new IFT287Exception(
                    "Les collections d'objets n'utilisent pas la même connexion au serveur");
        }else{
            this.reserver = reserver;
            this.clients = clients;
            this.chambres = chambres;
            this.cxMongo = reserver.getConnexion();
        }


    }

    public void Create(int idclient, int idchambre, Date datedebut, Date datefin)throws Exception{
        try{
            TupleChambre chambre = chambres.getChambre(idchambre);
            TupleClient client = clients.getClient(idclient);

            if(chambre == null) throw new IFT287Exception("Chambre inexistant: " + idchambre);

            if(client == null)throw new IFT287Exception("Client inexistant: " + idclient);

            reserver.Create(idclient, idchambre, datedebut, datefin);
        }
        catch (Exception e){
            throw e;
        }
    }
}
