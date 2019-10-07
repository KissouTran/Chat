package ihminterfacechat;

import model.MessageBean;
import model.UtilisateurBean;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;


public class IHMchat {


    private Controler controler;
    private UtilisateurBean utilisateur;

    // Composants graphiques

    private JFrame frame;
    private JLabel lblErreur;
    private JButton btnEnvoyer;
    private JTextArea textAreaZoneText;
    private JButton btnRefresh;
    private JLabel lblPseudo;
    private JTextField JTextFieldDiscussion;
    private JTextArea JTextDiscussion;


    /**
     * Launch the application.
     */


    /**
     * Create the application.
     * @param controler
     */
    public IHMchat(Controler controler) {
        this.controler = controler;
        initialize();
    }


    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBackground(new Color(192, 186, 151));
        frame.getContentPane().setBackground(new Color(245, 245, 220));
        frame.getContentPane().setForeground(Color.BLACK);
        frame.setBounds(100, 100, 1075, 514);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        btnEnvoyer = new JButton("Send");
        btnEnvoyer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //je préviens le controler du click sur le bouton
                try {
                    controler.onBtnEnvoyer(textAreaZoneText.getText());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnEnvoyer.setBackground(new Color(224, 146, 145));
        btnEnvoyer.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnEnvoyer.setBounds(571, 345, 115, 29);
        frame.getContentPane().add(btnEnvoyer);



        textAreaZoneText = new JTextArea();
        textAreaZoneText.setText("Message ...");
        textAreaZoneText.setBounds(158, 282, 528, 92);
        frame.getContentPane().add(textAreaZoneText);

        btnRefresh = new JButton("Refresh");
        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //je préviens le controler du click sur le bouton
                controler.onBtnRefresh();
            }
        });
        btnRefresh.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnRefresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnRefresh.setBackground(new Color(247, 172, 128));
        btnRefresh.setForeground(new Color(0, 0, 0));
        btnRefresh.setBounds(688, 240, 115, 29);
        frame.getContentPane().add(btnRefresh);

        lblPseudo = new JLabel("Pseudo utilisateur");
        lblPseudo.setBounds(15, 16, 134, 20);
        frame.getContentPane().add(lblPseudo);

        lblErreur = new JLabel("Erreur");
        lblErreur.setForeground(Color.RED);
        lblErreur.setFont(new Font("Tahoma", Font.BOLD, 22));
        lblErreur.setBounds(712, 106, 210, 60);
        frame.getContentPane().add(lblErreur);
        JTextFieldDiscussion = new JTextField();
        frame.getContentPane().add(JTextFieldDiscussion);

        JTextDiscussion = new JTextArea();
        frame.getContentPane().add(JTextDiscussion);
        JTextDiscussion.setBounds(160, 51, 526, 215);
        JScrollPane scrollPane = new JScrollPane(JTextDiscussion);
        scrollPane.setBounds(158, 49, 528, 217);
        frame.getContentPane().add(scrollPane);


//        JlistDiscussion = new JList();
//        frame.getContentPane().add(JlistDiscussion);
//
//        DefaultListModel model = new DefaultListModel();
//
////        //CODE BIDON////
////        for (int i = 0; i < 40; i++) {
////            model.addElement("test + " +i);
////        }
////        /////////
//
//        JlistDiscussion.setModel(model);
//
//        JlistDiscussion.setBounds(160, 51, 526, 215);
//        JScrollPane  = new JScrollPane(JlistDiscussion);
//        scrollPane.setBounds(158, 49, 528, 217);
//        frame.getContentPane().add(scrollPane);


    }

    /* ---------------------------------
   // Methode pour mettre à jour l'IHM
   // -------------------------------- */

    public void setVisibleIhmChat (boolean pseudos) {
        frame.setVisible(pseudos);
        lblErreur.setVisible(false);

    }

    public void setMessageErreurIhmChat(String erreur) {
        lblErreur.setText(erreur);
        lblErreur.setVisible(true);
    }

    public void updatePseudos (String pseudosUtilisateur) {

        lblPseudo.setText(pseudosUtilisateur);
    }

    public void updateErreur () {
        lblErreur.setVisible(false);
    }

    public void updateDiscussion (ArrayList<MessageBean> listeMessage) {
        String listeToutLesMessages = "";
        for (int i = 0; i < listeMessage.size() ; i++) {
            listeToutLesMessages += listeMessage.get(i).getUtilisateur().getPseudoUtilisateur() + " : " + listeMessage.get(i).getTextMessage()+"\n";

//            JTextDiscussion.setText(listeToutLesMessages);
        }
        JTextDiscussion.setText(listeToutLesMessages);
    }


    public void cleanZoneTextUtilisateur (){
        textAreaZoneText.setText("");
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

    public JLabel getLblErreur() {
        return lblErreur;
    }

    public void setLblErreur(JLabel lblErreur) {
        this.lblErreur = lblErreur;
    }

    public JButton getBtnEnvoyer() {
        return btnEnvoyer;
    }

    public void setBtnEnvoyer(JButton btnEnvoyer) {
        this.btnEnvoyer = btnEnvoyer;
    }

    public JTextArea getTextAreaZoneText() {
        return textAreaZoneText;
    }

    public void setTextAreaZoneText(JTextArea textAreaZoneText) {
        this.textAreaZoneText = textAreaZoneText;
    }

    public JButton getBtnRefresh() {
        return btnRefresh;
    }

    public void setBtnRefresh(JButton btnRefresh) {
        this.btnRefresh = btnRefresh;
    }

    public JLabel getLblPseudo() {
        return lblPseudo;
    }

    public void setLblPseudo(JLabel lblPseudo) {
        this.lblPseudo = lblPseudo;
    }

    public JTextField getJTextFieldDiscussion() {
        return JTextFieldDiscussion;
    }

    public void setJTextFieldDiscussion(JTextField JTextFieldDiscussion) {
        this.JTextFieldDiscussion = JTextFieldDiscussion;
    }

    public JTextArea getJTextDiscussion() {
        return JTextDiscussion;
    }

    public void setJTextDiscussion(JTextArea JTextDiscussion) {
        this.JTextDiscussion = JTextDiscussion;
    }
}

