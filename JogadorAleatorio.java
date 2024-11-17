package bankrupt;

import java.util.Random;

public class JogadorAleatorio extends Jogador {
    private Random random = new Random();

    public JogadorAleatorio() {
        super("Aleatório");
    }

    @Override
    public void decidirCompra(Propriedade propriedade) {
        if (saldo >= propriedade.getPrecoVenda() && !propriedade.temDono() &&
            random.nextBoolean()) {
            saldo -= propriedade.getPrecoVenda();
            propriedade.setDono(this);
        }
    }
}
