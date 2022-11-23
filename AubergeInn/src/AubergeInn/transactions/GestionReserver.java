package AubergeInn.transactions;

import AubergeInn.IFT287Exception;
import AubergeInn.bdd.ConnexionODB;
import AubergeInn.tables.TableReserver;
import AubergeInn.tuples.TupleReserver;

import java.sql.Date;

public class GestionReserver {

    private final TableReserver reserver;
    private final ConnexionODB cxODB;

    public GestionReserver(TableReserver reserver)throws IFT287Exception {
        this.cxODB = reserver.getConnexion();
        this.reserver = reserver;

    }

    public void Create(int idclient, int idchambre, Date datedebut, Date datefin)throws Exception{
        try{
            cxODB.demarreTransaction();
            TupleReserver tuplereserver = new TupleReserver(idclient,idchambre,datedebut,datefin);
            reserver.Create(tuplereserver);

            cxODB.commit();

        }
        catch (Exception e){
            cxODB.rollback();
            throw e;
        }
    }
}
