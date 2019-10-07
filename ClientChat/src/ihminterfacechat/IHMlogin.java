package ihminterfacechat;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class IHMlogin {

    public IHMchat ihmchat;
    public Controler controler;


    // Composants graphiques

    private JFrame frame;
    private JTextField txtUtilisateur;
    private JLabel lblErreurpseudo;
    private JLabel lblEntrerPseudo;
    private JButton btnLogin;


    /**
     * Launch the application.
     */


    /**
     * Create the application.
     * @param controler
     */
    public IHMlogin(Controler controler) {
        this.controler = controler;
        initialize();

    }


    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        frame = new JFrame();
        frame.setBackground(new Color(255, 250, 250));
        frame.getContentPane().setBackground(new Color(245, 245, 220));
        frame.getContentPane().setForeground(Color.BLACK);
        frame.setBounds(100, 100, 1075, 514);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        lblEntrerPseudo = new JLabel("Veuillez entrer votre pseudo");
        lblEntrerPseudo.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblEntrerPseudo.setBounds(312, 133, 329, 44);
        frame.getContentPane().add(lblEntrerPseudo);

        txtUtilisateur = new JTextField();
        txtUtilisateur.setText("Utilisateur");
        txtUtilisateur.setBounds(342, 186, 182, 36);
        frame.getContentPane().add(txtUtilisateur);
        txtUtilisateur.setColumns(10);

        btnLogin = new JButton("Valider");
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //je préviens le controler du click sur le bouton
                try {
                    controler.onBtnLogin(txtUtilisateur.getText());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnLogin.setBounds(515, 248, 115, 29);
        frame.getContentPane().add(btnLogin);

        lblErreurpseudo = new JLabel("Erreur");
        lblErreurpseudo.setForeground(Color.RED);
        lblErreurpseudo.setFont(new Font("Tahoma", Font.BOLD, 22));
        lblErreurpseudo.setBounds(712, 106, 210, 60);
        frame.getContentPane().add(lblErreurpseudo);

    }

    /* ---------------------------------
   // Methode pour mettre à jour l'IHM
   // -------------------------------- */

    public void setVisibleIhmLogin (boolean pseudos) {
        frame.setVisible(pseudos);
        lblErreurpseudo.setVisible(false);
    }

    public void setMessageErreurIhmLogin(String erreur) {
        lblErreurpseudo.setText(erreur);
        lblErreurpseudo.setVisible(true);
    }


    /* ---------------------------------
      // Getter / Setter
      // -------------------------------- */

    public Controler getControler() {
        return controler;
    }

    public void setControler(Controler controler) {
        this.controler = controler;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JTextField getTxtUtilisateur() {
        return txtUtilisateur;
    }

    public void setTxtUtilisateur(JTextField txtUtilisateur) {
        this.txtUtilisateur = txtUtilisateur;
    }

    public JLabel getLblErreurpseudo() {
        return lblErreurpseudo;
    }

    public void setLblErreurpseudo(JLabel lblErreurpseudo) {
        this.lblErreurpseudo = lblErreurpseudo;
    }

    public JLabel getLblEntrerPseudo() {
        return lblEntrerPseudo;
    }

    public void setLblEntrerPseudo(JLabel lblEntrerPseudo) {
        this.lblEntrerPseudo = lblEntrerPseudo;
    }

    public JButton getBtnLogin() {
        return btnLogin;
    }

    public void setBtnLogin(JButton btnValider) {
        this.btnLogin = btnLogin;
    }


}
