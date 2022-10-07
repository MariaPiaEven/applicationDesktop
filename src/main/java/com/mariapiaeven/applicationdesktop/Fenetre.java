package com.mariapiaeven.applicationdesktop;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

public class Fenetre extends JFrame {

    protected boolean themeSombre = false;

    //methode
    public Fenetre() {
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //div
        JPanel panneau = new JPanel(new BorderLayout());

        setContentPane(panneau);
        //---------BOUTON-----------------
        JButton bouton = new JButton("Fermer");


        bouton.addActionListener(e -> {

            //---------DIALOG-----------------
            Object[] choix = {"Oui", "Nope"};
            int reponse = JOptionPane.showOptionDialog(
                    this,
                    "Voulez vous fermer l'application",
                    "Confirmer",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    choix,
                    choix[0]);

            if (reponse == JOptionPane.YES_OPTION) {
                System.exit(0);
            }

        });

        //--------------Change theme---------

        JButton boutonChangeTheme = new JButton("Change theme");

        boutonChangeTheme.addActionListener(e -> {
            try {

                if (themeSombre) {
                    themeSombre = false;
                    UIManager.setLookAndFeel(new FlatLightLaf());
                } else {
                    themeSombre = true;
                    UIManager.setLookAndFeel(new FlatDarkLaf());
                }
                SwingUtilities.updateComponentTreeUI(this);
            } catch (Exception ex) {
                System.out.println("Failed to initialize LaF");
            }
        });

        //---------COMBOBOX-----------------

        String[] listeCivilite = {"M.", "Me.", "Mlle.", "Non precisé"};
        JComboBox<String> selectCivilite = new JComboBox(listeCivilite);


        selectCivilite.addActionListener(e -> {
            JComboBox comboBox = (JComboBox) e.getSource();
            System.out.println(comboBox.getSelectedItem());
            ;

        });

        Utilisateur[] utilisateurs = {
                null,
                new Utilisateur("BANSEPT", "Franck"),
                new Utilisateur("SNOW", "John"),
                new Utilisateur("SMITH", "Steve")
        };

        JComboBox<Utilisateur> selectUtilisateur = new JComboBox<>(utilisateurs);

        //-------------------CUSTOMISER LE RENDU DE LA LISTE DEROULANTE-------------------------------
        selectUtilisateur.setRenderer(
                new DefaultListCellRenderer() {

                    @Override
                    public Component getListCellRendererComponent(
                            final JList<?> list, final Object value, final int index, final boolean isSelected, final boolean cellHasFocus) {
                        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                        Utilisateur utilisateur = (Utilisateur) value;
                        if (utilisateur != null) {

                            setText(utilisateur.getPrenom() + " " + utilisateur.getNom());
                        } else {
                            setText("Aucun");
                        }

                        if (isSelected) {
                            setBackground(Color.black);
                        } else {
                            setBackground(Color.darkGray);
                        }
                        return this;
                    }
                }
        );


        //------BOUTON FORMULAIRE-----

        JButton boutonFormulaire = new JButton("Envoyer");
        boutonFormulaire.addActionListener(e -> {

            if (selectUtilisateur.getSelectedItem() != null) {

                Utilisateur utilisateur =
                        (Utilisateur) selectUtilisateur.getSelectedItem();

                System.out.println(selectCivilite.getSelectedItem() + utilisateur.getNom());

            }
        });

        Box boxPrincipal = Box.createVerticalBox();
        panneau.add(boxPrincipal, BorderLayout.CENTER);

        Box boxMenu = Box.createHorizontalBox();
        boxMenu.add(bouton);
        boxMenu.add(boutonChangeTheme);
        boxPrincipal.add(boxMenu);

        boxPrincipal.add(Box.createRigidArea(new Dimension(1, 50)));


        boxPrincipal.add(Champs.generate("Civilite", selectCivilite));
        boxPrincipal.add(Champs.generate("Utilisateur", selectUtilisateur));


        panneau.add(boutonFormulaire, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        FlatDarkLaf.setup();
        new Fenetre();

    }
}
