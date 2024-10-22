import java.util.Scanner;

public abstract class InstrumentoFinanceiro extends Banco {
  private float saldo;

  public void setSaldo(float saldo) {
    this.saldo = saldo;
  }

  public float getSaldo() {
    return this.saldo;
  }

  public abstract float calcularSaldoTotal() {
    Scanner scan = new Scanner(System.in);
    
    System.out.println("Acao - 1 \nContaCorrente - 2 \nFundoDeAplicacao - 3");
    int escolha = scan.nextInt();
    Switch (escolha) {
      case 1:
        return "Acao";
      case 2:
        return "ContaCorrente";
      case 3:
        return "FundoDeAplicacao";
    }
  }
}