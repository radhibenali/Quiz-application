package com.example.demo.Service;
import java.util.List;

/**
 *
 * @author ASUS
 * @param <L>
 */
public interface IserviceChoix<L> {
    public void AjouterChoix(L l );
    public void modifierChoix(L l,int id, String description , String reponse_q );
    public void supprimerChoix(L l);
    public List<L>RecupererChoix();

}
