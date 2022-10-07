package com.mariapiaeven.applicationdesktop;

import javax.swing.*;
import java.awt.*;

public class Champs {

    public static Box generate(String label, JComponent... listeComposants) {

        Box ligne = Box.createHorizontalBox();
        ligne.setMaximumSize(new Dimension(500, 30));

        ligne.add(Box.createRigidArea(new Dimension(20, 1)));

        JLabel jLabel = new JLabel(label);
        jLabel.setPreferredSize(new Dimension(100, 30));
        ligne.add(jLabel);

        for (Component composant : listeComposants) {
            ligne.add(composant);
        }

        ligne.add(Box.createRigidArea(new Dimension(20,1)));

        return ligne;

    }
}
