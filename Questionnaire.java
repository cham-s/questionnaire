/*
 * Projet cnam 2015 NFA031 Questionnaire
 */
public class Questionnaire{
  
  public static void main (String args[]) {

    effacerEcran(3);
    // Demander le nombre de question max
    char option;
    Terminal.ecrireString("\t\t\tBienvenue dans le Questionnaire 2015\n\n" +
                             "Par defaut le nombre de question maximum est de 100.\n" +
                              "Le nombre de sous proposition maximum est de 26 (a-z)\n" +
                              "Souhaitez vous les modifier? o/n > ");
    
    option = Terminal.lireChar();
    while (option != 'n' && option != 'o') {
        Terminal.ecrireString("Entrez 'o' pour oui ou 'n' pour non > ");
        option = Terminal.lireChar();
    }

    if (option == 'o') {
      effacerEcran(1);
      Terminal.ecrireString("Nombre de questions max: ");
      QUEST_MAX = Terminal.lireInt(); 
      Terminal.ecrireString("Nombre de sous propositions max: ");
      PROP_MAX = Terminal.lireInt();

      Terminal.ecrireString("... Valeurs modifier avec succes.");
    } else {
      QUEST_MAX = 100; 
      PROP_MAX = 26; 
    }
    
    String questions[] = new String[QUEST_MAX];
    String propositions[][] = new String[QUEST_MAX][PROP_MAX];
    char reponses_prof[] = new char[QUEST_MAX]; 
    char reponses_eleve[] = new char[QUEST_MAX];
    
    // Petit interface utilisateur pour ameliorer l'ergonomie
    int choix = 0;
    
    while (choix != 4) {
      effacerEcran(10);
      
      Terminal.ecrireString("|  ************************** Questionnaire 2015 **************************  |\n\n" +
                              "\t\t\tQue voulez - vous faire?\n\n" +
                             "1. Creer votre questionnaire\n" +
                             "2. Afficher le questionnaire\n" +
                             "3. Soumettre le questionnaire\n" +
                             "4. Quitter\n> ");
      // Inserer choix
      choix = Terminal.lireInt();

      if (choix == 1) {
        creerQuestionnaire(questions, propositions, reponses_prof);
      }

      else if (choix == 2) {
        afficherQuestionnaire(questions, propositions);
      }

      else if (choix == 3) {
        effacerEcran(50);
        soumettreQuestionnaire(questions, propositions, reponses_eleve, reponses_prof);
      }

    }

    Terminal.ecrireStringln("A une prochaine fois...");
  }  


  
  /*
   * Globals
   */

  // Nombre max de propositions correspondant au lettre de l'alphabet par defaut
  static int PROP_MAX;
  // Nombre max de questions admises par defaut
  static int QUEST_MAX;

  /*
   * Methodes
   */
  
  /*
   * Espace a l'ecran
   */
  public static void effacerEcran(int nbreDeLigne) {
    for (int i = 0; i < nbreDeLigne; i++ )
      Terminal.ecrireChar('\n');   
  }

  /* 
  *  Creer le questionnaire et definir les reponses
  */
  public static void creerQuestionnaire( String questions[], String choix[][], char reponses_prof[]) {
    // Avant quoi que ce sout verifier si le tableau est vide
     if (questions[1] != null){
      Terminal.ecrireString("Vous etes sur le point de d'effacer un questionnare deja creer, \n" +
          "voulez-vous poursuivre? o/n: >");
      char option;
      option = Terminal.lireChar();

      while (option != 'n' && option != 'o') {
        Terminal.ecrireString("Entrez 'o' pour oui ou 'n' pour non > ");
        option = Terminal.lireChar();
      }
      
      if (option == 'o') {
        questions = new String[QUEST_MAX];
        choix = new String[QUEST_MAX][PROP_MAX];
        reponses_prof = new char [QUEST_MAX];
      }else {
        return; 
      }
    }
    
    effacerEcran(2);
    Terminal.ecrireString("\t\t\t1- Creattion de Questionnaire\n\n");

    int i = 0;
    String input1 = " ";
    String input2 = " ";

    // Tant qu'il que l'utilisateur souhaite ajouter une question
    do {
      Terminal.ecrireString("Entrez la question " + (i + 1) + " (tapez 'Entrer' si plus de question): ");
      questions[i] = Terminal.lireString(); 
      input1 = questions[i];
      int j = 0;
      
      // Choix de proposition
      char prop = 'a';

      // Tant que l'utilisateur souhaite ajouter une proposition
      do {
        Terminal.ecrireString("   Entrez la proposition " + prop + " (tapez 'Entrer' si plus de proposition): ");
        choix[i][j] = Terminal.lireString(); 
        input2 = choix[i][j];
        j++;
        prop++;

      }while (!"".equals(input2));

      // Inserer la bonne reponse
      Terminal.ecrireString("     Inserer la reponse a la question " + (i + 1) + " : ");
      reponses_prof[i] = Terminal.lireChar();
      i++;
      
    }while( !"".equals(input1));
  
  }


  /*
  *  Affiche le questionnaire precedemment rempli
  */
  public static void afficherQuestionnaire(String questions[], String choix[][]) {
    effacerEcran(2); 
    // Verifier si le questionnaire a afficher est vide
    if (questions[1] == null) {
      Terminal.ecrireString("Le Questionnaire est vide. Rien ne peut etre affiche, vous pouvez en creer un nouveau\n");
      return;
    }
    Terminal.ecrireString("\t\t\t2- Questionnaire\n\n");
    
    int i = 0;

    // Parcourir le questionnaire
    while (!"".equals(questions[i])) {
      Terminal.ecrireStringln((i + 1) + ". " + questions[i]);
      int j = 0;

      // Choix
      char prop = 'a';
      
      // Parcourir les differents choix
      while (!"".equals(choix[i][j])) {
        Terminal.ecrireStringln("   " + prop  + ". " + choix[i][j]);
        j++;
        prop++;
      }
      i++;
      Terminal.sautDeLigne();
    }
  }


  /*
   *  Soummet le questionnaire a l'eleve et affiche le resultat
   */
  public static void soumettreQuestionnaire(String questions[]
      , String choix[][], char reponses_eleve[], char reponses_prof[]) {
    effacerEcran(2);
    if (questions[1] == null) {
      Terminal.ecrireString("Le Questionnaire est vide. Rien ne peut etre affiche, vous pouvez en creer un nouveau\n");
      return;
    }
    Terminal.ecrireString("\t\t\t3- Le test comment. Bon courage!\n\n");
    
    int points = 0;
    double moyenne_qcm = 0.0;
    double moyenne_eleve = 0.0;
    int totalQuestion = 0;
    int i = 0;

    // Parcourir le questionnaire
    while (!"".equals(questions[i])) {
      Terminal.ecrireStringln((i + 1) + ". " + questions[i]);
      int j = 0;

      // Choix
      char prop = 'a';
      
      // Parcourir les differents choix
      while (!"".equals(choix[i][j])) {
        Terminal.ecrireStringln("   " + prop  + ". " + choix[i][j]);
        j++;
        prop++;
        
      }
      // Reponse de l'eleve
      Terminal.ecrireString("Votre reponse: ");
      reponses_eleve[i] = Terminal.lireChar();

      // Incrementer les points a chaque bonne reponse
      if (reponses_eleve[i] == reponses_prof[i]) {
        points++;
      }

      i++;
      totalQuestion++;
      Terminal.sautDeLigne();
    }
    
    double MOITIE = 2.0;
    moyenne_qcm = (double) totalQuestion / MOITIE; 
    moyenne_eleve = (double) points / MOITIE;

    // Verifier si le test est reussi ou pas et afficher le resultat
    if (moyenne_eleve >= moyenne_qcm) {
      Terminal.ecrireStringln("Felicitation vous avez reussi le test avec un resultat de " + points + " / " + totalQuestion +  ". ( ^ v ^ )");
    } else {
      Terminal.ecrireStringln("Malheuresement vous n'avez pas reussi le test avec un resultat de " + points + " / " + totalQuestion + ".: (");
    }
  }
} 
