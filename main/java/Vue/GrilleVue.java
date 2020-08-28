package Vue;

import Modele.Coup;
import Modele.Piece;
import Modele.Plateau;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GrilleVue extends GridPane {

    public GrilleVue(Plateau plateau) {
        super();
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                StackPane sP = new StackPane();
                Rectangle r = new Rectangle(0, 0, 40, 40);
                ImageView iV = new ImageView();
                PieceVue p;
                Piece piecePlateau = plateau.getPiece(i-1, j-1);
                if ((i + j) % 2 == 0) {
                    r.setFill(Color.rgb(200, 200, 200));
                } else {
                    r.setFill(Color.rgb(55, 55, 55));
                }
                switch (piecePlateau.toString()) {
                    case "r":
                        p = new RoiVue();
                        iV.setImage(p.getImageBlanche());
                        break;
                    case "R":
                        p = new RoiVue();
                        iV.setImage(p.getImageNoire());
                        break;
                    case "d":
                        p = new DameVue();
                        iV.setImage(p.getImageBlanche());
                        break;
                    case "D":
                        p = new DameVue();
                        iV.setImage(p.getImageNoire());
                        break;
                    case "t":
                        p = new TourVue();
                        iV.setImage(p.getImageBlanche());
                        break;
                    case "T":
                        p = new TourVue();
                        iV.setImage(p.getImageNoire());
                        break;
                    case "f":
                        p = new FouVue();
                        iV.setImage(p.getImageBlanche());
                        break;
                    case "F":
                        p = new FouVue();
                        iV.setImage(p.getImageNoire());
                        break;
                    case "c":
                        p = new CavalierVue();
                        iV.setImage(p.getImageBlanche());
                        break;
                    case "C":
                        p = new CavalierVue();
                        iV.setImage(p.getImageNoire());
                        break;
                    case "p":
                        p = new PionVue();
                        iV.setImage(p.getImageBlanche());
                        break;
                    case "P":
                        p = new PionVue();
                        iV.setImage(p.getImageNoire());
                        break;
                }
                iV.setPreserveRatio(true);
                iV.setFitHeight(36);
                iV.setFitWidth(36);
                sP.getChildren().addAll(r, iV);
                this.add(sP, j, i);
            }
        }
    }

    public GrilleVue(){
        super();
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                StackPane sP = new StackPane();
                Rectangle r = new Rectangle(0, 0, 40, 40);
                ImageView iV = new ImageView();
                PieceVue p;
                if ((i + j) % 2 == 1) {
                    r.setFill(Color.rgb(200, 200, 200));
                } else {
                    r.setFill(Color.rgb(55, 55, 55));
                }
                if (i==2) {
                    p = new PionVue();
                    iV.setImage(p.getImageBlanche());
                }
                else if (i==7) {
                    p = new PionVue();
                    iV.setImage(p.getImageNoire());
                }
                else if (i==1) {
                    switch (j) {
                        case 1:
                            p = new TourVue();
                            iV.setImage(p.getImageBlanche());
                            break;
                        case 2:
                            p = new CavalierVue();
                            iV.setImage(p.getImageBlanche());
                            break;
                        case 3:
                            p = new FouVue();
                            iV.setImage(p.getImageBlanche());
                            break;
                        case 4:
                            p = new DameVue();
                            iV.setImage(p.getImageBlanche());
                            break;
                        case 5:
                            p = new RoiVue();
                            iV.setImage(p.getImageBlanche());
                            break;
                        case 6:
                            p = new FouVue();
                            iV.setImage(p.getImageBlanche());
                            break;
                        case 7:
                            p = new CavalierVue();
                            iV.setImage(p.getImageBlanche());
                            break;
                        case 8:
                            p = new TourVue();
                            iV.setImage(p.getImageBlanche());
                            break;
                    }
                }
                else if (i==8) {
                    switch (j) {
                        case 1:
                            p = new TourVue();
                            iV.setImage(p.getImageNoire());
                            break;
                        case 2:
                            p = new CavalierVue();
                            iV.setImage(p.getImageNoire());
                            break;
                        case 3:
                            p = new FouVue();
                            iV.setImage(p.getImageNoire());
                            break;
                        case 4:
                            p = new DameVue();
                            iV.setImage(p.getImageNoire());
                            break;
                        case 5:
                            p = new RoiVue();
                            iV.setImage(p.getImageNoire());
                            break;
                        case 6:
                            p = new FouVue();
                            iV.setImage(p.getImageNoire());
                            break;
                        case 7:
                            p = new CavalierVue();
                            iV.setImage(p.getImageNoire());
                            break;
                        case 8:
                            p = new TourVue();
                            iV.setImage(p.getImageNoire());
                            break;
                    }
                }
                iV.setPreserveRatio(true);
                iV.setFitHeight(36);
                iV.setFitWidth(36);
                sP.getChildren().addAll(r, iV);
                this.add(sP, j, i);
            }
        }
    }

    public void effectuerCoup(Coup c){
        ImageView fin = (ImageView)getNodeByRowColumnIndex(c.getFin()[0]+1, c.getFin()[1]+1).getChildren().get(1);
        ImageView debut = (ImageView)getNodeByRowColumnIndex(c.getDebut()[0]+1, c.getDebut()[1]+1).getChildren().get(1);
        ImageView tmp = new ImageView();
        tmp.setImage(debut.getImage());
        debut.setImage(fin.getImage());
        fin.setImage(tmp.getImage());
        if (debut.getImage() != null) {
            debut.setImage(null);
        }
    }

    public StackPane getNodeByRowColumnIndex(final int row, final int column) {
        StackPane result = null;
        ObservableList<Node> childrens = this.getChildren();
        for (Node s : childrens) {
            if(GrilleVue.getRowIndex(s) == row && GrilleVue.getColumnIndex(s) == column) {
                result = (StackPane)s;
                break;
            }
        }
        return result;
    }
}