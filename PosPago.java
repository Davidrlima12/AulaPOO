import java.util.ArrayList;
import java.util.Date;

public class PosPago extends Assinante {
  private float assinatura;
  private ArrayList<Chamada> chamadas; // Lista de chamadas feitas

  public PosPago(String cpf, String nome, long numero, float assinatura) {
    super(cpf, nome, numero);
    this.assinatura = assinatura;
    this.chamadas = new ArrayList<Chamada>(); // Inicializa a lista de chamadas
  }

  public void fazerChamada(Date data, int duracao) {
    float custo = duracao * 0.05f;
    this.assinatura -= custo;
    Chamada chamada = new Chamada(data, duracao);
    chamadas.add(chamada); // Adiciona a chamada à lista
  }

  public void imprimirFatura(int mes) {
    System.out.println("\n\nFatura do mês " + mes);
    System.out.println(super.toString());
    System.out.println("Assinatura: " + this.assinatura);
    for (Chamada chamada : chamadas) {
      System.out.println(chamada.toString());
    }
  }
}
