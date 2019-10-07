import ihminterfacechat.Controler;
import ihminterfacechat.IHMchat;
import ihminterfacechat.IHMlogin;
import model.UtilisateurBean;

import java.awt.*;

public class Main {

        public static void main(String[] args) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        //Création de l'HIM

                        /*On créé le controleur*/
                        Controler controler = new Controler();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }


    }

