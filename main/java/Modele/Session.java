package Modele;

public class Session {
    private Joueur J1;
    private Joueur J2;
    private int paramJ1;
    private int paramJ2;
    private static Session instance;

    public Session(Joueur J1, Joueur J2, int paramJ1, int paramJ2) {
        this.J1 = J1;
        this.J2 = J2;
        this.paramJ1 = paramJ1;
        this.paramJ2 = paramJ2;
    }

    public Joueur getJ1() {
        return J1;
    }
    public void setJ1(Joueur J1) {
        this.J1 = J1;
    }

    public Joueur getJ2() {
        return J2;
    }
    public void setJ2(Joueur J2) {
        this.J2 = J2;
    }

    public int getParamJ1() {
        return paramJ1;
    }
    public void setParamJ1(int paramJ1) {
        this.paramJ1 = paramJ1;
    }

    public int getParamJ2() {
        return paramJ2;
    }
    public void setParamJ2(int paramJ2) {
        this.paramJ2 = paramJ2;
    }

    public static Session getInstance() {
        return instance;
    }
    public static void setInstance(Session instance) {
        Session.instance = instance;
    }
}