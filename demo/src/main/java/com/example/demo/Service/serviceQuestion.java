package com.example.demo.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.Util.MyConnection;
import com.example.demo.entity.Choix;
import com.example.demo.entity.Question;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ASUS
 */
public class serviceQuestion   implements IserviceQuestion<Question>{
    Connection cnxfl;

    public serviceQuestion() {
        cnxfl= MyConnection.getInstance().getCnx();
    }

    @Override
    public void AjouterQuestion(Question l) {
        try {
            String req = "insert into Question( id,  description)"
                    +"values('"+l.getId()+"','"+l.getDescription()
                    +"')";
            Statement st = cnxfl.createStatement();
            st.executeUpdate(req);
            System.out.println("Voiture ajouter avec succ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }


    }

    @Override
    public void modifierQuestion(Question l, int id,  String description) {
        try {
            String req = " UPDATE Question SET " + "id=?,  description = ?  where id=" + l.getId();
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
            pst.setInt(1, id);
            pst.setString(2,description);



            pst.executeUpdate();
            System.out.println("la question   modifié !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    @Override
    public void supprimerQuestion(Question l) {

        try {
            String req = "DELETE FROM question WHERE id=" + l.getId();
            Statement st = MyConnection.getInstance().getCnx().createStatement();

            st.executeUpdate(req);
            System.out.println(" la question esr   supprimé !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<Question> RecupererQuestion() {
        ObservableList<Question> myList = FXCollections.observableArrayList();
        try {

            String requete2 = "Select id,description  FROM Question";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete2);
            while (rs.next()) {
                Question rec = new Question();
                rec.setId(rs.getInt("id"));

                rec.setDescription(rs.getString("description"));


                myList.add(rec);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return myList;
    }

    @Override
    public List<Choix> getChoicesForQuestion(int Id) {
        List<Choix> choices = new ArrayList<>();
        try {

            String query = "SELECT * FROM choix WHERE id = " + Id;
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                String description = rs.getString("description");
                String reponse_q = rs.getString("reponse_q");
                Choix choix = new Choix(id, description, reponse_q);
                choices.add(choix);
            }

        }catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return choices;
    }

    @Override
    public boolean checkUserChoice(Choix choix, String userSelection) {
        return choix.getReponse_q().equalsIgnoreCase(userSelection);
    }

    public boolean isDuplicateQuestion(Question question) {
        Question[] questionList = new Question[0]; // Initialize an empty array

        // Add code to populate the questionList with actual questions

        for (Question q : questionList) {
            if (q.getId() == question.getId() && q.getDescription().equals(question.getDescription())) {
                return true; // Found a duplicate question
            }
        }
        return false; // No duplicate question found
    }



}





