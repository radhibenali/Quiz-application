package com.example.demo.Service;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class Chat implements Initializable {

    private Map<String, String> responses;
    @FXML
    public Hyperlink hyperlink;
    @FXML
    public TextArea textchat;
    @FXML
    public TextField ask;
    @FXML
    public WebView web;

    @FXML
    public void hyper(ActionEvent actionEvent) {
        // Implement your hyperlink action here
        web.getEngine().load("https://mvnrepository.com/artifact/org.controlsfx/controlsfx/8.40.11");

    }



    @FXML
    public void userAsk(ActionEvent actionEvent) {
        String input = ask.getText();
        String response = responses.getOrDefault(input, "désolé, je n'ai pas la réponse");
        textchat.appendText("User: " + input + "\n");
        textchat.appendText("Chatbot: " + response + "\n\n");
        ask.clear();
    }

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        responses = new HashMap<>();
        responses.put("je suis", "hhhhhhh mdr tu parles de quoi mon frère");
        responses.put("cv", "oui et toi?");
        responses.put("hamdoulah", "eyh tfadhel bech 7ajtek");
        responses.put("une bonne voiture", "bmw");
        responses.put("couleur", "rouge, blanc, jaune, gris, black inty o dhaw9ek");
        responses.put("prix", "inty o elkarahba");
        responses.put("bmw", "70 fel nhar");
        responses.put("la tunisie", "a7la mefema");
        responses.put("nom", "oui");
        responses.put("donne moi le lien de l'esprit", "https://esprit-tn.com");
        responses.put("salut bot", "merci");
        responses.put("who are you ?", "je suis un bot créé par Leaders");
        responses.put("leaders ?", "leaders leaders ALLez ALLEZ");
        responses.put("malek turky", "manaarfouch chkoun hedha ?? tdhakertou edheka mtaa el hdm5, wenty mnin taarrfou ??? sayeb 3lik menou");
      // TextFields.bindAutoCompletion(ask, responses.keySet());
        TextField textField =  TextFields.createClearablePasswordField();



    }
}