package serversample


import com.tran.tchatserver.BDDUtils
import org.apache.logging.log4j.message.Message
import org.springframework.web.bind.annotation.*
import java.lang.Exception

//Ca correspond à un WebService REST
@RestController
class ChatAPI {


    //URL : http://176.175.150.108:8080/test
    @GetMapping("/test")
    fun analyse(): String {

        println("/test")

        return "Ca marche !"
    }

    //URL : http://176.175.150.108:8080/pseudos
    //Retourne la liste de tous les utilisateurs
    @GetMapping("/pseudos")

    fun getPseudos(): Any {

        print("/pseudos")
        try {
            val listPseudos = BDDUtils.selectPseudos()
            return listPseudos
        } catch (e: Exception) {
            return e
        }
    }

    //URL : http://176.175.150.108:8080/messages
    //Retourne la liste de tous les messages
    @GetMapping("/messages")
    fun getMessages(): Any {

        print("/messages")
        try {
            val listMessages = BDDUtils.selectMessages()
            return listMessages
        } catch (e: Exception) {
            return e
        }
    }

    //URL : http://176.175.150.108:8080/saveUtilisateur
    //Ajoute un utilisateur à partir d'un pseudo et retourne l'utilisateur
    @PostMapping("/saveUtilisateur")
    fun saveUtilisateur(@RequestBody utilisateur: UtilisateurBean?): Any? {

        println("/saveUtilisateur : $utilisateur ")
        try {
            if (utilisateur != null) {
                BDDUtils.insertUtilisateurAvecControle(utilisateur.pseudoUtilisateur)

                println("Utilisateur ajouté !")
            } else {
                println("Utilisateur Incorrect ")
            }
            return BDDUtils.getUser(utilisateur?.pseudoUtilisateur)
        } catch (e: Exception) {
            return e
        }
    }

   // URL : http://176.175.150.108:8080/addMessage
   //Ajoute un message
    @PostMapping("/addMessage")
    fun addMessage(@RequestBody message: MessageBean?): Any? {

       println("/addMessage : $message")
       return try {
           if (message != null) {
               //BDDUtils.insertMessageSansControle(message)
               BDDUtils.insertMessage(message)
               println("Message Ajouté")
               return null
           } else {
               println("Message Incorrect ")
           }

       } catch (e: Exception) {
           return e
       }
   }


       //URL : http://176.175.150.108:8080/test2
       @GetMapping("/test2")
       fun test(): String {

           println("/test2")

           return "Ca marche ?"
       }
   }








