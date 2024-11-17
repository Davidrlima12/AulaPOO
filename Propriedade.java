package bankrupt;

public class Propriedade {
    private int precoVenda;
    private int valorAluguel;
    private Jogador dono;

    public Propriedade(int precoVenda, int valorAluguel) {
        this.precoVenda = precoVenda;
        this.valorAluguel = valorAluguel;
        this.dono = null;
    }

    public int getPrecoVenda() {
        return precoVenda;
    }

    public int getValorAluguel() {
        return valorAluguel;
    }

    public Jogador getDono() {
        return dono;
    }

    public void setDono(Jogador dono) {
        this.dono = dono;
    }

    public boolean temDono() {
        return dono != null;
    }
}
