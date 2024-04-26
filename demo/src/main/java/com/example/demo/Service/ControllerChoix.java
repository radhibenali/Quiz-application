package com.example.demo.Service;

import com.example.demo.Util.MyConnection;
import com.example.demo.entity.Choix;
import com.example.demo.entity.Question;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ControllerChoix implements Initializable {
    public ObservableList<Choix> data = FXCollections.observableArrayList();
    @FXML
    public TextField desc;
    @FXML
    public TableColumn <Choix,String> reponse_q;
    @FXML
    public Label rep1;
    @FXML
    public TextField rep;
    int index = -1;

    @FXML
    public AnchorPane Ah;
    @FXML
    public AnchorPane ah1;
    @FXML
    public TextField idfa;
    @FXML
    public Label description1;
    @FXML
    public TableColumn<Choix, Integer> id;
    @FXML
    public TableColumn<Choix, String> description;
    @FXML
    public TableView<Choix> table_choix;
    @FXML
    public Button btn1;
    @FXML
    public Button btn2;
    @FXML
    public TextField marque;
    @FXML
    public Label id1;
    @FXML
    public Button supr;

    @FXML
    public void selecterChoix(MouseEvent mouseEvent) {
        index = table_choix.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        idfa.setText(String.valueOf(id.getCellData(index)));
        desc.setText(description.getCellData(index));
        rep.setText(reponse_q.getCellData(index));
    }

    @FXML
    public void checkstringa(KeyEvent event) {
        if (event.getCharacter().matches(("[^\\e\t\r\\d+$]"))){
            event.consume();
        }

    }

    @FXML
    public void suprimerah(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText("Êtes-vous sûr de vouloir supprimer cet élément ?");
        alert.setContentText("Cette action est irréversible.");

        // ajouter des boutons personnalisés à l'alerte
        ButtonType ouiButton = new ButtonType("Oui");
        ButtonType nonButton = new ButtonType("Non");

        alert.getButtonTypes().setAll(ouiButton, nonButton);

        // afficher l'alerte et attendre la réponse de l'utilisateur
        alert.showAndWait().ifPresent(reponse -> {
            if (reponse == ouiButton) {
                Choix v = table_choix.getSelectionModel().getSelectedItem();
                serviceChoix rc = new serviceChoix();
                rc.supprimerChoix(v);
                updateTable();
            } else if (reponse == nonButton) {
                // l'utilisateur a cliqué sur "Non", ne rien faire
            }
        });}


    @FXML
    public void modifierah(ActionEvent actionEvent) {          {  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de modifier");
        alert.setHeaderText("Êtes-vous sûr de vouloir modifier cet élément ?");
        alert.setContentText("Cette action est irréversible.");
        ButtonType ouiButton = new ButtonType("Oui");
        ButtonType nonButton = new ButtonType("Non");
        alert.getButtonTypes().setAll(ouiButton, nonButton);

        // afficher l'alerte et attendre la réponse de l'utilisateur
        alert.showAndWait().ifPresent(reponse -> {
            if (reponse == ouiButton){

                serviceChoix rc = new serviceChoix();
                String var1=idfa.getText();
                String var2=desc.getText();
                String var3=rep.getText();



                int var4=Integer.parseInt(var1);

                Choix r = new Choix() ;
                r.setId(var4);
                r.setDescription(var2);
                r.setDescription(var3);


                r=table_choix.getSelectionModel().getSelectedItem();
                rc.modifierChoix(r,var4,var2,var3);
                updateTable();
                idfa.clear();
                desc.clear();
                rep.clear();
            }

            else if (reponse == nonButton) {
            }
        });

    }
    }

    @FXML
    public void ajooter(ActionEvent actionEvent) {
        String t = idfa.getText();
        if (t.isEmpty() || !t.matches("\\d+")) {
            // Alert if t is empty or not a valid integer
            Alert alert = new Alert(Alert.AlertType.WARNING, "Veuillez entrer un entier valide pour t.");
            alert.showAndWait();
        } else if (desc.getText().isEmpty()) {
            // Alert if desc is empty
            Alert alert = new Alert(Alert.AlertType.WARNING, "Veuillez remplir la description.");
            alert.showAndWait();
        } else {
            serviceChoix rc = new serviceChoix();
            int var4 = Integer.parseInt(t);
            Choix r = new Choix();
            r.setId(var4);
            r.setDescription(desc.getText());
            r.setReponse_q(rep.getText());
            rc.AjouterChoix(r);
            updateTable();
            idfa.clear();
            desc.clear();
            rep.clear();
        }
    }

    @FXML
    public void checkentier(KeyEvent event) {if (event.getCharacter().matches(("[^\\e\t\r\\d+$]"))){
        event.consume();
    }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        show();
    }

    private void show() {
        try {
            String query;
            query = "SELECT id, description,reponse_q FROM choix";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                int idValue = rs.getInt("id");
                String descriptionValue = rs.getString("description");
                String reponse_qValue = rs.getString("reponse_q");
                data.add(new Choix(idValue, descriptionValue,reponse_qValue));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        id.setCellValueFactory(new PropertyValueFactory<Choix,Integer>("id"));
        description.setCellValueFactory(new PropertyValueFactory<Choix,String>("description"));
        reponse_q.setCellValueFactory(new PropertyValueFactory<Choix,String>("reponse_q"));

        table_choix.setItems(data);
    }

    ObservableList<Choix> Choix;
    serviceChoix rcd = new serviceChoix();

    public void updateTable() {
        Choix = (ObservableList<com.example.demo.entity.Choix>) rcd.RecupererChoix();
        table_choix.setItems(Choix);
    }
}