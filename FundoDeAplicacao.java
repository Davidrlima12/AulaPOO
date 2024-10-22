public class FundoDeAplicacao extends InstrumentoFinanceiro {
  private float rentabilidade;

  public void setRentabilidade(float rentabilidade) {
    this.rentabilidade = rentabilidade;
  }

  public float calcularSaldoTotal() {
    return (super.getSaldo() + this.rentabilidade);
  }
}