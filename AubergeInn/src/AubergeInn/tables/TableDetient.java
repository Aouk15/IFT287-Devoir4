package AubergeInn.tables;

import AubergeInn.bdd.ConnexionODB;
import AubergeInn.tuples.TupleDetient;
import javax.persistence.TypedQuery;
import java.sql.SQLException;


public class TableDetient {

    private final TypedQuery<TupleDetient> stmtExiste;
    private final TypedQuery<TupleDetient> stmtDelete;
    private final ConnexionODB cxODB;

    public TableDetient(ConnexionODB cxODB) {
        this.cxODB = cxODB;
        stmtExiste = cxODB.getConnection()
                .createQuery("select d from TupleDetient d where d.idChambre = :idChambre and d.idCommodite = :idCommodite",TupleDetient.class);

        stmtDelete = cxODB.getConnection()
                .createQuery("delete from TupleDetient where idChambre = :idChambre and idCommodite = :idCommodite",TupleDetient.class);
    }

    public ConnexionODB getConnexion(){
        return cxODB;
    }

    public boolean Existe(int idchambre, int idcommodite) throws  SQLException{

        stmtExiste.setParameter("idChambre", idchambre);
        stmtExiste.setParameter("idCommodite", idcommodite);
        return !stmtExiste.getResultList().isEmpty();
    }

    public TupleDetient Inclure(TupleDetient detient){

        cxODB.getConnection().persist(detient);
        return detient;
    }

    public int Exclure(int idchambre, int idcommodite){

        stmtDelete.setParameter("idChambre", idchambre);
        stmtDelete.setParameter("idCommodite", idcommodite);

        return stmtDelete.executeUpdate();
    }
}
