package AubergeInn.transactions;

import AubergeInn.IFT287Exception;
import AubergeInn.bdd.ConnexionODB;
import AubergeInn.bdd.IConnexion;
import AubergeInn.tables.TableChambre;
import AubergeInn.tuples.TupleChambre;

import java.util.List;

public class GestionChambre {
    private final TableChambre chambres;
    private final ConnexionODB cxODB;

    public GestionChambre(TableChambre chambres)throws IFT287Exception {

        this.cxODB = chambres.getConnexion();
        this.chambres = chambres;

    }

    public List<TupleChambre> afficherChambres() throws Exception{
        try
        {
            cxODB.demarreTransaction();

            List<TupleChambre> chambres = this.chambres.afficher();

            cxODB.commit();
            return chambres;
        }
        catch (Exception e){
            cxODB.rollback();
            throw e;
        }
    }


    public void Create(int idchambre,String nom_chambre, String type_lit, int prix_base)throws Exception{
        try{
            cxODB.demarreTransaction();

            TupleChambre chambre = new TupleChambre(idchambre,nom_chambre,type_lit,prix_base);
            if(chambres.Existe(idchambre))throw new IFT287Exception("Chambre déjà existant" + idchambre);

            chambres.Create(chambre);
            cxODB.commit();

        }
        catch (Exception e){
            cxODB.rollback();
            throw e;
        }
    }

    public void Delete(int idchambre)throws Exception{
        try {
            cxODB.demarreTransaction();

            TupleChambre tuplechambre = chambres.getChambre(idchambre);
            if (tuplechambre == null) throw new IFT287Exception("La Chambre " + idchambre + "n'existe pas");

            chambres.Delete(idchambre);
            cxODB.commit();
        }catch (Exception e){
            cxODB.rollback();
            throw e;
        }
    }

}
