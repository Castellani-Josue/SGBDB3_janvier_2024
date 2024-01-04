package Requete;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Snapshot
{
    public void saveSnapshotToDatabase(String base64Image, String option, int accidentTimestamp, String expertiseDateTime) {
        try {
            // Construisez le corps de la requête JSON avec les données
            JSONObject data = new JSONObject();
            data.put("blobdata", base64Image);
            data.put("verdict", option);
            data.put("timestamp", accidentTimestamp);
            data.put("dateexp", expertiseDateTime);
            String urlParameters = data.toString();
            byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8 );
            int postDataLength = postData.length;


            URL url = new URL("http://192.168.137.160:8080/ords/projetfinal/final/image");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setInstanceFollowRedirects(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("charset", "utf-8");
            conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
            conn.setUseCaches(false);




            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.write(postData);
            wr.close();



            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                // La requête a réussi
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String response;
                while ((response = in.readLine()) != null) {
                    System.out.println(response);
                }
                in.close();
            } else {
                // Gérez l'erreur en conséquence
                System.err.println("Erreur HTTP : " + responseCode);
            }


            //con.disconnect();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("teste insertion BD");
    }
}
