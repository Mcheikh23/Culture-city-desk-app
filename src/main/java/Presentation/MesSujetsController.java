/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.Sujet;
import Service.SujetService;
import com.mycompany.projetman.MainApp;
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
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class MesSujetsController implements Initializable {

    @FXML
    private AnchorPane id_affichage_Forum;
    @FXML
    private VBox pnItems;

    /**
     * Initializes the controller class.
     */
    
     private static SujetService sujetService = new SujetService();
     private static Sujet sujet_modifié = new Sujet();

    public static Sujet getSujet_modifié() {
        return sujet_modifié;
    }

    public static void setSujet_modifié(Sujet sujet_modifié) {
        MesSujetsController.sujet_modifié = sujet_modifié;
    }
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {
            loadUserGrid();
        } catch (SQLException ex) {
            Logger.getLogger(AffichageForumController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
     private void loadUserGrid() throws SQLException {
		List<Sujet> sujets = fetchForumsByIdUser();
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

		if (nodes.length >= 0) {
			pnItems.setStyle("-fx-background-color : #53639F");
			pnItems.toFront();
		}
	}


	private List<Sujet> fetchForumsByIdUser() throws SQLException {
		try {
			return sujetService.FindSujetByIdUser(MainApp.user.getId());
		} catch (RuntimeException e) {
			e.printStackTrace();
		}

		return Collections.emptyList();
	}

	private Node loadNewItemNode() {
		try {
			return FXMLLoader.load(getClass().getResource("/fxml/Item_Mes_sujets.fxml"));
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

	private void displaySujetDetails(Node node, Sujet sujet) {
		
		Label item_description = (Label) node.lookup(".item_description");
		item_description.setText(sujet.getDescription_f());
                
                Label item_date = (Label) node.lookup(".item_date");
		item_date.setText(sujet.getDate());
		
		// other properties
		// ...
	}

	private void setupActions(Node node, Sujet sujet, int index) {
            
		Button deleteButton = (Button) node.lookup(".item_action_supprimer");
		deleteButton.setOnMouseClicked(DeleteEventHandler(sujet, index));
                Button modifierButton = (Button) node.lookup(".item_action_modifier");
               
		modifierButton.setOnMouseClicked(UpdateEventHandler(sujet, index));
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

	private EventHandler<MouseEvent> DeleteEventHandler(Sujet sujet, int index) {
		return event -> {
			
                  sujetService.supprimerSujet(sujet);
			
			pnItems.getChildren().remove(index);
                        Image img = new Image("confirmation.jpg");
         Notifications notificationBuilder = Notifications.create()
                 .title("Sujet Supprimé")
                 .text("Vous avez supprimé votre sujet")
                 .graphic(new ImageView(img))
                 .hideAfter(Duration.seconds(5))
                 .position(Pos.TOP_RIGHT);
         
         notificationBuilder.showError();
		};
	}
        
        private EventHandler<MouseEvent> UpdateEventHandler(Sujet sujet, int index) {
		return event -> {
			
                  
                    try {
                         sujet_modifié=sujet;
                       AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/ModifierSujet.fxml"));
                        id_affichage_Forum.getChildren().clear();
			id_affichage_Forum.getChildren().add(newLoadedPane);
                    } catch (IOException ex) {
                        Logger.getLogger(MesSujetsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
				
		};
	}
    
}
