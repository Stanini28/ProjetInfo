 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.allouche.infoprojet;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Portable
 */
public class Identificateur<Object> {
    private int id;
    private Map<Integer,Object> idVersObjet;
    private Map<Object,Integer> ObjetVersId;
    
    public Identificateur  (int id){
        this.id = id;
        this.idVersObjet = new TreeMap<>();
        this.ObjetVersId = new HashMap<>();
    }
    
    public Identificateur  (){
        this(0);
    }

    public int getOrCreateId (Object objet){
        Integer id = this.ObjetVersId.get(objet) ;
        if ( id != null) {
            System.out.println("id != null");
            return id;
        } else {
          this.ObjetVersId.put(objet, this.id);
          this.idVersObjet.put(this.id, objet);
          this.id ++;
          return this.id -1;
        }
    }
    
     public boolean objExist(Object obj) {
        return this.ObjetVersId.containsKey(obj);
    }
    
    public int getID(Object obj) {
        if (this.objExist(obj)) {
            return this.ObjetVersId.get(obj);
        } else {
            throw new Error("Objet" + obj + " inconnu dans numéroteur");
        }
    }
    
    public boolean objetPresent (Object objet){
      return this.ObjetVersId.get(objet) != null;
    }
    
    public int getOuCreeID(Object obj) {
        if (this.objExist(obj)) {
            return this.ObjetVersId.get(obj);
        } else {
            return this.getOrCreateId(obj);
        }
    }
    
    public Object getObj(int id) {
        if (! this.idExist(id)) {
            throw new Error("identificateur non existant");
        }
        return this.idVersObjet.get(id);
    }
    public void setObj(int id, Object newobj) {
        if (! this.idExist(id)) {
            throw new Error("identificateur non existant");
        }
        this.idVersObjet.get(id);
    }
    
    public boolean idExist(int id) {
        return this.idVersObjet.containsKey(id);
    }
    
    public void associe(int id,Object obj) {
        if (this.idExist(id)) {
            throw new Error("identificateur existant");
        }
        this.idVersObjet.put(id, obj);
        this.ObjetVersId.put(obj, id);
    }

    public Map<Integer, Object> getIdVersObjet() {
        return idVersObjet;
    }

    public Map<Object, Integer> getObjetVersId() {
        return ObjetVersId;
    }
    
    
   
}
