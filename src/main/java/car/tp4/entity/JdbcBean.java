package car.tp4.entity;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Class JdbcBean
 */
@Stateless
@Local
public class JdbcBean {

    /**
     * check if login and password are valid
     * @param email
     * @param password
     * @return boolean
     * @throws NoSuchAlgorithmException
     */
    public boolean loginCheck( String email, String password ) throws NoSuchAlgorithmException {

        String encryptedPassword = md5(password);

        System.out.println("encrypted password is "+encryptedPassword);

        jdbcDriver();

    /* Connexion à la base de données */
        String url = "jdbc:mysql://localhost:3306/bdd_jee";
        String utilisateur = "jee";
        String motDePasse = "root";
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;
        try {
            System.out.println( "Connexion à la base de données..." );
            connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
            System.out.println( "Connexion réussie !" );

        /* Création de l'objet gérant les requêtes */
            statement = connexion.createStatement();
            System.out.println( "Objet requête créé !" );

        /* Exécution d'une requête de lecture */

            resultat = statement.executeQuery( "SELECT id, email, password, name FROM bdd_jee.User WHERE email='"+email+"'AND password='"+encryptedPassword+"';");
            System.out.println( "Requête \"SELECT id, email, mot_de_passe, nom FROM Utilisateur;\" effectuée !" );

        /* Récupération des données du résultat de la requête de lecture */
            while ( resultat.next() ) {
                int idUtilisateur = resultat.getInt( "id" );
                String emailUtilisateur = resultat.getString( "email" );
                String motDePasseUtilisateur = resultat.getString( "password" );
                String nomUtilisateur = resultat.getString( "name" );
            /* Formatage des données pour affichage dans la JSP finale. */
                System.out.println( "Données retournées par la requête : id = " + idUtilisateur + ", email = " + emailUtilisateur
                        + ", motdepasse = "
                        + motDePasseUtilisateur + ", nom = " + nomUtilisateur + "." );
                return true;
            }

        } catch ( SQLException e ) {
            System.out.println( "Erreur lors de la connexion : <br/>"
                    + e.getMessage() );
        } finally {
            System.out.println( "Fermeture de l'objet ResultSet." );
            if ( resultat != null ) {
                try {
                    resultat.close();
                } catch ( SQLException ignore ) {
                }
            }
            System.out.println( "Fermeture de l'objet Statement." );
            if ( statement != null ) {
                try {
                    statement.close();
                } catch ( SQLException ignore ) {
                }
            }
            System.out.println( "Fermeture de l'objet Connection." );
            if ( connexion != null ) {
                try {
                    connexion.close();
                } catch ( SQLException ignore ) {
                }
            }
        }
        return false;
    }

    /**
     * hachcode md5
     * @param input
     * @return string
     */

    public static String md5(String input) {

        String md5 = null;

        if(null == input) return null;

        try {

            //Create MessageDigest object for MD5
            MessageDigest digest = MessageDigest.getInstance("MD5");

            //Update input string in message digest
            digest.update(input.getBytes(), 0, input.length());

            //Converts message digest value in base 16 (hex)
            md5 = new BigInteger(1, digest.digest()).toString(16);

        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();
        }
        return md5;
    }

    /**
     * jdbc driver
     */
    public static void jdbcDriver(){

            /* Chargement du driver JDBC pour MySQL */
        try {
            System.out.println( "Chargement du driver..." );
            Class.forName( "com.mysql.jdbc.Driver" );
            System.out.println( "Driver chargé !" );
        } catch ( ClassNotFoundException e ) {
            System.out.println( "Erreur lors du chargement : le driver n'a pas été trouvé dans le classpath ! <br/>"
                    + e.getMessage() );
        }

    }

}



