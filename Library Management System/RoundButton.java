/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bp2project;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;

/**
 *
 * @author user
 */
class RoundButton extends JButton {

    public RoundButton(String text) {
        super(text);
        setContentAreaFilled(false); 
        setFocusPainted(false);      
        setBorderPainted(false);    
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (getModel().isPressed()) {
            g2.setColor(Color.LIGHT_GRAY);
        } else {
            g2.setColor(Color.WHITE);
        }
        g2.fillOval(0, 0, getWidth(), getHeight());

        super.paintComponent(g);
        g2.dispose();
    }

    @Override
    public boolean contains(int x, int y) {

        int radius = Math.min(getWidth(), getHeight()) / 2;
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        return Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2) <= Math.pow(radius, 2);
    }
}
