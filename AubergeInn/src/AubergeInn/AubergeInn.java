// Travail fait par :
//   Alexandre Fontaine - 21 112 449
//   Victor Charboneau - 21 064 973

package AubergeInn;

import AubergeInn.bdd.Connexion;
import AubergeInn.bdd.ConnexionMongo;
import AubergeInn.tuples.TupleChambre;
import AubergeInn.tuples.TupleClient;
import AubergeInn.tuples.TupleCommodite;

import java.io.*;
import java.util.List;
import java.util.StringTokenizer;
import java.sql.*;

/**
 * Fichier de base pour le TP2 du cours IFT287
 *
 * <pre>
 * 
 * Vincent Ducharme
 * Universite de Sherbrooke
 * Version 1.0 - 7 juillet 2016
 * IFT287 - Exploitation de BD relationnelles et OO
 * 
 * Ce programme permet d'appeler des transactions d'un systeme
 * de gestion utilisant une base de donnees.
 *
 * Paramètres du programme
 * 0- site du serveur SQL ("local" ou "dinf")
 * 1- nom de la BD
 * 2- user id pour etablir une connexion avec le serveur SQL
 * 3- mot de passe pour le user id
 * 4- fichier de transaction [optionnel]
 *           si non spécifié, les transactions sont lues au
 *           clavier (System.in)
 *
 * Pré-condition
 *   - La base de donnees doit exister
 *
 * Post-condition
 *   - Le programme effectue les mises à jour associees à chaque
 *     transaction
 * </pre>
 */
public class AubergeInn
{
    private static Connexion cx;

    private static ConnexionMongo cxMongo;

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception
    {
        if (args.length < 4)
        {
            System.out.println("Usage: java AubergeInn.AubergeInn <serveur> <bd> <user> <password> [<fichier-transactions>]");
            return;
        }
        
        //cx = null;
        cxMongo = null;
        
        try
        {
            // Il est possible que vous ayez à déplacer la connexion ailleurs.
            // N'hésitez pas à le faire!
            cxMongo = new ConnexionMongo(args[0], args[1], args[2], args[3]);
            BufferedReader reader = ouvrirFichier(args);
            String transaction = lireTransaction(reader);
            while (!finTransaction(transaction))
            {
                executerTransaction(transaction);
                transaction = lireTransaction(reader);
            }
        }
        finally
        {
            if (cxMongo != null)
                cxMongo.fermer();
        }
    }

    /**
     * Decodage et traitement d'une transaction
     */
    static void executerTransaction(String transaction) throws Exception, IFT287Exception
    {
        try
        {
            System.out.print(transaction);
            // Decoupage de la transaction en mots
            StringTokenizer tokenizer = new StringTokenizer(transaction, " ");
            if (tokenizer.hasMoreTokens())
            {
                String commandName = tokenizer.nextToken();
                GestionAubergeInn gestionAubergeInn = new GestionAubergeInn(cxMongo);

                switch (commandName){
                    case "ajouterClient":
                    {
                        // Lecture des parametres
                        int param1 = readInt(tokenizer);
                        String param2 = readString(tokenizer);
                        String param3 = readString(tokenizer);
                        int param4 = readInt(tokenizer);
                        gestionAubergeInn.getGestionClient().Create(param1,param2,param3,param4);
                        break;
                    }

                    case "supprimerClient": {
                        // Lecture des parametres
                        int param1 = readInt(tokenizer);
                        gestionAubergeInn.getGestionClient().Delete(param1);
                        break;
                    }

                    case "afficherClient": {
                        System.out.println(" ");
                        System.out.println("idClient Prenom Nom Age");
                        System.out.println("-----------------------");
                        gestionAubergeInn.getGestionClient().afficherClients();
                        break;
                    }

                    case "ajouterChambre":{
                        // Lecture des parametres
                        int param1 = readInt(tokenizer);
                        String param2 = readString(tokenizer);
                        String param3 = readString(tokenizer);
                        int param4 = readInt(tokenizer);
                        gestionAubergeInn.getGestionChambre().Create(param1,param2,param3,param4);
                        break;
                    }

                    case "supprimerChambre":{
                        // Lecture des parametres
                        int param1 = readInt(tokenizer);
                        gestionAubergeInn.getGestionChambre().Delete(param1);
                        break;
                    }

                    case "afficherChambre":{
                        System.out.println(" ");
                        System.out.println("idChambre Nom_chambre Type_lit Prix_base");
                        System.out.println("----------------------------------------");
                        gestionAubergeInn.getGestionChambre().afficherChambres();
                        break;

                    }

                    case "ajouterCommodite":{
                        int param1 = readInt(tokenizer);
                        String param2 = readString(tokenizer);
                        int param3 = readInt(tokenizer);
                        gestionAubergeInn.getGestionCommodite().Create(param1,param2,param3);
                        break;
                    }

                    case "supprimerCommodite":{
                        // Lecture des parametres
                        int param1 = readInt(tokenizer);
                        gestionAubergeInn.getGestionCommodite().Delete(param1);
                        break;
                    }

                    case "afficherCommodite":{
                        System.out.println(" ");
                        System.out.println("idCommodite Description Surplus_prix");
                        System.out.println("----------------------------------------");
                        gestionAubergeInn.getGestionCommodite().afficherCommodite();

                        break;
                    }

                    case "inclureCommodite":{
                        // Lecture des parametres
                        int param1 = readInt(tokenizer);
                        int param2 = readInt(tokenizer);
                        gestionAubergeInn.getGestionDetient().Inclure(param1,param2);
                        break;
                    }

                    case "enleverCommodite":{
                        // Lecture des parametres
                        int param1 = readInt(tokenizer);
                        int param2 = readInt(tokenizer);
                        gestionAubergeInn.getGestionDetient().Exclure(param1,param2);
                        break;
                    }
/*
                    case "reserver":{
                        // Lecture des parametres
                        int param1 = readInt(tokenizer);
                        int param2 = readInt(tokenizer);
                        Date param3 = readDate(tokenizer);
                        Date param4 = readDate(tokenizer);
                        gestionAubergeInn.getGestionReserver().Create(param1,param2,param3,param4);
                        break;
                    }
*/

                    default:
                        System.out.println(" : Transaction non reconnue");
                        break;

                }

            }
        }
        catch (Exception e)
        {
            System.out.println(" " + e.toString());
            // Ce rollback est ici seulement pour vous aider et éviter des problèmes lors de la correction
            // automatique. En théorie, il ne sert à rien et ne devrait pas apparaître ici dans un programme
            // fini et fonctionnel sans bogues.
            //cxODB.rollback();
        }
    }

    
    // ****************************************************************
    // *   Les methodes suivantes n'ont pas besoin d'etre modifiees   *
    // ****************************************************************

    /**
     * Ouvre le fichier de transaction, ou lit à partir de System.in
     */
    public static BufferedReader ouvrirFichier(String[] args) throws FileNotFoundException
    {
        if (args.length < 5)
            // Lecture au clavier
            return new BufferedReader(new InputStreamReader(System.in));
        else
            // Lecture dans le fichier passe en parametre
            return new BufferedReader(new InputStreamReader(new FileInputStream(args[4])));
    }

    /**
     * Lecture d'une transaction
     */
    static String lireTransaction(BufferedReader reader) throws IOException
    {
        return reader.readLine();
    }

    /**
     * Verifie si la fin du traitement des transactions est atteinte.
     */
    static boolean finTransaction(String transaction)
    {
        // fin de fichier atteinte
        return (transaction == null || transaction.equals("quitter"));
    }

    /** Lecture d'une chaine de caracteres de la transaction entree a l'ecran */
    static String readString(StringTokenizer tokenizer) throws Exception
    {
        if (tokenizer.hasMoreElements())
            return tokenizer.nextToken();
        else
            throw new Exception("Autre parametre attendu");
    }

    /**
     * Lecture d'un int java de la transaction entree a l'ecran
     */
    static int readInt(StringTokenizer tokenizer) throws Exception
    {
        if (tokenizer.hasMoreElements())
        {
            String token = tokenizer.nextToken();
            try
            {
                return Integer.valueOf(token).intValue();
            }
            catch (NumberFormatException e)
            {
                throw new Exception("Nombre attendu a la place de \"" + token + "\"");
            }
        }
        else
            throw new Exception("Autre parametre attendu");
    }

    static Date readDate(StringTokenizer tokenizer) throws Exception
    {
        if (tokenizer.hasMoreElements())
        {
            String token = tokenizer.nextToken();
            try
            {
                return Date.valueOf(token);
            }
            catch (IllegalArgumentException e)
            {
                throw new Exception("Date dans un format invalide - \"" + token + "\"");
            }
        }
        else
            throw new Exception("Autre parametre attendu");
    }

}
