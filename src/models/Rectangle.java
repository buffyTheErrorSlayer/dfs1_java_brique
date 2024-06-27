package models;

import java.awt.*;

public class Rectangle extends Sprite {

    protected int largeur;
    protected int hauteur;


    public Rectangle(int x, int y, int largeur, int hauteur,Color couleur) {
        super(x, y, couleur);
        this.largeur = largeur;
        this.hauteur = hauteur;
    }

    @Override
    public void dessiner(Graphics2D dessin) {
        dessin.setColor(couleur);
        dessin.fillRect(x, y, largeur, hauteur);
    }

    public boolean intersects(Rectangle other) {
        return this.x < other.x + other.largeur &&
                this.x + this.largeur > other.x &&
                this.y < other.y + other.hauteur &&
                this.y + this.hauteur > other.y;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getLargeur(){return largeur;}
    public int getHauteur(){return hauteur;}
    public Color getCouleur(){return couleur;}


}