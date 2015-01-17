import java.io.*;
public class Terminal{    
    static BufferedReader in =
	new BufferedReader(new InputStreamReader(System.in));
    public static String lireString() // Lire un String
	{
	    String tmp="";
	    char C='\0';
	    try {
		tmp = in.readLine();
	    }
	    catch (IOException e)
	    {
		exceptionHandler(e);
	    }
	    return tmp;
	} // fin de lireString()
    public static int lireInt()  // Lire un entier
	{
	    int x=0;
	    try {
		x=Integer.parseInt(lireString());
	    }
	    catch (NumberFormatException e) {
		exceptionHandler(e);
	    }	
	    return x ;
	}
    public static boolean lireBoolean()  // Lire un entier
	{
	    boolean b = true;
	    try {
		b = Boolean.valueOf(lireString()).booleanValue();
	    }
	    catch (NumberFormatException e) {
		exceptionHandler(e);
	    }	
	    return b;
	}
    public  static double lireDouble()  // Lire un double
	{
	    double x=0.0;
	    try {
		x=Double.valueOf(lireString()).doubleValue();
	    }
	    catch (NumberFormatException e) {
		exceptionHandler(e);
	    }	
	    return x ;
	}
    public  static char lireChar()  // Lire un caractere
	{
	    String tmp=lireString();
	    if (tmp.length()==0)
		return '\n';
	    else 
	    {
		return tmp.charAt(0);
	    }
	}
    public static void ecrireString(String s){ // Afficher un String
	try{
	    System.out.print(s);
	} catch (Exception ex){
	    exceptionHandler(ex);
	}
    }
    public static void ecrireStringln(String s) // Afficher un String
	{
	    ecrireString(s);
	    sautDeLigne();
	} // fin de ecrireStringln()
    public static void ecrireInt(int i)  // Afficher un entier
	{
	    ecrireString(""+i);
	}
    public static void ecrireIntln(int i)  // Afficher un entier
	{
	    ecrireString(""+i);
	    sautDeLigne();
	}
    public static void ecrireBoolean(boolean b){
	ecrireString(""+b);
    }
    public static void ecrireBooleanln(boolean b){
	ecrireString(""+b);
	sautDeLigne();
    }
    public  static void ecrireDouble(double d)  // Afficher un double
	{
	    ecrireString(""+d);
	}
    public  static void ecrireDoubleln(double d)  // Afficher un double
	{
	    ecrireDouble(d);
	    sautDeLigne();
	}
    public  static void ecrireChar(char c)  // Afficher un caractere
	{
	    ecrireString(""+c);
	}  
    public  static void ecrireCharln(char c)  // Afficher un caractere
	{
	    ecrireChar(c);
	    sautDeLigne();
	}
    public static void sautDeLigne(){
	try{
	    System.out.println();
	}catch(Exception ex){
	    exceptionHandler(ex);
	}
    }
    protected static void exceptionHandler(Exception ex){
	TerminalException err = new TerminalException(ex);
	throw err;
    }
    public static void ecrireException(Throwable ex){
	ecrireString(ex.toString());
	ex.printStackTrace(System.err);
    }
}  
class TerminalException extends RuntimeException{
    Exception ex;
    TerminalException(Exception e){
	ex = e;
    }
}


