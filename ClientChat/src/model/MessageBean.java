package model;

public class MessageBean {
    private String textMessage = "";
    private int id = 0 ;
    private int dateMessage =0;
    private UtilisateurBean utilisateur = null;


    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDateMessage() {
        return dateMessage;
    }

    public void setDateMessage(int dateMessage) {
        this.dateMessage = dateMessage;
    }

    public UtilisateurBean getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(UtilisateurBean utilisateur) {
        this.utilisateur = utilisateur;
    }
}
