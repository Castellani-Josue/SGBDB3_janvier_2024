package InterfaceGraphique;

import Requete.Data;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.statistics.HistogramDataset;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import static Requete.GET.*;

public class HistogramInterface extends javax.swing.JFrame {
    double[] accxslow = new double[0];
    double[] accxnormal = new double[0];
    double[] accxfast = new double[0];
    double[] accyslow = new double[0];
    double[] accynormal = new double[0];
    double[] accyfast = new double[0];
    double[] acczslow = new double[0];
    double[] accznormal = new double[0];
    double[] acczfast = new double[0];
    double[] gyroxslow = new double[0];
    double[] gyroxnormal = new double[0];
    double[] gyroxfast = new double[0];
    double[] gyroyslow = new double[0];
    double[] gyroynormal = new double[0];
    double[] gyroyfast = new double[0];
    double[] gyrozslow = new double[0];
    double[] gyroznormal = new double[0];
    double[] gyrozfast = new double[0];

    private JLabel jLabel1;
    private JPanel jpanel1;
    private JPanel jpanel2;
    private JPanel jpanel3;
    private JButton bouttongyro;


    static ArrayList<Data> listslow = new ArrayList<Data>();
    static ArrayList<Data> listfast = new ArrayList<Data>();
    static ArrayList<Data> listnormal = new ArrayList<Data>();

    int testacc = 0;



    private void addGyroButton()
    {
        bouttongyro = new JButton("donnée suivante ->");
        bouttongyro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateHistogramsUsingGyroData();
            }
        });
        this.add(bouttongyro, BorderLayout.PAGE_END);
    }

    private void updateHistogramsUsingGyroData()
    {


        // Fetch gyro data or perform any gyro-related operations here
        // Update hgyrox, hgyroy, hgyroz arrays with gyro data
        // Call the methods to update histograms


        if(testacc == 0)
        {



            showHistogramslownormalfast(accyslow,"accy slow",jpanel1,Color.BLUE);
            showHistogramslownormalfast(accynormal,"accy normal",jpanel2,Color.GREEN);
            showHistogramslownormalfast(accyfast,"accy fast",jpanel3,Color.RED);
            testacc ++;
        }
        else if(testacc == 1)
        {


            showHistogramslownormalfast(acczslow,"accz slow",jpanel1,Color.BLUE);
            showHistogramslownormalfast(accznormal,"accz normal",jpanel2,Color.GREEN);
            showHistogramslownormalfast(acczfast,"accz fast",jpanel3,Color.RED);
            testacc ++;
        }
        else if(testacc == 2)
        {
            showHistogramslownormalfast(gyroxslow,"gyrox slow",jpanel1,Color.BLUE);
            showHistogramslownormalfast(gyroxnormal,"gyrox normal",jpanel2,Color.GREEN);
            showHistogramslownormalfast(gyroxfast,"gyrox fast",jpanel3,Color.RED);
            testacc ++;
        }
        else if(testacc == 3)
        {
            showHistogramslownormalfast(gyroyslow,"gyroy slow",jpanel1,Color.BLUE);
            showHistogramslownormalfast(gyroynormal,"gyroy normal",jpanel2,Color.GREEN);
            showHistogramslownormalfast(gyroyfast,"gyroy fast",jpanel3,Color.RED);
            testacc ++;
        }else if(testacc == 4)
        {
            showHistogramslownormalfast(gyrozslow,"gyroz slow",jpanel1,Color.BLUE);
            showHistogramslownormalfast(gyroznormal,"gyroz normal",jpanel2,Color.GREEN);
            showHistogramslownormalfast(gyrozfast,"gyroz fast",jpanel3,Color.RED);
            testacc ++;
        }else if(testacc == 5)
        {
            showHistogramslownormalfast(accxslow,"accx slow",jpanel1,Color.BLUE);
            showHistogramslownormalfast(accxnormal,"accx normal",jpanel2,Color.GREEN);
            showHistogramslownormalfast(accxfast,"accx fast",jpanel3,Color.RED);
            testacc =0;
        }

    }

    private void Insert()
    {
        listslow = Convertslow();
        listnormal = Convertnormal();
        listfast = Convertfast();

        accxslow = new double[listslow.size()];
        accxnormal = new double[listnormal.size()];
        accxfast = new double[listfast.size()];
        accyslow = new double[listslow.size()];
        accynormal = new double[listnormal.size()];
        accyfast = new double[listfast.size()];
        acczslow = new double[listslow.size()];
        accznormal = new double[listnormal.size()];
        acczfast = new double[listfast.size()];
        gyroxslow = new double[listslow.size()];
        gyroxnormal = new double[listnormal.size()];
        gyroxfast = new double[listfast.size()];
        gyroyslow = new double[listslow.size()];
        gyroynormal = new double[listnormal.size()];
        gyroyfast = new double[listfast.size()];
        gyrozslow = new double[listslow.size()];
        gyroznormal = new double[listnormal.size()];
        gyrozfast = new double[listfast.size()];

        System.out.println("avant le for");
        for(int i = 0;i<listslow.size();i++) {
            accxslow[i] = listslow.get(i).getAccx();
            acczslow[i] = listslow.get(i).getAccz();
            accyslow[i] = listslow.get(i).getAccy();
            gyroxslow[i] = listslow.get(i).getGyrox();
            gyrozslow[i] = listslow.get(i).getGyroz();
            gyroyslow[i] = listslow.get(i).getGyroy();
        }
        for(int i = 0;i<listnormal.size();i++) {
                accxnormal[i] = listnormal.get(i).getAccx();
                accznormal[i] = listnormal.get(i).getAccz();
                accynormal[i] = listnormal.get(i).getAccy();
                gyroxnormal[i] = listnormal.get(i).getGyrox();
                gyroznormal[i] = listnormal.get(i).getGyroz();
                gyroynormal[i] = listnormal.get(i).getGyroy();
        }
        System.out.println(accxnormal.length);
        for(int i = 0;i<listfast.size();i++) {
            accxfast[i] = listfast.get(i).getAccx();
            acczfast[i] = listfast.get(i).getAccz();
            accyfast[i] = listfast.get(i).getAccy();
            gyroxfast[i] = listfast.get(i).getGyrox();
            gyrozfast[i] = listfast.get(i).getGyroz();
            gyroyfast[i] = listfast.get(i).getGyroy();
        }
    }

    public HistogramInterface() {
        // Initialize your components here (histograms, labels, etc.)

        // Example: Add a label
        JLabel label = new JLabel("Histogram Interface");
        this.add(label);

        Insert();
        showHistogramslownormalfast(accxslow,"accx slow",jpanel1,Color.BLUE);
        showHistogramslownormalfast(accxnormal,"accx normal",jpanel2,Color.GREEN);
        showHistogramslownormalfast(accxfast,"accx fast",jpanel3,Color.RED);

        // Use BorderLayout for the main content
        setLayout(new BorderLayout());

        // Add the histogram panels to the center
        JPanel histogramPanelContainer = new JPanel(new GridLayout(1, 3));
        histogramPanelContainer.add(jpanel1);
        histogramPanelContainer.add(jpanel2);
        histogramPanelContainer.add(jpanel3);
        add(histogramPanelContainer, BorderLayout.CENTER);

        addGyroButton();  // Add the gyro button to the interface

        // Configure window parameters
        this.setSize(1500, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }



    private void showHistogramslownormalfast(double[] donnée,String affichage,JPanel panel,Paint paint) {
        HistogramDataset dataset = new HistogramDataset();
        dataset.addSeries("key", donnée, 30);

        JFreeChart jfc = ChartFactory.createHistogram("Histogram "+affichage,
                affichage, "Frequency", dataset);

        jfc.getXYPlot().getRenderer().setSeriesPaint(0, paint);


        ChartPanel cp = new ChartPanel(jfc);
        panel.setLayout(new BorderLayout()); // Assurez-vous que le layout du PanelTest est défini pour BorderLayout ou un autre approprié.
        panel.removeAll();
        panel.add(cp, BorderLayout.CENTER); // Ajoutez le ChartPanel au centre du PanelTest.
        panel.revalidate();
        panel.repaint();
    }




    // Add this helper method to convert int array to double array




}