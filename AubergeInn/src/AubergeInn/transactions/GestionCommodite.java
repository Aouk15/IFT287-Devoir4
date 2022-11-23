package AubergeInn.transactions;

import AubergeInn.IFT287Exception;
import AubergeInn.bdd.ConnexionODB;
import AubergeInn.bdd.IConnexion;
import AubergeInn.tables.TableCommodite;
import AubergeInn.tuples.TupleCommodite;

import java.util.List;

public class GestionCommodite {

    private final TableCommodite commodites;
    private final ConnexionODB cxODB;

    public GestionCommodite(TableCommodite commodites)throws IFT287Exception {
        this.cxODB = commodites.getConnexion();
        this.commodites = commodites;

    }


    public List<TupleCommodite> afficherCommodite() throws Exception{
        try
        {
            cxODB.demarreTransaction();

            List<TupleCommodite> commodites = this.commodites.afficher();

            cxODB.commit();
            return commodites;
        }
        catch (Exception e){
            cxODB.rollback();
            throw e;
        }
    }


    public void Create(int idcommodite,String description,int surplus_prix)throws Exception{
        try{
            cxODB.demarreTransaction();

            TupleCommodite commodite = new TupleCommodite(idcommodite,description,surplus_prix);
            if(commodites.Existe(idcommodite))throw new IFT287Exception("Commodite déjà existant" + idcommodite);

            commodites.Create(commodite);
            cxODB.commit();
        }
        catch (Exception e){
            cxODB.rollback();
            throw e;
        }
    }

    public void Delete(int idcommodite)throws Exception{
        try {
            cxODB.demarreTransaction();

            TupleCommodite tupleCommodite = commodites.getCommodite(idcommodite);
            if (tupleCommodite == null) throw new IFT287Exception("La Commodite " + idcommodite + "n'existe pas");

            commodites.Delete(idcommodite);
            cxODB.commit();
        }
        catch (Exception e){
            cxODB.rollback();
            throw e;
        }
    }

}
