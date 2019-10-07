package ihminterfacechat;

import model.UtilisateurBean;
import model.MessageBean;
import javax.swing.*;

import java.util.ArrayList;

public class Controler {


    public Controler controler;
    //Model
    public UtilisateurBean utilisateur;

    //Interface Graphique

    private IHMchat ihmChat;
    private IHMlogin ihmLogin ;



    public Controler() {
        ihmChat = new IHMchat(this);
        ihmLogin = new IHMlogin(this);
        ihmChat.getFrame().setVisible(false); //on rend invisible l'IHMChat
        ihmLogin.getFrame().setVisible(true); //on rend visible l'IHMLogin
        ihmLogin.getLblErreurpseudo().setVisible(false); // on rend la zone d'erreur d l'IHMLogin invisible

    }


    public void onBtnEnvoyer(String texteMessage) {
        ihmChat.updateErreur();
        MessageBean message = new MessageBean();
        message.setTextMessage(texteMessage);
        message.setUtilisateur(utilisateur);
        if (texteMessage == null || texteMessage.trim().length()==0) {
            ihmChat.setMessageErreurIhmChat("Saisie incorrecte"); //gestion erreur
        } else {
            try {
                WSUtils.sendAddMessages(message);
                ihmChat.updateErreur();

                ihmChat.cleanZoneTextUtilisateur(); //vide la zone de texte de l'utilisateur
                onBtnRefresh();

            } catch (Exception e) {
                e.printStackTrace();
                ihmChat.setMessageErreurIhmChat("Erreur : " + e.getMessage());
            }
        }

    }

    public void onBtnRefresh() {
        try {
            ihmChat.updateDiscussion(WSUtils.sendGetMessages());
        } catch (Exception e) {
            e.printStackTrace();
            ihmChat.setMessageErreurIhmChat("Erreur : " + e.getMessage());
        }
    }

    public void onBtnLogin(String pseudo) {

        if (pseudo == null || pseudo.trim().length() == 0) {
            ihmLogin.setMessageErreurIhmLogin("Saisie incorrecte");  //gestion erreur
        } else {
            try {
                this.utilisateur = WSUtils.sendSaveUtilisateur(pseudo);
                System.out.println(utilisateur.getId() + " : " + utilisateur.getPseudoUtilisateur());
                ihmLogin.setVisibleIhmLogin(false); //rend l'IHMLogin invisible
                ihmChat.setVisibleIhmChat(true); // rend l'IHMChat visible
                ihmChat.updatePseudos(pseudo);  // remplace le lblpseudos par celui saisit pas l'utilisateur
            } catch (Exception e) {
                e.printStackTrace();
                ihmLogin.setMessageErreurIhmLogin("Erreur : " + e.getMessage());
            }
        }
    }

    public UtilisateurBean getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(UtilisateurBean utilisateur) {
        this.utilisateur = utilisateur;
    }

    public IHMchat getIhmChat() {
        return ihmChat;
    }

    public void setIhmChat(IHMchat ihmChat) {
        this.ihmChat = ihmChat;
    }

    public IHMlogin getIhmLogin() {
        return ihmLogin;
    }

    public void setIhmLogin(IHMlogin ihmLogin) {
        this.ihmLogin = ihmLogin;
    }

}
