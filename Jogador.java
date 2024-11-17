package bankrupt;

public abstract class Jogador {
    protected int saldo;
    protected int posicao;
    protected String comportamento;

    public Jogador(String comportamento) {
        this.saldo = 300;
        this.posicao = 0;
        this.comportamento = comportamento;
    }

    public int getSaldo() {
        return saldo;
    }

    public void mover(Dado dado, Tabuleiro tabuleiro) {
        int passos = dado.rolar();
        posicao = (posicao + passos) % tabuleiro.getNumeroDePropriedades();
    }

    public abstract void decidirCompra(Propriedade propriedade);

    public void pagar(int valor) {
        saldo -= valor;
    }

    public void receber(int valor) {
        saldo += valor;
    }
}
