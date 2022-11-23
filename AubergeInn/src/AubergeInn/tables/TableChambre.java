package AubergeInn.tables;
import AubergeInn.bdd.ConnexionODB;
import AubergeInn.tuples.TupleChambre;

import javax.persistence.TypedQuery;
import java.sql.*;
import java.util.List;

public class TableChambre {
    private final TypedQuery<TupleChambre> stmtAffichage;
    private final TypedQuery<TupleChambre> stmtExiste;
    private final TypedQuery<TupleChambre> stmtUpdate;
    private final TypedQuery<TupleChambre> stmtDelete;
    private final ConnexionODB cxODB;

    public TableChambre(ConnexionODB cxODB) {
        this.cxODB = cxODB;

        stmtExiste = cxODB.getConnection()
                .createQuery("select c from TupleChambre c where c.idChambre = :idChambre", TupleChambre.class);

        stmtAffichage = cxODB.getConnection().createQuery("select c from TupleChambre c", TupleChambre.class);

        stmtUpdate = cxODB.getConnection()
                .createQuery("update TupleChambre c set c.nom_chambre = :nom_chambre, c.type = :type, c.prix_base = :prix_base where c.idChambre = :idChambre",TupleChambre.class);

        stmtDelete = cxODB.getConnection().createQuery("delete from TupleChambre where idChambre = :idChambre", TupleChambre.class);

    }

    public ConnexionODB getConnexion(){
        return cxODB;
    }

    public boolean Existe(int id) throws  SQLException{

        stmtExiste.setParameter("idChambre", id);
        return !stmtExiste.getResultList().isEmpty();
    }

    public TupleChambre getChambre(int id) throws SQLException{

        stmtExiste.setParameter("idChambre", id);
        List<TupleChambre> chambres = stmtExiste.getResultList();
        if(!chambres.isEmpty())
        {
            return chambres.get(0);
        }
        else
        {
            return null;
        }
    }

    public TupleChambre Create(TupleChambre chambre) throws SQLException{

        cxODB.getConnection().persist(chambre);
        return chambre;
    }

    public int Update(int idchambre,String nom_chambre,String type_lit,int prix_base)throws SQLException{

        stmtUpdate.setParameter("idChambre",idchambre);
        stmtUpdate.setParameter("nom_chambre",nom_chambre);
        stmtUpdate.setParameter("type",type_lit);
        stmtUpdate.setParameter("prix_base",prix_base);

        return stmtUpdate.executeUpdate();
    }

    public int Delete(int idchambre)throws SQLException{

        stmtDelete.setParameter("idChambre", idchambre);
        return stmtDelete.executeUpdate();
    }

    public List<TupleChambre> afficher() throws SQLException{

        return stmtAffichage.getResultList();
    }



}
