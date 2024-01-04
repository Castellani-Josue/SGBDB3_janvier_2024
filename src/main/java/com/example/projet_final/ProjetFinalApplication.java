package com.example.projet_final;


import InterfaceGraphique.GUI;
import Requete.GET;

public class ProjetFinalApplication extends GUI
{


		/**
		 * @param args the command line arguments
		 */
		public static void main(String[] args) {

			//GET get = new GET(valeurint);
			//get.GET();

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new GUI().setVisible(true);
			}
		});
	}

}
