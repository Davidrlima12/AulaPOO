public class ContaCorrente extends InstrumentoFinanceiro {
  private float limite;

  public void setLimite(float limite) {
    this.limite = limite;
  }

  public float calcularSaldoTotal() {
    return (super.getSaldo() + this.limite);
  }
}