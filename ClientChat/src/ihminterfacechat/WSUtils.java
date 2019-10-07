package ihminterfacechat;

import com.google.gson.Gson;
import model.MessageBean;
import model.UtilisateurBean;

import java.util.ArrayList;
import java.util.Arrays;


public class WSUtils {

    private final static String URL = "http://176.175.150.108:8080/";
    private final static Gson gson = new Gson();


    public static void main(String[] args) throws Exception {

        /*Test sendGetPseudos*/
//        ArrayList<UtilisateurBean> jsonPseudo = sendGetPseudos();
//        printPseudos(jsonPseudo);
        /*Test sendGetMessages*/
//        ArrayList<MessageBean> jsonMessage = sendGetMessages();
//        printMessages(jsonMessage);
        /*Envoie un utilisateur à partir du client*/
//        sendSaveUtilisateur("Kyky");

        /*Envoie un msg depuis le client ( seulement si l'utilisateur n'est pas null)*/
//        UtilisateurBean utilisateur = new UtilisateurBean();
//        utilisateur.setId(1);
//        utilisateur.setPseudoUtilisateur("Kyky");
//        MessageBean message = new MessageBean();
//        message.setTextMessage("Mon message client");
//        message.setUtilisateur(utilisateur);
//        sendAddMessages(message);
    }


    /*----------------------------------METHODS---------------------------------------------------------*/

    /*Récupère une Array en JSON et le parse en Java puis transforme l'Array en ArrayList*/
    public static ArrayList<UtilisateurBean> sendGetPseudos() throws Exception {
        String json = OkHttpUtils.sendGetOkHttpRequest(URL + "pseudos");
//        System.out.println(json);

        Gson gson = new Gson();
        UtilisateurBean[] utilisateurTab = gson.fromJson(json, UtilisateurBean[].class);
        ArrayList<UtilisateurBean> listUtilisateur = new ArrayList<>(Arrays.asList(utilisateurTab));

        return listUtilisateur;
    }

    /*Récupère une Array en JSON et le parse en Java puis transforme l'Array en ArrayList*/
    public static ArrayList<MessageBean> sendGetMessages() throws Exception {
        String json = OkHttpUtils.sendGetOkHttpRequest(URL + "messages");

        Gson gson = new Gson();
        MessageBean[] utilisateurTab = gson.fromJson(json, MessageBean[].class);
        ArrayList<MessageBean> listMessage = new ArrayList<>(Arrays.asList(utilisateurTab));

        return listMessage;
    }

/*Permet d'enregistré un nouvel utilisateur s'il n'existe pas encore*/
    public static UtilisateurBean sendSaveUtilisateur(String pseudo) throws Exception {
        String parametre = "saveUtilisateur";

        UtilisateurBean utilisateurSend = new UtilisateurBean();
        utilisateurSend.setPseudoUtilisateur(pseudo);
        String jsonTest = new Gson().toJson(utilisateurSend);
        utilisateurSend.setId(utilisateurSend.getId());

        String json = OkHttpUtils.sendPostOkHttpRequest(URL + parametre, jsonTest);


        UtilisateurBean utilisateurReceive = gson.fromJson(json, UtilisateurBean.class);
        return utilisateurReceive;
}

/*Permet d'envoyer un message à partir du client*/
    public static MessageBean sendAddMessages(MessageBean message) throws Exception {

        String jsonTest = new Gson().toJson(message);

        System.out.println(OkHttpUtils.sendPostOkHttpRequest(URL + "addMessage", jsonTest));

        return message;
    }

    /*Affiche Utilisateurs*/
    private static void printPseudos(ArrayList<UtilisateurBean> pseudoUtilisateur) {
        for (UtilisateurBean utilisateur : pseudoUtilisateur) {
            System.out.println("id:" + utilisateur.getId() + "\npseudoUtilisateur: " + utilisateur.getPseudoUtilisateur());
        }
    }
    /*Affiche Messages*/
    private static void printMessages(ArrayList<MessageBean> messagesUtilisateur) {
        for (MessageBean message : messagesUtilisateur) {
            System.out.println("Utilisateur: " + message.getUtilisateur().getPseudoUtilisateur() + " Message : " + message.getTextMessage() );
        }
    }


}
