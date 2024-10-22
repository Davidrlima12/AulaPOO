public class Banco {
  private int numInstrumentos;
  private InstrumentoFinanceiro instrumentos[] = new InstrumentoFinanceiro[this.numInstrumentos];

  public void adicionar(InstrumentoFinanceiro instrumento) {
    this.numInstrumentos++;
  }

  public float calcularSaldos() {
    float saldos = 0;
    for (int i = 0; i < this.numInstrumentos; i++) {
      saldos += this.instrumentos[i].getSaldo();
      if (i == (this.numInstrumentos - 1)) {
        return saldos;
      }
    }
    return -1;
  }

  public void main(String[] args) {
    
  }
}