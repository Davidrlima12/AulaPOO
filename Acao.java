public class Acao extends InstrumentoFinanceiro {
  private int cotas;

  public void setCotas(int cotas) {
    this.cotas = cotas;
  }

  public float calcularSaldoTotal() {
    return (cotas * super.getSaldo());
  }
}