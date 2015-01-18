public class Questionnaire{

  /*
   * Modules 
   */

  // Cette methode permet de remplir les questionnaire et définir les réponses
  public static void remplirQuestionnaire( String questions[], String choix[][]){
    int i = 0;
    String input1 = " ";
    String input2 = " ";

    // while the user wants to add a question 
    do {
      Terminal.ecrireStringln("Entre la question " + (i + 1) + ": rien si pas de ques");
      questions[i] = Terminal.lireString(); 
      input1 = questions[i];
      int j = 0;

      do {
        Terminal.ecrireStringln("   Entre la réponse " + (j + 1) + ": rien si pas de rep");
        choix[i][j] = Terminal.lireString(); 
        input2 = choix[i][j];
        j++;
      }while(!"".equals(input2));

      i++;
    } while( !"".equals(input1));
  
  }
  
  // Affiche un questionnaire precedemment rempli
  public static void afficherQuestionnaire(String questions[], String choix[][]) {
    int i = 0;
    // loop the questionnaire
    while (!"".equals(questions[i])) {
      Terminal.ecrireStringln((i + 1) + ". " + questions[i]);
      int j = 0;

      //proposition a b c d... 
      char prop = 'a';
       
      while (!"".equals(choix[i][j])) {
        Terminal.ecrireStringln("   " + prop  + ". " + choix[i][j]);
        j++;
        prop++;
      }
      i++;
      Terminal.sautDeLigne();
    } 
  }


  public static void main (String args[]) {
    String ques[] = new String[100];
    String rep[][] = new String[100][50];
        
    remplirQuestionnaire(ques, rep);
    afficherQuestionnaire(ques, rep);
  }
} 
