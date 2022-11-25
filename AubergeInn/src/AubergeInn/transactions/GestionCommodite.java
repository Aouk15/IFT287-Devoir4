package AubergeInn.transactions;

import AubergeInn.IFT287Exception;
import AubergeInn.bdd.ConnexionMongo;
import AubergeInn.bdd.IConnexion;
import AubergeInn.tables.TableCommodite;
import AubergeInn.tuples.TupleClient;
import AubergeInn.tuples.TupleCommodite;

import java.util.List;

public class GestionCommodite {

    private final TableCommodite commodites;
    private final ConnexionMongo cxMongo;

    public GestionCommodite(TableCommodite commodites)throws IFT287Exception {
        this.cxMongo = commodites.getConnexion();
        this.commodites = commodites;

    }


    public void afficherCommodite() throws Exception{
        try
        {
            this.commodites.afficher();

        }
        catch (Exception e){
            throw e;
        }
    }


    public void Create(int idcommodite,String description,int surplus_prix)throws Exception{
        try{

            if(commodites.Existe(idcommodite)) {
                throw new IFT287Exception("Commodite existe déja: " + idcommodite);
            }else{
                commodites.Create(idcommodite,description,surplus_prix);
            }

        }
        catch (Exception e){

            throw e;
        }
    }

    public void Delete(int idcommodite)throws Exception{
        try {


            TupleCommodite tupleCommodite = commodites.getCommodite(idcommodite);
            if (tupleCommodite == null) throw new IFT287Exception("La Commodite " + idcommodite + "n'existe pas");

            commodites.Delete(idcommodite);

        }catch (Exception e){
            throw e;
        }
    }

}
