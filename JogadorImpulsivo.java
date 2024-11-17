package bankrupt;

public class JogadorImpulsivo extends Jogador {
    public JogadorImpulsivo() {
        super("Impulsivo");
    }

    @Override
    public void decidirCompra(Propriedade propriedade) {
        if (saldo >= propriedade.getPrecoVenda() && !propriedade.temDono()) {
            saldo -= propriedade.getPrecoVenda();
            propriedade.setDono(this);
        }
    }
}
