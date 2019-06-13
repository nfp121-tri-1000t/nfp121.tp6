package question2;

import question1.Contributeur;
import question1.GroupeDeContributeurs;
import question1.Visiteur;
import question1.Cotisant;

public class CompositeValide implements Visiteur<Boolean>{
  // Le solde de chaque contributeur doit �tre sup�rieur ou �gal � 0 
  // et il n�existe pas de groupe n�ayant pas de contributeurs.
  
  public Boolean visite(Contributeur c){
    return c.solde()>= 0; 
  }
  
  public Boolean visite(GroupeDeContributeurs g){
      if(g.nombreDeCotisants()<=0){ 
            return false; 
        } 
        for(Cotisant c : g.getChildren()){ 
            if( !c.accepter(this)){ 
                return false; 
            } 
        } return true ; 
  }
}