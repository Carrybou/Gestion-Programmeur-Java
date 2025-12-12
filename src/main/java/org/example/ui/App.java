package org.example.ui;

import org.example.dao.ActionsBDD;
import org.example.model.employe.Employe;

import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        ActionsBDD dbAction = new ActionsBDD();
        List<Employe> employes = dbAction.getAllEmployes();
        for(Employe employe: employes) {
            System.out.println(employe.getMetier());
        }
    }
}
