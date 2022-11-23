package AubergeInn.transactions;

import AubergeInn.IFT287Exception;
import AubergeInn.bdd.ConnexionODB;
import AubergeInn.tables.TableChambre;
import AubergeInn.tables.TableCommodite;
import AubergeInn.tables.TableDetient;
import AubergeInn.tuples.TupleChambre;
import AubergeInn.tuples.TupleCommodite;
import AubergeInn.tuples.TupleDetient;

public class GestionDetient {

    private final TableDetient detient;
    private final TableChambre chambres;
    private final TableCommodite commodites;
    private final ConnexionODB cxODB;

    public GestionDetient(TableDetient detient, TableChambre chambres, TableCommodite commodites)throws IFT287Exception {

        if (detient.getConnexion() != chambres.getConnexion() || chambres.getConnexion() != commodites.getConnexion())
            throw new IFT287Exception(
                    "Les collections d'objets n'utilisent pas la même connexion au serveur");

        this.cxODB = detient.getConnexion();

        this.detient = detient;
        this.chambres = chambres;
        this.commodites = commodites;
    }

    public void Inclure(int idchambre, int idcommodite)throws Exception{
        try {
            cxODB.demarreTransaction();

            TupleChambre chambre = chambres.getChambre(idchambre);
            if(chambre == null) throw new IFT287Exception("Chambre inexistant: " + idchambre);

            TupleCommodite commodite = commodites.getCommodite(idcommodite);
            if(commodite == null) throw new IFT287Exception("Commodite inexistant: " + idcommodite);

            TupleDetient detient = new TupleDetient(idchambre,idcommodite);
            if(this.detient.Existe(idchambre,idcommodite)) throw new IFT287Exception("Existe deja");

            this.detient.Inclure(detient);
            cxODB.commit();
        }
        catch (Exception e){
            cxODB.rollback();
            throw e;
        }
    }

    public void Exclure(int idchambre, int idcommodite)throws Exception{
        try {
            cxODB.demarreTransaction();

            if(!this.detient.Existe(idchambre,idcommodite)) throw new IFT287Exception("N'existe pas");

            this.detient.Exclure(idchambre, idcommodite);
            cxODB.commit();
        }
        catch (Exception e){
            cxODB.rollback();
            throw e;
        }
    }
}
