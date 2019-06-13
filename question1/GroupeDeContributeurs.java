package question1;

import java.util.Iterator;
import java.util.NoSuchElementException;

import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

public class GroupeDeContributeurs extends Cotisant implements Iterable<Cotisant>{
  private List<Cotisant> liste;
  
  public GroupeDeContributeurs(String nomDuGroupe){
    super(nomDuGroupe);
    // a completer
      this.liste = new ArrayList<Cotisant>(); 
  }
  
  public void ajouter(Cotisant cotisant){
    // a completer
    this.liste.add(cotisant);                             
        cotisant.setParent(this); 
  }
  
  
  public int nombreDeCotisants(){
    int nombre = 0;
    // a completer
     Iterator<Cotisant> itc = liste.iterator();
     while(itc.hasNext()){     
       Cotisant c = itc.next();
       if(c instanceof Contributeur){
           nombre ++;
        }else{
            nombre += c.nombreDeCotisants(); 
     
        }
         
         
    }    
    return nombre;
  }
  
  public String toString(){
    String str = new String();
    // a completer
    for(Cotisant c: this.liste){
        str += c.toString()  + " \n";
    }
    return str;
  }
  
  public List<Cotisant> getChildren(){
    return this.liste;
  }
  
  public void debit(int somme) throws SoldeDebiteurException{
    // a completer
    if(somme <0){
        
     throw new RuntimeException("nombre negatif!!!!");
     
    }else{
            for(Cotisant c: this.liste){
                try{                             
                    c.debit(somme); 
                }catch( SoldeDebiteurException e){ 
                    throw new SoldeDebiteurException(); 
                } 
            } 
        } 
  }
  
  public void credit(int somme){
    // a completer
      if(somme <  0){ 
            throw new RuntimeException("nombre negatif !!!"); 
        } 
        else{ 
            for(Cotisant c: this.liste){                             
                c.credit(somme); 
            } 
        } 
  }
  
  public int solde(){
    int solde = 0;
    // a completer
     for(Cotisant c: this.liste){                            
            solde += c.solde(); 
        } 
        
        return solde;
  }
  
  // méthodes fournies
  
 public Iterator<Cotisant> iterator(){
    return new GroupeIterator(liste.iterator());
  }
  
  private class GroupeIterator implements Iterator<Cotisant>{
    private Stack<Iterator<Cotisant>> stk;
    
    public GroupeIterator(Iterator<Cotisant> iterator){
      this.stk = new Stack<Iterator<Cotisant>>();
      this.stk.push(iterator);
    }
    
    public boolean hasNext(){
      if(stk.empty()){
        return false;
      }else{
         Iterator<Cotisant> iterator = stk.peek();
         if( !iterator.hasNext()){
           stk.pop();
           return hasNext();
         }else{
           return true;
         }
      }
    }
    
    public Cotisant next(){
      if(hasNext()){
        Iterator<Cotisant> iterator = stk.peek();
        Cotisant cpt = iterator.next();
        if(cpt instanceof GroupeDeContributeurs){
          GroupeDeContributeurs gr = (GroupeDeContributeurs)cpt;
          stk.push(gr.liste.iterator());
        }
        return cpt;
      }else{
        throw new NoSuchElementException();
      }
    }
    public void remove(){
      throw new UnsupportedOperationException();
    }
  }
  

  public <T> T accepter(Visiteur<T> visiteur){
    return visiteur.visite(this);
  }
  

}
