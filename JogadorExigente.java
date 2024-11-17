package bankrupt;

public class JogadorExigente extends Jogador {
    public JogadorExigente() {
        super("Exigente");
    }

    @Override
    public void decidirCompra(Propriedade propriedade) {
        if (saldo >= propriedade.getPrecoVenda() && !propriedade.temDono() &&
            propriedade.getValorAluguel() > 50) {
            saldo -= propriedade.getPrecoVenda();
            propriedade.setDono(this);
        }
    }
}
