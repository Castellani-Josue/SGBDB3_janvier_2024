package Requete;

public class Data
{
    private float accx;
    private float accy;
    private float accz;
    private float gyrox;
    private float gyroy;
    private float gyroz;
    private String classe;
    private int timestmp;
    public Data(float accx,float accy,float accz, float gyrox,float gyroy,float gyroz,String classe,int timestmp)
    {
        this.accx = accx;
        this.accy = accy;
        this.accz = accz;
        this.gyrox = gyrox;
        this.gyroy = gyroy;
        this.gyroz = gyroz;
        this.classe = classe;
        this.timestmp = timestmp;
    }


    public float getAccx() {
        return accx;
    }

    public String getClasse() {
        return classe;
    }

    public float getAccy() {
        return accy;
    }

    public float getAccz() {
        return accz;
    }

    public float getGyrox() {
        return gyrox;
    }

    public float getGyroy() {
        return gyroy;
    }

    public float getGyroz() {
        return gyroz;
    }

    public int getTimestmp() {
        return timestmp;
    }


}
