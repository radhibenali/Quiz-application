package com.example.demo.Service;
import com.example.demo.entity.Choix;

import java.util.List;

/**
 *
 * @author ASUS
 * @param <L>
 */
public interface IserviceQuestion<L> {
    public void AjouterQuestion(L l);
    public void modifierQuestion(L l,int id, String description);
    public void supprimerQuestion(L l);
    public List<L>RecupererQuestion();
    public List<Choix> getChoicesForQuestion(int Id);
    public boolean checkUserChoice(Choix choix, String userSelection);

}
