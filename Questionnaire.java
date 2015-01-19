/*
 * Projet cnam 2015 NFA031
 */
public class Questionnaire{

  /*
   * Globals
   */

  // Nombre max de propositions correspondant au lettre de l'alphabet
  private static int PROP_MAX = 26;
  // Nombre max de questions admise
  private static int QUEST_MAX = 100;

  /*
   * Methodes
   */
  
  // Effacer ecran
  public static void effacerEcran(int nbreDeLigne) {
    for (int i = 0; i < nbreDeLigne; i++ )
      Terminal.ecrireChar('\n');   
  }

  // Cette methode permet de creer les questionnaire et definir les reponses
  public static void creerQuestionnaire( String questions[], String choix[][], char reponses_prof[]) {
    // Avant quoi que ce sout verifier si le tableau est vide
    if (questions[1] != null) {
      Terminal.ecrireString("Vous etes sur le point de d'effacer un questionnare deja creer, \n" +
          "voulez-vous poursuivre? o/n: >");
      char option;
      option = Terminal.lireChar();

      while (option != 'n' && option != 'o') {
        Terminal.ecrireString("Entrez 'o' pour oui ou 'n' pour non > ");
        option = Terminal.lireChar();
        Terminal.ecrireCharln(option);
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
    Terminal.ecrireString("\t\t1- Creattion de Questionnaire\n\n");

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
      Terminal.ecrireString("     Inserer reponse " + (i + 1) + " : ");
      reponses_prof[i] = Terminal.lireChar();
      i++;
      
    }while( !"".equals(input1));
  
  }
  
  // Affiche un questionnaire precedemment rempli
  public static void afficherQuestionnaire(String questions[], String choix[][]) {
    effacerEcran(2); 
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

  public static void soumettreQuestionnaire(String questions[]
      , String choix[][], char reponses_eleve[], char reponses_prof[]) {
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
      Terminal.ecrireStringln("Felicitation vous avez reussi le test avec " + points + " points. :)");
    } else {
      Terminal.ecrireStringln("Malheuresement vous n'avez pas reussi le test :(. points: " + points + ".");
    }
  }


  public static void main (String args[]) {
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
                             "1. Creer un questionnaire\n" +
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
} 
