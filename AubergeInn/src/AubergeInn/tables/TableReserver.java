package AubergeInn.tables;

import AubergeInn.bdd.ConnexionODB;
import AubergeInn.tuples.TupleDetient;
import AubergeInn.tuples.TupleReserver;

import javax.persistence.TypedQuery;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TableReserver {
    private final ConnexionODB cxODB;

    public TableReserver(ConnexionODB cxODB)throws SQLException {
        this.cxODB = cxODB;
    }

    public TupleReserver Create(TupleReserver reserver) throws SQLException{
        cxODB.getConnection().persist(reserver);
        return reserver;
    }

    public ConnexionODB getConnexion(){
        return cxODB;
    }
}
