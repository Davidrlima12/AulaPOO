import java.util.ArrayList;
import java.util.Date;

public class PrePago extends Assinante {
  private float creditos;
  private int numRecargas;
  private ArrayList<Chamada> chamadas; // Lista de chamadas feitas

  public PrePago(String cpf, String nome, long numero, float creditos) {
    super(cpf, nome, numero);
    this.creditos = creditos;
    this.chamadas = new ArrayList<Chamada>(); // Inicializa a lista de chamadas
  }

  public void recarregar(Date data, float valor) {
    this.creditos += valor;
    this.numRecargas++;
  }

  public void fazerChamada(Date data, int duracao) {
    float custo = duracao * 0.05f;
    if (creditos >= custo) { // Verifica se há créditos suficientes
      this.creditos -= custo;
      Chamada chamada = new Chamada(data, duracao);
      chamadas.add(chamada); // Adiciona a chamada à lista
    } else {
      System.out.println("Créditos insuficientes para realizar a chamada.");
    }
  }

  public void imprimirFatura(int mes) {
    System.out.println("Fatura do mês " + mes);
    System.out.println(super.toString());
    System.out.println("Créditos: " + this.creditos);
    for (Chamada chamada : chamadas) {
      System.out.println(chamada.toString());
    }
  }
}
