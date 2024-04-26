package com.example.demo.Service;
import  java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.example.demo.Util.MyConnection;
import com.example.demo.entity.Choix;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


    public class serviceChoix   implements IserviceChoix<Choix> {
        Connection cnxfl;
        public serviceChoix() {
            cnxfl= MyConnection.getInstance().getCnx();
        }
        @Override
        public void AjouterChoix(Choix choix) {
            try {
            String req = "insert into Choix( id, description , reponse_q)"
                    +"values('"+choix.getId()+"','"+choix.getDescription()+"','"+choix.getReponse_q()
                    +"')";
            Statement st = cnxfl.createStatement();
            st.executeUpdate(req);
            System.out.println("Choix ajouter avec succ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }


    }

        @Override
        public void modifierChoix(Choix choix, int id, String description, String reponse_q) {
            try {
                String req = " UPDATE Choix SET " + "id=?,  description = ? , reponse_q = ? where id=" + choix.getId();
                PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
                pst.setInt(1, id);
                pst.setString(2,description);
                pst.setString(3,reponse_q);



                pst.executeUpdate();
                System.out.println("le choix   modifié !");

            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }


        }

        @Override
        public void supprimerChoix(Choix choix) {
            try {
                String req = "DELETE FROM choix WHERE id=" + choix.getId();
                Statement st = MyConnection.getInstance().getCnx().createStatement();

                st.executeUpdate(req);
                System.out.println(" le choix est   supprimé !");

            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }

        }

        @Override
        public List<Choix> RecupererChoix() {
            ObservableList<Choix> myList = FXCollections.observableArrayList();
            try {

                String requete2 = "Select id,description,reponse_q  FROM Choix";
                Statement st = MyConnection.getInstance().getCnx().createStatement();
                ResultSet rs = st.executeQuery(requete2);
                while (rs.next()) {
                    Choix rec = new Choix();
                    rec.setId(rs.getInt("id"));

                    rec.setDescription(rs.getString("description"));
                    rec.setReponse_q(rs.getString("reponse_q"));


                    myList.add(rec);

                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());

            }
            return myList;
        }

        public void modifierChoix(Choix r, int var4, String var2) {
        }
    }


