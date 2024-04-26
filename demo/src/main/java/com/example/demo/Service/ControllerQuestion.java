package com.example.demo.Service;

import com.example.demo.Util.MyConnection;
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

public class ControllerQuestion implements Initializable {
    public ObservableList<Question> data = FXCollections.observableArrayList();
    @FXML
    public TextField desc;
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
    public TableColumn<Question, Integer> id;
    @FXML
    public TableColumn<Question, String> description;
    @FXML
    public TableView<Question> table_question;
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
    public void selectervoitureadmin(MouseEvent mouseEvent) {
        index = table_question.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        idfa.setText(String.valueOf(id.getCellData(index)));
        desc.setText(description.getCellData(index));
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
        alert.showAndWait().ifPresent(reponse ->{
            if (reponse == ouiButton) {
                Question v = new Question();
                v= table_question.getSelectionModel().getSelectedItem();
                rcd.supprimerQuestion(v);
                updateTable();
            }
            else if (reponse == nonButton) {
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

                serviceQuestion rc = new serviceQuestion();
                String var1=idfa.getText();
                String var2=desc.getText();



                int var4=Integer.parseInt(var1);

                Question r = new Question() ;
                r.setId(var4);
                r.setDescription(var2);


                r=table_question.getSelectionModel().getSelectedItem();
                rc.modifierQuestion(r,var4,var2);
                updateTable();
                idfa.clear();
                desc.clear();
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
        serviceQuestion rc = new serviceQuestion();
        int var4 = Integer.parseInt(t);
        Question r = new Question();
        r.setId(var4);
        r.setDescription(desc.getText());

        try {
            // Check for duplication
            if (rc.isDuplicateQuestion(r)) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Une question avec cet ID existe déjà.");
                alert.showAndWait();
            } else {
                rc.AjouterQuestion(r);
                updateTable();
                idfa.clear();
                desc.clear();
            }
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Une erreur s'est produite : " + ex.getMessage());
            alert.showAndWait();
        }
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
            query = "SELECT id, description FROM question";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                int idValue = rs.getInt("id");
                String descriptionValue = rs.getString("description");
                data.add(new Question(idValue, descriptionValue));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        id.setCellValueFactory(new PropertyValueFactory<Question,Integer>("id"));
        description.setCellValueFactory(new PropertyValueFactory<Question,String>("description"));

        table_question.setItems(data);
    }

    ObservableList<Question> Question;
    serviceQuestion rcd = new serviceQuestion();

    public void updateTable() {
        Question = (ObservableList<com.example.demo.entity.Question>) rcd.RecupererQuestion();
        table_question.setItems(Question);
    }
}