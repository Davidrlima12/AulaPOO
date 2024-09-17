public class B extends A {
  protected int b;
  
  B () {
    
  }

  B (int b) {
    super(2);
    this.b = b;
  }

  public void b2 () {
     System.out.println("Oi, sou o b2 do B");
  }
}