package AubergeInn.bdd;

public interface IConnexion {
    /**
     * Ferme une connexion
     */
    void fermer() throws Exception;

    /**
     * Lance une transaction avec une base de données
     */
    void demarreTransaction() throws Exception;

    /**
     * Exécute la transaction courante
     */
    void commit() throws Exception;

    /**
     * Annule la transaction courante
     */
    void rollback() throws Exception;
}
