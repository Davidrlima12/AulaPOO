package bankrupt;

public class JogadorCauteloso extends Jogador {
    public JogadorCauteloso() {
        super("Cauteloso");
    }

    @Override
    public void decidirCompra(Propriedade propriedade) {
        if (saldo >= propriedade.getPrecoVenda() + 80 && !propriedade.temDono()) {
            saldo -= propriedade.getPrecoVenda();
            propriedade.setDono(this);
        }
    }
}
