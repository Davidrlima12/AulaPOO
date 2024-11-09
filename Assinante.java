public class Assinante {
  private String cpf;
  private String nome;
  private long numero;
  protected int numChamadas;

  public Assinante(String cpf, String nome, long numero) {
    this.cpf = cpf;
    this.nome = nome;
    this.numero = numero;
  }

  public String getCpf() {
    return cpf;
  }

  public String toString() {
    return "\nCpf: " + cpf + " \nNome: " + nome + " \nNumero: " + numero + "\n";
  }

  public String getPlanoTipo() {
    if (this instanceof PrePago) {
        return "PrePago";
    } else if (this instanceof PosPago) {
        return "PosPago";
    } else {
        return "Plano inválido";
    }
  }
}