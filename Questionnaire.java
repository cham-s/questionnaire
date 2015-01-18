/*
 * Projet cnam 2015 NFA031
 */
public class Questionnaire{

  /*
   * Methodes
   */

  // Cette methode permet de creer les questionnaire et définir les réponses
  public static void creerQuestionnaire( String questions[], String choix[][], char reponses_prof[]) {
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
        Terminal.ecrireString("   Entre la proposition " + prop + " (tapez 'Entrer' si plus de proposition): ");
        choix[i][j] = Terminal.lireString(); 
        input2 = choix[i][j];
        j++;
        prop++;

      }while(!"".equals(input2));
      Terminal.ecrireString("   Entre la reponse " + (i + 1) + " : ");
      reponses_prof[i] = Terminal.lireChar();
      i++;
      
    } while( !"".equals(input1));
  
  }
  
  // Affiche un questionnaire precedemment rempli
  public static void afficherQuestionnaire(String questions[], String choix[][]) {
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

      if (reponses_eleve[i] == reponses_prof[i]) {
        points++;
      }

      i++;
      totalQuestion++;
      Terminal.sautDeLigne();
    }

    moyenne_qcm = (double) totalQuestion / 2.0; 
    moyenne_eleve = (double) points / (double) totalQuestion;
    if (moyenne_eleve >= moyenne_qcm) {
      Terminal.ecrireStringln("Vous avez reussi le test avec " + points + ". :)");
    } else {
      Terminal.ecrireStringln("Vous n'avez pas reussi le test avec " + points + ". :(");
    }
  }


  public static void main (String args[]) {
    // Nombre max de propositions correspondant au lettre de l'alphabet
    int PROP_MAX = 26;
    // Nombre max de questions admis
    int QUEST_MAX = 100;
    String questions[] = new String[QUEST_MAX];
    String propositions[][] = new String[QUEST_MAX][PROP_MAX];
    char reponses_prof[] = new char[QUEST_MAX]; 
    char reponses_eleve[] = new char[QUEST_MAX];
    
    // petit interface utilisateur pour ameliorer l'ergonomie
    int choix = 0;
    while (choix != 4) {
      Terminal.ecrireString("|  ************************** Questionnaire 2015 **************************  |\n" +
                              "\t\t\tQue voulez - vous faire?\n\n" +
                             "1. Creer un questionnaire\n" +
                             "2. Afficher le questionnaire\n" +
                             "3. Soumettre le questionnaire\n" +
                             "4. Quitter\n> ");
      choix = Terminal.lireInt();

      if (choix == 1) {
        creerQuestionnaire(questions, propositions, reponses_prof);
      }

      else if (choix == 2) {
        afficherQuestionnaire(questions, propositions);
      }

      else if (choix == 3) {
        soumettreQuestionnaire(questions, propositions, reponses_eleve, reponses_prof);
      }

    }

    Terminal.ecrireStringln("A une prochaine fois...");
  }
} 
