package Controleur;

import Vue.ConfigPartiesVue;
import Vue.FenetreAccueil;
import Vue.HistoriqueVue;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class TraitementBoutonsAccueil{

    private EventHandler<MouseEvent> handler1 = new EventHandler<MouseEvent>(){

        @Override
        public void handle(MouseEvent event){
            FenetreAccueil.getInstance().getScene().setRoot(ConfigPartiesVue.getInstance().getRacine());
        }
    };
    private EventHandler<MouseEvent> handler2 = new EventHandler<MouseEvent>(){

        @Override
        public void handle(MouseEvent event){
            FenetreAccueil.getInstance().getScene().setRoot(HistoriqueVue.getInstance().getGroup());
        }
    };

    public TraitementBoutonsAccueil(Button b1, Button b2) {
        b1.setOnMouseClicked(this.handler1);
        b2.setOnMouseClicked(this.handler2);
    }
}