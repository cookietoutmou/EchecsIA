package Vue;

import javafx.scene.image.Image;

public abstract class PieceVue {
    Image imageBlanche;
    Image imageNoire;

    public PieceVue() {
    }

    public Image getImageBlanche() {
        return this.imageBlanche;
    }
    public void setImageBlanche(Image imageBlanche) {
        this.imageBlanche = imageBlanche;
    }

    public Image getImageNoire() {
        return this.imageNoire;
    }
    public void setImageNoire(Image imageNoire) {
        this.imageNoire = imageNoire;
    }
}