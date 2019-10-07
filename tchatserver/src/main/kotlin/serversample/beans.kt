package serversample

data class UtilisateurBean (var pseudoUtilisateur : String){
    var id = 0

}

data class MessageBean (var textMessage : String?) {
    var id = 0
    var dateMessage = 0
    var utilisateur : UtilisateurBean? = null
}
