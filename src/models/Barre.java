package models;

import application.Fenetre;

import java.awt.*;

public class Barre extends Rectangle{


    protected int vitesse = 10;

    public Barre(int x, int y, int largeur, int hauteur, Color couleur) {
        super(x, y, largeur, hauteur, couleur);
    }

    public Barre() {
        super(Fenetre.LARGEUR / 2 - 75, Fenetre.HAUTEUR - 100, 150, 20, Color.BLUE);
    }


    public void moveLeft() {
        if (x - vitesse >= 0) {
            x -= vitesse;
        }
    }

    public void moveRight() {
        if (x + largeur + vitesse <= Fenetre.LARGEUR) {
            x += vitesse;
        }
    }
}
