package InterfaceGraphique;

import Requete.Data;
import Requete.Snapshot;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;


import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Base64;

import static Requete.GET.*;


public class GUI extends JFrame {
    private JPanel contentPane;

    private JPanel panel1;

    HistogramInterface histogramInterface;

    private JPanel grapgh1;
    private JButton button1;

    private JLabel jlabeltextclass;

    private JButton défilerButton;

    private JButton timestampButton;

    private TimeSeries seriesAccX;
    private TimeSeries seriesAccY;
    private TimeSeries seriesAccZ;

    TimeSeriesCollection dataset = new TimeSeriesCollection();
    TimeSeriesCollection dataset2 = new TimeSeriesCollection();

    static Data data;

    JFreeChart jfc2;
    JFreeChart jfc;

    static ArrayList<Data> list = new ArrayList<Data>();



    static StringBuilder result = new StringBuilder();

    private Timer timer;

    boolean isAutoScrolling = false;

    private TimeSeries seriesgyroX;
    private TimeSeries seriesgyroY;
    private TimeSeries seriesgyroZ;


    private JTextField timestamp;
    private JPanel panel3;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    JPanel mainPanel;


    public  GUI() {
        setTitle("Série Temporelle");
        setContentPane(contentPane);
        setLocationRelativeTo(null);
        pack();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1500, 800);
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screen.width - this.getSize().width) / 2, (screen.height - this.getSize().height) / 2);
        setResizable(true);

        Border border = new LineBorder(Color.white, 1); // Couleur et largeur de la bordure


        défilerButton.setBorder(border);
        timestampButton.setBorder(border);



        grapgh1.setPreferredSize(new Dimension(800, 300));
        panel3.setPreferredSize(new Dimension(1300, 300));
        panel1.setPreferredSize(new Dimension(1200, 500));

        // Populate comboBox1 with "gyro" and "acceleration" options
        comboBox1.addItem("gyro");
        comboBox1.addItem("acceleration");

        // Populate comboBox2 with "droit" and "tort" options
        comboBox2.addItem("droit");
        comboBox2.addItem("tort");

        défilerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!isAutoScrolling) {
                    // Démarrer le défilement automatique
                    //mettre à 500 pour l'exam
                    timer = new Timer(1000, new ActionListener() {
                        //toutes les 1
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            actionListener.actionPerformed(e);
                        }
                    });
                    timer.start();
                } else {
                    // Arrêter le défilement automatique
                    if (timer != null && timer.isRunning()) {
                        timer.stop();
                        timer = null;
                    }
                }
                isAutoScrolling = !isAutoScrolling;
            }
        });


        timestampButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Capturez l'instantané du graphique en cours

                String selectedgraphique = comboBox1.getSelectedItem().toString();
                BufferedImage chartImage;
                if(selectedgraphique.equals("gyro"))
                {
                    chartImage = jfc.createBufferedImage(800, 600);
                }
                else
                {
                    chartImage = jfc2.createBufferedImage(800, 600);
                }


                // Convertissez l'image en format PNG
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                try {
                    ImageIO.write(chartImage, "png", baos);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                // Encodez l'image en base64
                String base64Image = Base64.getEncoder().encodeToString(baos.toByteArray());

                // Récupérez la sélection TORT ou DROIT depuis la liste déroulante
                String selectedOption = comboBox2.getSelectedItem().toString();

                // Récupérez le timestamp de l'accident
                int accidentTimestamp = Integer.parseInt(timestamp.getText());

                // Récupérez la date et l'heure actuelles pour l'expertise
                //Date expertiseDateTime = new Date();

                LocalDateTime currentDateTime = LocalDateTime.now();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                String formattedDateTime = currentDateTime.format(formatter);

                // Envoyez les données à ORDS pour la sauvegarde dans la base de données
                Snapshot snapshot = new Snapshot();
                snapshot.saveSnapshotToDatabase(base64Image, selectedOption, accidentTimestamp, formattedDateTime);
            }
        });

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Créez une instance de la deuxième interface
                histogramInterface = new HistogramInterface();

                // Affichez la deuxième interface
                histogramInterface.setVisible(true);
            }
        });
    }

    ActionListener actionListener = new ActionListener() {
        private int currentIndex = 0;
        @Override
        public void actionPerformed(ActionEvent e) {


            String valeur = timestamp.getText();
            int valeurint = Integer.parseInt(valeur);
            if(valeurint >= 3581689 && valeurint <= 3583791)
            {



                //converti le jdon de la requete de selction en objt data
                list = ConvertToString(valeurint);


                //affiche le type de la class ds le .form , en recup la classe de l'objt data ds la list

                switch (list.get(0).getClasse()) {
                    case "SLOW" -> {
                        jlabeltextclass.setForeground(Color.GREEN);
                        jlabeltextclass.setText("SLOW");
                    }
                    case "AGGRESSIVE" -> {
                        jlabeltextclass.setForeground(Color.RED);
                        jlabeltextclass.setText("AGGRESSIVE");
                    }
                    case "NORMAL" -> {
                        jlabeltextclass.setForeground(Color.BLUE);
                        jlabeltextclass.setText("NORMAL");
                    }
                }

                //légende , lignes de nos de données
                seriesgyroX = new TimeSeries("gyrox");
                seriesgyroY = new TimeSeries("gyroy");
                seriesgyroZ = new TimeSeries("gyroz");


                seriesgyroX.clear();
                seriesgyroY.clear();
                seriesgyroZ.clear();

                int i=0;

                // 10 valeures à la fois qui vont être affichées , qd on est à la fin  de l'arraylist tu recommences
                for (i=currentIndex; i<currentIndex+10 && i<list.size();i++)
                {
                    //mettre  ds les timesSeries les valeur de la list
                    //on f ça car addorUpdate soule et donc on est obligé de mettre qqchose ds le premier paramètre
                    int year = 2023;
                    double timeInSeconds = i ;

                    int hours = (int) (timeInSeconds / 3600);
                    int minutes = (int) ((timeInSeconds % 3600) / 60);
                    int seconds = (int) (timeInSeconds % 60);

                    //seriesAccX.addOrUpdate(new Second(seconds, minutes, hours, 1, 1, year), list.get(i).getAccx());
                    //seriesAccY.addOrUpdate(new Second(seconds, minutes, hours, 1, 1, year), list.get(i).getAccy());
                    //seriesAccZ.addOrUpdate(new Second(seconds, minutes, hours, 1, 1, year), list.get(i).getAccz());

                    seriesgyroX.addOrUpdate(new Second(seconds, minutes, hours, 1, 1, year), list.get(i).getGyrox());
                    seriesgyroY.addOrUpdate(new Second(seconds, minutes, hours, 1, 1, year), list.get(i).getGyroy());
                    seriesgyroZ.addOrUpdate(new Second(seconds, minutes, hours, 1, 1, year), list.get(i).getGyroz());
                }
                dataset.removeAllSeries();

                //dataset c nos données ds le graph qu'on assigne à nos "légendes"
                dataset.addSeries(seriesgyroX);
                dataset.addSeries(seriesgyroY);
                dataset.addSeries(seriesgyroZ);


                jfc = ChartFactory.createTimeSeriesChart("gyro par rapport au temps",
                        "temps", // x
                        "gyro", // y
                        dataset,
                        true, true, false
                );

                DateAxis domainAxis = (DateAxis) jfc.getXYPlot().getDomainAxis();
                domainAxis.setTickUnit(new DateTickUnit(DateTickUnitType.SECOND, 1));
                domainAxis.setDateFormatOverride(new SimpleDateFormat("mm:ss"));


                ChartPanel cp = new ChartPanel(jfc);
                grapgh1.setLayout(new BorderLayout());
                grapgh1.removeAll();
                grapgh1.add(cp, BorderLayout.CENTER);
                grapgh1.revalidate();
                grapgh1.repaint();


                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                seriesAccX = new TimeSeries("accx");
                seriesAccY = new TimeSeries("accy");
                seriesAccZ = new TimeSeries("accz");

                seriesAccX.clear();
                seriesAccY.clear();
                seriesAccZ.clear();

                for (i=currentIndex; i<currentIndex+10 && i<list.size();i++)
                {
                    int year = 2023;
                    double timeInSeconds = i ;

                    int hours = (int) (timeInSeconds / 3600);
                    int minutes = (int) ((timeInSeconds % 3600) / 60);
                    int seconds = (int) (timeInSeconds % 60);

                    seriesAccX.addOrUpdate(new Second(seconds, minutes, hours, 1, 1, year), list.get(i).getAccx());
                    seriesAccY.addOrUpdate(new Second(seconds, minutes, hours, 1, 1, year), list.get(i).getAccy());
                    seriesAccZ.addOrUpdate(new Second(seconds, minutes, hours, 1, 1, year), list.get(i).getAccz());

                    //seriesgyroX.addOrUpdate(new Second(seconds, minutes, hours, 1, 1, year), list.get(i).getGyrox());
                    //seriesgyroY.addOrUpdate(new Second(seconds, minutes, hours, 1, 1, year), list.get(i).getGyroy());
                    //seriesgyroZ.addOrUpdate(new Second(seconds, minutes, hours, 1, 1, year), list.get(i).getGyroz());
                }

                dataset2.removeAllSeries();

                dataset2.addSeries(seriesAccX);
                dataset2.addSeries(seriesAccY);
                dataset2.addSeries(seriesAccZ);


                jfc2 = ChartFactory.createTimeSeriesChart("acceleration par rapport au temps",
                        "temps", // x
                        "accélération", // y
                        dataset2,
                        true, true, false
                );

                DateAxis domainAxis2 = (DateAxis) jfc2.getXYPlot().getDomainAxis();
                domainAxis2.setTickUnit(new DateTickUnit(DateTickUnitType.SECOND, 1));
                domainAxis2.setDateFormatOverride(new SimpleDateFormat("mm:ss"));


                ChartPanel cp2 = new ChartPanel(jfc2);
                panel3.setLayout(new BorderLayout());
                panel3.removeAll();
                panel3.add(cp2,BorderLayout.EAST);
                panel3.revalidate();
                panel3.repaint();

                button1.setPreferredSize(new Dimension(10, 10));



                //incrémenter notre index pour afficher les 10 prochaines valeurs ,
                // qd on est à la fin de l'arraylist tu recommences , currentIndex à 0
                currentIndex += 1;
                if (currentIndex + 9 >= list.size()) {
                    currentIndex = 0; // Revenez au début si vous atteignez la fin des données.
                    System.out.println("CurrentIndex remis a 0");
                }
            }
            else
            {
                System.out.println("La valeur n'est entre 3 581 689 et 3 583 791 .");
            }

        }
    };




    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GUI frame = new GUI();
            frame.setVisible(true);
        });
    }
}
