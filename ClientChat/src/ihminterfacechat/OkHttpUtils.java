package ihminterfacechat;

import okhttp3.*;

public class OkHttpUtils {

    //methode sendGest
    public static String sendGetOkHttpRequest(String url) throws Exception {
        System.out.println("Url : " + url);
        OkHttpClient client = new OkHttpClient();
        //Création de la requete
        Request request = new Request.Builder().url(url).build();
        //Execution de la requête
        Response response = client.newCall(request).execute();
        //Analyse du code retour
        if (response.code() < 200 || response.code() > 299) {
            throw new Exception("Réponse du serveur incorrect : " + response.code());
        }
        else {
            //Résultat de la requete.
            return response.body().string();
        }
    }

        //Méthode sendPost
        public static String sendPostOkHttpRequest(String url, String paramJson )
        throws Exception {
        System.out.println("Url : " + url+ "  json = " + paramJson);
        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        //Corps de la requête
        RequestBody body = RequestBody.create(JSON, paramJson);
        //Création de la requete
         Request request = new Request.Builder().url(url).post(body).build();
         //Execution de la requête
         Response response = client.newCall(request).execute();
         //Analyse du code retour
         if (response.code() < 200 || response.code() > 299) {
             throw new Exception("Réponse du serveur incorrect : " + response.code());
         }
            else {
                //Résultat de la requete.
            return response.body().string(); }
        }
}

