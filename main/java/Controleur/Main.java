package Controleur;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import Modele.Algorithme;
import Modele.Alpha_Beta;
import Modele.Couleur;
import Modele.Coup;
import Modele.Joueur;
import Modele.Monte_Carlo;
import Modele.Partie;
import Modele.Session;
import Vue.FenetreAccueil;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static Connection connection = null;
    public static String databaseName = "echecs";
    public static String url = "jdbc:mysql://localhost:3306/" + databaseName + "?serverTimezone=Europe/Paris";
    public static String username = "root";
    public static String password = "U8wA1m5S2bgJ8T3p:?";
    public static File f = new File("../../../log");
    public static PrintWriter o;

    public static void main(String[] args)
            throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
            NoSuchMethodException, SecurityException, ClassNotFoundException, SQLException, FileNotFoundException {
        o = new PrintWriter(f);
        o.println("----------------------------------------------------------------------------------");
        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        connection = DriverManager.getConnection(url, username, password);
        launch(args);
        
        /*Partie p = new Partie();
        int parametreAB = 0;
        int parametreMC = 100;
        int parametre = parametreMC;
        Joueur j1 = new Joueur("j1", Couleur.BLANC, new Monte_Carlo(parametreMC));
        Joueur j2 = new Joueur("j2", Couleur.NOIR, new Alpha_Beta(parametreAB));
        Joueur j = j1;
        Session.setInstance(new Session(j1, j2, parametreMC, parametreAB));
        Scanner sc = new Scanner(System.in);
        while (!p.isFinie()) {
            System.out.println(p.getPlateau().toString());
            Coup c = p.prepareCoup(j, parametre);
            //System.out.println(p.getPlateau().toString());
            System.out.println("Appuie sur entree pour jouer le coup");
            sc.nextLine();
            p.getPlateau().effectuerCoup(c);
            if (p.getPlateau().estFini()) {
                p.setFinie(true);
            }
            else{
                if (j.equals(j1)) {
                    j = j2;
                    parametre = parametreAB;
                }
                else{
                    j = j1;
                    parametre = parametreMC;
                }
            }
        }
        sc.close();
        System.out.println("partie terminée");
        o.close();*/
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FenetreAccueil fA = FenetreAccueil.getInstance();
        primaryStage.setScene(fA.getScene());
        primaryStage.setTitle("Jeu d'echecs");  
        primaryStage.show();
    }
}