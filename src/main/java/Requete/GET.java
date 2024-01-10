package Requete;

import org.json.JSONObject;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

public class GET {
    static Data data;

    static Data data2;

    static Data data3;

    static Data data4;

    static ArrayList<Data> list = new ArrayList<Data>();

    static ArrayList<Data> listslow = new ArrayList<Data>();
    static ArrayList<Data> listnormal = new ArrayList<Data>();
    static ArrayList<Data> listfast = new ArrayList<Data>();
    public static float accz;
    public static float accy;
    public static float accx;
    public static float gyrox;
    public static float gyroy;

    public static float gyroz;

    public static String classe;
    public static int timestmp;

    public static float acczslow;
    public static float accyslow;
    public static float accxslow;
    public static float gyroxslow;
    public static float gyroyslow;

    public static float gyrozslow;

    public static float accznormal;
    public static float accynormal;
    public static float accxnormal;
    public static float gyroxnormal;
    public static float gyroynormal;

    public static float gyroznormal;

    public static float acczfast;
    public static float accyfast;
    public static float accxfast;
    public static float gyroxfast;
    public static float gyroyfast;

    public static float gyrozfast;

    public static String classefast;

    public static String classenormal;

    public static String classeslow;

    public static int timestmpslow;

    public static int timestmpnormal;

    public static int timestmpfast;



    static StringBuilder result = new StringBuilder();

    static StringBuilder result2 = new StringBuilder();

    static StringBuilder result3 = new StringBuilder();

    static StringBuilder result4 = new StringBuilder();

    //3581700
    //3582600

    public static ArrayList<Data> ConvertToString(int valeurint) {

        System.out.println("Avant le try");
    if(list.size()==0) {


        try {
            //valeure = 3581700;
            URL url = new URL("http://192.168.137.160:8080/ords/projetfinal/final/train/" + valeurint);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            System.out.println("Apres le setRequestMethod");

            BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
                System.out.println(line);
                System.out.println("Bjr");
            }
            rd.close();

            JSONObject received = new JSONObject(new String(result));
            JSONArray jsonres = (JSONArray) received.get("items");
            System.out.println("testaffiche" + jsonres.length());

            for (int i = 0; i < jsonres.length(); i++) {
                JSONObject jsonobj = jsonres.getJSONObject(i);

                        accz = jsonobj.getFloat("accz");
                        accy = jsonobj.getFloat("accy");
                        accx = jsonobj.getFloat("accx");
                        gyrox = jsonobj.getFloat("gyrox");
                        gyroy = jsonobj.getFloat("gyroy");
                        gyroz = jsonobj.getFloat("gyroz");
                        classe = jsonobj.getString("class");
                        timestmp = jsonobj.getInt("timestamp");

                    data = new Data(accx, accy, accz, gyrox, gyroy, gyroz, classe, timestmp);
                    list.add(data);
                    System.out.println(list);


                }


            System.out.println(jsonres.length());

            System.out.println(list.get(1).getAccx());


            return list;


        } catch (Exception e) {
            System.out.println("Error in getting details(GET) : " + e);
        }


    }
        System.out.println("donnee deja recup");


        return list;
    }


    public static ArrayList<Data> Convertslow() {

        try {

            URL url = new URL("http://192.168.137.160:8080/ords/projetfinal/final/recupslow");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            System.out.println("Apres le setRequestMethod");

            BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String line;
            while ((line = rd.readLine()) != null) {
                result2.append(line);
                System.out.println(line);
            }
            rd.close();

            JSONObject received = new JSONObject(new String(result2));
            JSONArray jsonres = (JSONArray) received.get("items");
            System.out.println("testaffiche" + jsonres.length());

            for (int i = 0; i < jsonres.length(); i++) {
                JSONObject jsonobj = jsonres.getJSONObject(i);

                acczslow = jsonobj.getFloat("accz");
                accyslow = jsonobj.getFloat("accy");
                accxslow = jsonobj.getFloat("accx");
                gyroxslow = jsonobj.getFloat("gyrox");
                gyroyslow = jsonobj.getFloat("gyroy");
                gyrozslow = jsonobj.getFloat("gyroz");
                classeslow = jsonobj.getString("class");
                timestmpslow = jsonobj.getInt("timestamp");

                data2 = new Data(accxslow, accyslow, acczslow, gyroxslow, gyroyslow, gyrozslow, classeslow, timestmpslow);
                listslow.add(data2);
                System.out.println(listslow);


            }


            System.out.println(jsonres.length());



            return listslow;


        } catch (Exception e) {
            System.out.println("Error in getting details(GET) : " + e);
        }


        System.out.println("donnee deja recup");


        return listslow;

    }

    public static ArrayList<Data> Convertnormal() {

        try {

            URL url = new URL("http://192.168.137.160:8080/ords/projetfinal/final/recupnormal");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            System.out.println("Apres le setRequestMethod");

            BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String line;
            while ((line = rd.readLine()) != null) {
                result3.append(line);
                System.out.println(line);
            }
            rd.close();

            JSONObject received = new JSONObject(new String(result3));
            JSONArray jsonres = (JSONArray) received.get("items");
            System.out.println("testaffiche" + jsonres.length());

            for (int i = 0; i < jsonres.length(); i++) {
                JSONObject jsonobj = jsonres.getJSONObject(i);

                accznormal = jsonobj.getFloat("accz");
                accynormal = jsonobj.getFloat("accy");
                accxnormal = jsonobj.getFloat("accx");
                gyroxnormal = jsonobj.getFloat("gyrox");
                gyroynormal = jsonobj.getFloat("gyroy");
                gyroznormal = jsonobj.getFloat("gyroz");
                classenormal = jsonobj.getString("class");
                timestmpnormal = jsonobj.getInt("timestamp");


                data3 = new Data(accxnormal, accynormal, accznormal, gyroxnormal, gyroynormal, gyroznormal, classenormal, timestmpnormal);
                listnormal.add(data3);
                System.out.println(listnormal);


            }


            System.out.println(jsonres.length());



            return listnormal;


        } catch (Exception e) {
            System.out.println("Error in getting details(GET) : " + e);
        }


        System.out.println("donnee deja recup");


        return listnormal;

    }
    public static ArrayList<Data> Convertfast() {

        try {

            URL url = new URL("http://192.168.137.160:8080/ords/projetfinal/final/recupfast");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            System.out.println("Apres le setRequestMethod");

            BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String line;
            while ((line = rd.readLine()) != null) {
                result4.append(line);
                System.out.println(line);
            }
            rd.close();

            JSONObject received = new JSONObject(new String(result4));
            JSONArray jsonres = (JSONArray) received.get("items");
            System.out.println("testaffiche" + jsonres.length());

            for (int i = 0; i < jsonres.length(); i++) {
                JSONObject jsonobj = jsonres.getJSONObject(i);

               acczfast = jsonobj.getFloat("accz");
                accyfast = jsonobj.getFloat("accy");
                accxfast = jsonobj.getFloat("accx");
                gyroxfast = jsonobj.getFloat("gyrox");
                gyroyfast = jsonobj.getFloat("gyroy");
                gyrozfast = jsonobj.getFloat("gyroz");
                classefast = jsonobj.getString("class");
                timestmpfast = jsonobj.getInt("timestamp");


                data4 = new Data(accxfast, accyfast, acczfast, gyroxfast, gyroyfast, gyrozfast, classefast, timestmpfast);
                listfast.add(data4);
                System.out.println(listfast);


            }


            System.out.println(jsonres.length());



            return listfast;


        } catch (Exception e) {
            System.out.println("Error in getting details(GET) : " + e);
        }


        System.out.println("donnee deja recup");


        return listfast;

    }
}
