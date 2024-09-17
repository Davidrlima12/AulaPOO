public class C extends B {
  protected int c;
  
  C () {

  }

  C (int c) {
    super(2);
    this.c = c;
  }
  
  public void c1 () {
     System.out.println("Oi, sou o c1 do C");
  }
}
