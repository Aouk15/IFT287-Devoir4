package AubergeInn.tables;


import AubergeInn.bdd.ConnexionODB;
import AubergeInn.tuples.TupleCommodite;

import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;

public class TableCommodite {

    private final TypedQuery<TupleCommodite> stmtAffichage;
    private final TypedQuery<TupleCommodite> stmtExiste;
    private final TypedQuery<TupleCommodite> stmtUpdate;
    private final TypedQuery<TupleCommodite> stmtDelete;
    private final ConnexionODB cxODB;

    public TableCommodite(ConnexionODB cxODB) {
        this.cxODB = cxODB;

        stmtExiste = cxODB.getConnection()
                .createQuery("select c from TupleCommodite c where c.idCommodite = :idCommodite", TupleCommodite.class);

        stmtAffichage = cxODB.getConnection().createQuery("select c from TupleCommodite c", TupleCommodite.class);

        stmtUpdate = cxODB.getConnection()
                .createQuery("update TupleCommodite c set c.description = :description, c.surplus_prix = :surplus_prix where c.idCommodite = :idCommodite", TupleCommodite.class);

        stmtDelete = cxODB.getConnection().createQuery("delete from TupleCommodite where idCommodite = :idCommodite", TupleCommodite.class);

    }

    public ConnexionODB getConnexion(){
        return cxODB;
    }

    public boolean Existe(int id) throws  SQLException{

        stmtExiste.setParameter("idCommodite", id);
        return !stmtExiste.getResultList().isEmpty();
    }

    public TupleCommodite getCommodite(int id) throws SQLException{

        stmtExiste.setParameter("idCommodite", id);
        List<TupleCommodite> commodites = stmtExiste.getResultList();
        if(!commodites.isEmpty())
        {
            return commodites.get(0);
        }
        else
        {
            return null;
        }
    }


    public TupleCommodite Create(TupleCommodite commodite) throws SQLException{

        cxODB.getConnection().persist(commodite);
        return commodite;
    }

    public int Update(int idcommodite,String description,int surplus_prix)throws SQLException{

        stmtUpdate.setParameter("idCommodite",idcommodite);
        stmtUpdate.setParameter("description",description);
        stmtUpdate.setParameter("surplus_prix",surplus_prix);

        return stmtUpdate.executeUpdate();
    }

    public int Delete(int idcommodite)throws SQLException{

        stmtDelete.setParameter("idCommodite", idcommodite);

        return stmtDelete.executeUpdate();
    }

    public List<TupleCommodite> afficher() throws SQLException{

        return stmtAffichage.getResultList();
    }


}
