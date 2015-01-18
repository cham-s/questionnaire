public class Questionnaire{

  /*
   * Modules 
   */

  // Cette methode permet de remplir les questionnaire et définir les réponses
  public static void remplirQuestionnaire( String questions[], String choix[][], char reponses_prof[]) {
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
        
    remplirQuestionnaire(questions, propositions, reponses_prof);
    afficherQuestionnaire(questions, propositions);
    soumettreQuestionnaire(questions, propositions, reponses_eleve, reponses_prof);
  }
} 
