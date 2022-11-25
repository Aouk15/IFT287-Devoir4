package AubergeInn;


import AubergeInn.bdd.ConnexionMongo;
import AubergeInn.tables.*;
import AubergeInn.transactions.*;
import java.sql.SQLException;

public class GestionAubergeInn {
    private GestionClient gestionClient;
    private final TableClient client;

    private GestionChambre gestionChambre;
   // private final TableChambre chambre;

    private GestionCommodite gestionCommodite;
   // private final TableCommodite commodite;

    private GestionDetient gestionDetient;
   // private final TableDetient detient;

    private GestionReserver gestionReserver;

   // private final TableReserver reserver;


    ConnexionMongo cxMongo;

    public GestionAubergeInn(ConnexionMongo connexion)throws IFT287Exception, SQLException {
        this.cxMongo = connexion;

        this.client = new TableClient(cxMongo);
        setGestionClient(new GestionClient(client));
         /**
        this.chambre = new TableChambre(cxODB);
        setGestionChambre(new GestionChambre(chambre));

        this.commodite = new TableCommodite(cxODB);
        setGestionCommodite(new GestionCommodite(commodite));

        this.detient = new TableDetient(cxODB);
        setGestionDetient(new GestionDetient(detient, chambre, commodite));


        this.reserver = new TableReserver(cxODB);
        setGestionReserver(new GestionReserver(reserver));

        */
    }


    //SECTION CLIENT
    public void setGestionClient(GestionClient gestClient) {
        gestionClient = gestClient;
    }

    public GestionClient getGestionClient(){
        return gestionClient;
    }
/**
    //SECTION CHAMBRE
    public void setGestionChambre(GestionChambre gestChambre){gestionChambre = gestChambre;}
    public GestionChambre getGestionChambre(){return gestionChambre;}

    //SECTION COMMODITE
    public void setGestionCommodite(GestionCommodite gestCommodite) {
        this.gestionCommodite = gestCommodite;
    }

    public GestionCommodite getGestionCommodite() {
        return gestionCommodite;
    }

    //SECTION DETIENT
    public void setGestionDetient(GestionDetient gestDetient){gestionDetient = gestDetient;}
    public GestionDetient getGestionDetient(){return gestionDetient;}


    //SECTION RESERVER
    public void setGestionReserver(GestionReserver gestReserver){gestionReserver = gestReserver;}
    public GestionReserver getGestionReserver(){return gestionReserver;}

*/
}
