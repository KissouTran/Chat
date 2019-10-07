package com.tran.tchatserver;


import serversample.MessageBean;
import serversample.UtilisateurBean;


import java.sql.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class BDDUtils {

    private final static String GET_PSEUDOS = "SELECT * FROM Utilisateur WHERE pseudos=? ;";
    private final static String GET_USER = "SELECT * FROM Utilisateur WHERE pseudos=?;";
    private final static String GET_ALL_USER = " SELECT * FROM Utilisateur;";
    private final static String GET_MESSAGES = " SELECT * FROM Message INNER JOIN Utilisateur ON Utilisateur.id_utilisateur=Message.id_utilisateur; ";
    private final static String INSERT_UTILISATEUR = "INSERT INTO Utilisateur(pseudos) VALUES(?);";
    private final static String INSERT_MESSAGE = " INSERT INTO Message(textmessage,id_utilisateur) VALUES (?,?);";

    public static void main(String[] args) throws Exception {

        try {
            /*Je créer des Utilisateurs*/
//            UtilisateurBean utilisateur1 = new UtilisateurBean("Kyky");
//            UtilisateurBean utilisateur2 = new UtilisateurBean("Clémence");
//            System.out.println(getUser("Kyky"));
            /*J'insère dans la BDD mon utilisateur sans contrôle*/
//            insertUtilisateurSansControle(utilisateur1);

            /*Je créer un message*/
            MessageBean message = new MessageBean("Mon message");
            /*J'insère un message sans contrôle (créer un ID automatiquement)*/
//           insertMessageSansControle(message);


            /*Je récupère un utilisateur à partir de son ID */
            UtilisateurBean user4 = new UtilisateurBean("Kyky");
            /*Je relie mon utilisateur au message puis je l'insère*/
            message.setUtilisateur(getUser("Kyky"));
            insertMessage(message);


            /*Affiche un utilisateur à partir de son ID*/
//           System.out.println(getUser(4));
//           System.out.println(selectMessages());

            /*J'ajoute un nouvel utilisateur, Si il existe = retourne l'utilisateur | SINON le créer */
//            UtilisateurBean u = insertUtilisateurAvecControle("Mochi");
//            System.out.println(u);

//            System.out.println(selectPseudos());


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /*-----------------------------------------METHODES------------------------------------------------*/


    /* Méthode pour afficher tous les messages de la BDD */

    public static ArrayList<MessageBean> selectMessages() throws Exception {
        ArrayList<MessageBean> listMessages = new ArrayList<>();
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(ConnexionJDBC.URL); //La connexion
            stmt = con.prepareStatement(GET_MESSAGES);
            ResultSet rset = stmt.executeQuery();

            while (rset.next()) {
                MessageBean message = rsetToMessage(rset);
                listMessages.add(message);
            }
            return listMessages;
        } finally {
            if (con != null) {// On ferme la connexion
                try {
                    con.close();
                } catch (final SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /* Méthode pour afficher tous les utilisateurs de la BDD */

    public static ArrayList<UtilisateurBean> selectPseudos() throws Exception {
        ArrayList<UtilisateurBean> listUtilisateur = new ArrayList<>();
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(ConnexionJDBC.URL); //La connexion
            stmt = con.prepareStatement(GET_ALL_USER);
            ResultSet rset = stmt.executeQuery();

            while (rset.next()) {
                UtilisateurBean utilisateur = rsetToUtilisateur(rset);
                listUtilisateur.add(utilisateur);
            }
            return listUtilisateur;
        } finally {
            if (con != null) {// On ferme la connexion
                try {
                    con.close();
                } catch (final SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


/*Retourne un UtilisateurBean en prenant en entrée son pseudo */
    public static UtilisateurBean getUser(String pseudo) throws Exception {
        UtilisateurBean utilisateur = new UtilisateurBean(pseudo);
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(ConnexionJDBC.URL); //La connexion
            stmt = con.prepareStatement(GET_USER);
            stmt.setString(1, pseudo);
            ResultSet rset = stmt.executeQuery();

            while (rset.next()) {
                if (pseudo.equals(rset.getString("pseudos"))) {
                    utilisateur.setId(rset.getInt("id_utilisateur"));
                    utilisateur.setPseudoUtilisateur(rset.getString("pseudos"));
                }
                return utilisateur;

                }

            return utilisateur;

        } finally {
            if (con != null) {// On ferme la connexion
                try {
                    con.close();
                } catch (final SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }




/*Permet de vérifier si l'utilisateur existe, si il existe le retourne, SINON le créer */
    public static UtilisateurBean insertUtilisateurAvecControle(String pseudos) throws Exception {
        UtilisateurBean utilisateur = new UtilisateurBean(pseudos);
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(ConnexionJDBC.URL); //La connexion
            stmt = con.prepareStatement(GET_PSEUDOS);
            stmt.setString(1, pseudos);
            ResultSet rset = stmt.executeQuery();

                 while (rset.next()) {
                try {
                    if (pseudos.equals(rset.getString("pseudos"))){
                        return utilisateur;
                    }else if(utilisateur.getPseudoUtilisateur().trim().length() == 0) {
                        throw new Exception ("Utilisateur vide, veuillez saisir ");
                    }
                        return utilisateur;
                } catch( final SQLException e){
                        e.printStackTrace();
                    }
                }
             insertUtilisateurSansControle(utilisateur);
            System.out.println("Je créer l'utilisateur");
            return utilisateur;
        } finally{
                if (con != null) {// On ferme la connexion
                    try {
                        con.close();
                    } catch (final SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

 /*Prends en entrée un UtilisateurBean et l'ajoute à la BDD */
    public static void insertUtilisateurSansControle(UtilisateurBean pseudo) throws Exception {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            //Pour travailler avec Tomcat + SQLITE Rajouter :
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(ConnexionJDBC.URL); // La connexion
            stmt = con.prepareStatement(INSERT_UTILISATEUR, Statement.RETURN_GENERATED_KEYS);
            // Remplir la requête
            stmt.setString(1, pseudo.getPseudoUtilisateur());
            // Lancer la requête
            stmt.executeUpdate();
            //Je récupère l'ID auto généré
            ResultSet tableKeys = stmt.getGeneratedKeys();
            tableKeys.next();
            pseudo.setId(((int) tableKeys.getInt(1)));
        } finally {
            // On ferme la connexion
            if (con != null) {
                try {
                    con.close();
                } catch (final SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
//trim efface tous les espaces début et fin , enlever msg vide
/*Vérifier si l'ID utilisateur dans le Message est null, utilisateur existe, message vide SINON insére le msg */
    public static void insertMessage(MessageBean message) throws Exception {
        if (message.getUtilisateur() == null) {
            throw new Exception("L'utilisateur n'existe pas");
        }else if(message.getTextMessage() == null || message.getTextMessage().trim().length() == 0) {
            throw new Exception("Le message est vide");
        }else if(message.getUtilisateur().getId()== 0) {
            throw new Exception("L'ID n'existe pas");
        } else {
            insertMessageSansControle(message);
            System.out.println("Message inséré");
        }
    }

/*Prends un MessageBean en entrée et l'ajoute dans la BDD, ne retourne rien */
    public static void insertMessageSansControle(MessageBean message) throws Exception {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            //Pour travailler avec Tomcat + SQLITE Rajouter :
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(ConnexionJDBC.URL); // La connexion
            stmt = con.prepareStatement(INSERT_MESSAGE, Statement.RETURN_GENERATED_KEYS);
            // Remplir la requête
            stmt.setString(1, message.getTextMessage());
            stmt.setInt(2, message.getUtilisateur().getId());
            // Lancer la requête
            stmt.executeUpdate();
            //Je récupère l'ID auto généré
            ResultSet tableKeys = stmt.getGeneratedKeys();
            tableKeys.next();
            message.setId(((int) tableKeys.getInt(1)));
        } finally {
            // On ferme la connexion
            if (con != null) {
                try {
                    con.close();
                } catch (final SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

 /*Récupère les données de l'utilisateur dans la BDD */
    private static UtilisateurBean rsetToUtilisateur(ResultSet rset) throws SQLException {
        final UtilisateurBean utilisateur = new UtilisateurBean("");

        utilisateur.setId(rset.getInt("id_utilisateur"));
        utilisateur.setPseudoUtilisateur(rset.getString("pseudos"));

        return utilisateur;

    }
/*Récupère les données du message dans la BDD*/
    private static MessageBean rsetToMessage(ResultSet rset) throws Exception {
        final MessageBean message = new MessageBean("");

        message.setId(rset.getInt("id_message"));
        message.setTextMessage(rset.getString("textmessage"));
        message.setDateMessage(rset.getInt("dateMessage"));

        //Je recup le pseudo à partir de getUser et de l'ID
        UtilisateurBean user = rsetToUtilisateur(rset);
        message.setUtilisateur(user);
        return message;

}
}
