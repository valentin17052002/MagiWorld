package com.example.magiworld;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.example.magiworld.Personnage.Guerrier;
import com.example.magiworld.Personnage.Mage;
import com.example.magiworld.Personnage.Personnage;
import com.example.magiworld.Personnage.Rodeur;




public class Game  {
    private Personnage player1;
    private Personnage player2;
    private Scanner sc = new Scanner(System.in);
    int classe, niveau, force, agilite, intelligence;

    /**
     * Game start here
     * With the creation of both player1 & player2
     */
    public Game (){
      player1 = createPlayer(1);
      player2 = createPlayer(2);
      Start();
    }
    // ---- Personnage création ----
    /**
     * Method used for ask the parameter of the actual concerned personnage
     * Use a scanner for ask the value
     */
      private Personnage createPlayer(int playerNumber){
      System.out.println("Création du personnage du Joueur " + playerNumber); 


      do {
        classe = 0;
        System.out.println("Veuillez choisir la classe du personnage (1 : Guerrier, 2 : Rôdeur, 3 : Mage");
        try {
            classe = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Veuillez saisir un nombre !");
            sc.next();
        }
    } while (!(classe == 1 || classe == 2 || classe == 3));





      do {
        System.out.println("Niveau du personnage ?");
        niveau = sc.nextInt();
        System.out.println("Force du personnage ?");
        force = sc.nextInt();
        System.out.println("Agilité du personnage ?");
        agilite = sc.nextInt();
        System.out.println("Intelligence du personnage ?");
        intelligence = sc.nextInt();

        
        if ((force + agilite + intelligence) > niveau)
          System.out.println("un Personnage niveau " + niveau + " ne peut pas avoir " + force + " de force" +
                  " + " + agilite + " d'agilité + " + intelligence + " d’intelligence : le total doit faire " + niveau);

      }while ((force + agilite + intelligence) > niveau);




      return instanciationPersonnage(classe, niveau, force, agilite, intelligence, playerNumber);
    }







    /**
     * Method used for instanciate the personnage with the good value
     */
    private Personnage instanciationPersonnage(int classe, int niveau, int force, int agilite, int intelligence, int playerNumber) {
      Personnage temp = null;
      switch (classe) {
        case 1:
          temp = new Guerrier(niveau, force, agilite, intelligence, playerNumber);
          break;
        case 2:
          temp = new Rodeur(niveau, force, agilite, intelligence, playerNumber);
          break;
        case 3:
          temp = new Mage(niveau, force, agilite, intelligence, playerNumber);
          break;
      }
      System.out.println(temp);
      return temp;
    }
    // ---- Game turn ----
    /**
     * Method for start the fight
     * We use a modulo for the pair and impair turn
     */
    private void Start(){
      int turn = 1;
      while (player1.getVie() > 0 && player2.getVie() > 0){
        if(turn % 2 == 1){
          Turn(player1, player2);
        }else{
          Turn(player2, player1);
          }
          turn++;
        }
      if (player1.getVie() <= 0){
        System.out.println("Joueur 1 à perdu !");
      }else{
        System.out.println("Joueur 2 à perdu !");
      }
      }
    /**
     * Method used for a turn of the game
     * @param attack the personnage who attack
     * @param defend the personnage who defend
     */
    private void Turn(Personnage attack, Personnage defend){
        System.out.println("Joueur " + attack.getPlayerNumber() + " (" + attack.getVie() + " de vitalité) veuillez choisir " +
                "votre action (1 : Attaque Basique, 2 : Attaque Spéciale)");
        int choix = sc.nextInt();
        if (choix == 1)
          attack.attackBase(defend);
        else
          attack.attackSpecial(defend);
    }

}



