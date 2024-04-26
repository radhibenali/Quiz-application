package com.example.demo.Service;
import javafx.application.Platform;

import com.example.demo.entity.Choix;
import com.example.demo.entity.Question;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class QuizControllerFXML implements Initializable {
    @FXML
    public Label questionLabel;
    @FXML
    public Button trueButton;
    @FXML
    public Button falseButton;

    private serviceQuestion serviceQuestion;
    private List<Question> questions;
    private int currentQuestionIndex;

    private int score; // Variable to track the score




    @FXML
    public void handleChoiceButtonClick(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String userSelection = clickedButton.getText();

        // Set the style of the clicked button to indicate user selection
        clickedButton.setStyle("-fx-background-color: yellow;");

        Question currentQuestion = questions.get(currentQuestionIndex);
        List<Choix> choices = serviceQuestion.getChoicesForQuestion(currentQuestion.getId());

        for (Choix choix : choices) {
            if (serviceQuestion.checkUserChoice(choix, userSelection)) {
                // User's choice is correct
                score++; // Increment the score
                break;
            }
        }

        // Disable all choice buttons to prevent further selection
        trueButton.setDisable(true);
        falseButton.setDisable(true);

        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(e -> {
            if (currentQuestionIndex >= questions.size()) {
                // All questions have been answered
                int totalQuestions = questions.size();
                double percentageScore = (double) score / totalQuestions * 100;

                // Show score alert outside the animation block
                Platform.runLater(() -> showAlert("Quiz Completed", "Your score: " + score + "/" + totalQuestions + " (" + percentageScore + "%)"));

                // Reset the quiz
                currentQuestionIndex = 0;
                score = 0;
                showCurrentQuestion();
            } else {
                showCurrentQuestion();
            }

            // Enable the choice buttons again
            trueButton.setDisable(false);
            falseButton.setDisable(false);
        });

        pause.play();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        serviceQuestion = new serviceQuestion();
        questions = serviceQuestion.RecupererQuestion();
        currentQuestionIndex = 0;
        score = 0; // Initialize the score
        showCurrentQuestion();
    }

    private void showCurrentQuestion() {
        Question currentQuestion = questions.get(currentQuestionIndex);
        questionLabel.setText(currentQuestion.getDescription());
        List<Choix> choices = serviceQuestion.getChoicesForQuestion(currentQuestion.getId());
        // Update UI with choices
    }

    public void goToNextQuestion() {
        currentQuestionIndex++;
        if (currentQuestionIndex >= questions.size()) {
            // All questions have been answered
            int totalQuestions = questions.size();
            double percentageScore = (double) score / totalQuestions * 100;

            // Show score alert
            showAlert("Quiz Completed", "Your score: " + score + "/" + totalQuestions + " (" + percentageScore + "%)");

            // Reset the quiz
            currentQuestionIndex = 0;
            score = 0;
            showCurrentQuestion();
            return; // Exit the method
        }
        showCurrentQuestion();
    }

    @FXML
    public void goToPreviousQuestion() {
        currentQuestionIndex--;
        if (currentQuestionIndex < 0) {
            currentQuestionIndex = questions.size() - 1; // Wrap around to the last question
        }
        showCurrentQuestion();
    }
}