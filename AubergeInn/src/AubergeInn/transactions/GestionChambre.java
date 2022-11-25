package AubergeInn.transactions;

import AubergeInn.IFT287Exception;
import AubergeInn.bdd.ConnexionMongo;
import AubergeInn.tables.TableChambre;
import AubergeInn.tuples.TupleChambre;

import java.util.List;

public class GestionChambre {
    private final TableChambre chambres;

    public GestionChambre(TableChambre chambres)throws IFT287Exception {
        this.chambres = chambres;

    }

    public void afficherChambres() throws Exception{
        chambres.afficher();
    }


    public void Create(int idchambre,String nom_chambre, String type_lit, int prix_base)throws Exception{
        try{
            if(chambres.Existe(idchambre)){
                throw new IFT287Exception("Chambre déjà existant" + idchambre);
            }else{
                chambres.Create(idchambre,nom_chambre,type_lit,prix_base);
            }
        }
        catch (Exception e){

            throw e;
        }
    }

    public void Delete(int idchambre)throws Exception{
        try {
            TupleChambre tuplechambre = chambres.getChambre(idchambre);
            if (tuplechambre == null){
                throw new IFT287Exception("La Chambre " + idchambre + "n'existe pas");
            }else{
                chambres.Delete(idchambre);
            }

        }catch (Exception e){
            throw e;
        }
    }

}
