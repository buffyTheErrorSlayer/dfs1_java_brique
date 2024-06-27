package application;

import models.Balle;
import models.Barre;
import models.Brique;
import models.Sprite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Fenetre extends Canvas implements KeyListener {

    public static final int LARGEUR = 500;
    public static final int HAUTEUR = 700;

    protected boolean toucheEspace = false;

    ArrayList<Balle> listeBalles = new ArrayList<>();
    ArrayList<Sprite> listeSprites = new ArrayList<>();
    ArrayList<Brique> listeBriques = new ArrayList<>();

    Barre barre;

    Fenetre()  throws InterruptedException {

        JFrame fenetre = new JFrame();

        this.setSize(LARGEUR, HAUTEUR);
        this.setBounds(0, 0, LARGEUR, HAUTEUR);
        this.setIgnoreRepaint(true);
        this.setFocusable(false);

        fenetre.pack();
        fenetre.setSize(LARGEUR, HAUTEUR );
        fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fenetre.setResizable(false);
        fenetre.requestFocus();
        fenetre.addKeyListener(this);


        Container panneau = fenetre.getContentPane();
        panneau.add(this);

        fenetre.setVisible(true);
        this.createBufferStrategy(2);



        this.demarrer();
    }

    public void demarrerBriques()
    {
        int briqueLargeur = 40;
        int briqueHauteur = 20;
        int padding = 5;
        int offsetX = 10;
        int offsetY = 10;

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 10; col++) {
                int x = offsetX + col * (briqueLargeur + padding);
                int y = offsetY + row * (briqueHauteur + padding);
                Brique brique = new Brique(x, y, briqueLargeur, briqueHauteur, Color.GRAY);
                listeBriques.add(brique);
                listeSprites.add(brique);
            }
        }
    }

    public void demarrer() throws InterruptedException {

        barre = new Barre();
        listeSprites.add(barre);

        Balle balle = new Balle(100, 200 , Color.GREEN, 30);

        listeBalles.add(balle);
        listeSprites.add(balle);

        demarrerBriques();

        while(true) {

            Graphics2D dessin = (Graphics2D) this.getBufferStrategy().getDrawGraphics();
            dessin.setColor(Color.WHITE);
            dessin.fillRect(0,0,LARGEUR,HAUTEUR);

            //----- app -----
            for(Balle b : listeBalles) {
                b.deplacement(barre,listeBriques);
            }

            for(Sprite s : listeSprites) {
                s.dessiner(dessin);
            }

            listeSprites.removeIf(sprite -> sprite instanceof Brique && !listeBriques.contains(sprite));


            if(toucheEspace) {
                listeBalles.add( new Balle(200, 400 , Color.BLUE, 50));
            }
            //---------------

            dessin.dispose();
            this.getBufferStrategy().show();
            Thread.sleep(1000 / 60);
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            toucheEspace = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            barre.moveLeft();
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            barre.moveRight();
        }
        repaint();
    }

    @Override
    public void  keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            toucheEspace = false;
        }
    }
}
