/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.allouche.infoprojet;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Portable
 */
public class Identificateur {
    private int id;
    private Map<Integer,Object> idVersObjet;
    private Map<Object,Integer> ObjetVersId;
    
    public Identificateur  (int id){
        this.id = id;
        this.idVersObjet = new HashMap<>();
        this.ObjetVersId = new HashMap<>();
    }
    
    public Identificateur  (){
        this(0);
    }

    public int getOrCreateId (Object objet){
        Integer id = this.ObjetVersId.get(objet) ;
        if ( id != null) {
            return id;
        } else {
          this.ObjetVersId.put(objet, this.id);
          this.idVersObjet.put(this.id, objet);
          this.id ++;
          return this.id -1;
        }
    }
    
    public boolean objetPresent (Object objet){
      return this.ObjetVersId.get(objet) != null;
    }
}
