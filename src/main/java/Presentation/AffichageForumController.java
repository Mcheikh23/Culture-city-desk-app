/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.Sujet;
import Service.SujetService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AffichageForumController implements Initializable {

    @FXML
    private VBox pnItems;
    
          @FXML
    private AnchorPane id_affichage_Forum;

   private static SujetService sujetService = new SujetService();
    /**
     * Initializes the controller class.
     */
   private static Sujet sujet_à_ouvrir = new Sujet();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadUserGrid();
        } catch (SQLException ex) {
            Logger.getLogger(AffichageForumController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
   private void loadUserGrid() throws SQLException {
		List<Sujet> sujets = fetchForums();
		Node[] nodes = new Node[sujets.size()];

		AtomicInteger i = new AtomicInteger(0);
		sujets.forEach(sujet -> {
			int j = i.getAndIncrement();
			Node node = nodes[j] = loadNewItemNode();

			displaySujetDetails(node, sujet);

			setupActions(node, sujet, j);

			setHoverStyleForNode(nodes, j);

			pnItems.getChildren().add(node);
		});

		if (nodes.length > 0) {
			pnItems.setStyle("-fx-background-color : #53639F");
			pnItems.toFront();
		}
	}


	private List<Sujet> fetchForums() throws SQLException {
		try {
			return sujetService.readAllS();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}

		return Collections.emptyList();
	}

	private Node loadNewItemNode() {
		try {
			return FXMLLoader.load(getClass().getResource("/fxml/Item.fxml"));
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

	private void displaySujetDetails(Node node, Sujet sujet) {
		
		Label item_description = (Label) node.lookup(".item_description");
		item_description.setText(sujet.getDate());
               
                Label item_date = (Label) node.lookup(".item_date");
		item_date.setText(sujet.getDescription_f());
		
		// other properties
		// ...
	}

	private void setupActions(Node node, Sujet sujet, int index) {
		Button openButton = (Button) node.lookup(".item_action_open");
		openButton.setOnMouseClicked(OpenEventHandler(sujet, index));
	}

	private void setHoverStyleForNode(Node[] nodes, int i) {
		final int j = i;
		nodes[i].setOnMouseEntered(even -> {
			nodes[j].setStyle("-fx-background-color : #0A0E3F");
		});
		nodes[i].setOnMouseExited(even -> {
			nodes[j].setStyle("-fx-background-color : #02030A");
		});
	}

	private EventHandler<MouseEvent> OpenEventHandler(Sujet sujet, int index) {
		return event -> {
			
                   try {
                                       sujet_à_ouvrir=sujet;

				AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/Detail_Sujet.fxml"));
				id_affichage_Forum.getChildren().clear();
				id_affichage_Forum.getChildren().add(newLoadedPane);
			} catch (IOException ex) {
				Logger.getLogger(AcceuilForumController.class.getName()).log(Level.SEVERE, null, ex);
			}
		};
	}

    public static Sujet getSujet_à_ouvrir() {
        return sujet_à_ouvrir;
    }

    public static void setSujet_à_ouvrir(Sujet sujet_à_ouvrir) {
        AffichageForumController.sujet_à_ouvrir = sujet_à_ouvrir;
    }

	
}
