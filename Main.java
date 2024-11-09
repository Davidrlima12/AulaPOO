import java.util.Scanner;

public class Main  {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    
    Telefonia telefonia = new Telefonia();

    telefonia.main(args);
    
    scan.close();
}
}