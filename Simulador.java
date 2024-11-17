package bankrupt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Simulador {
    private List<Jogador> jogadores;
    private Tabuleiro tabuleiro;
    private final Map<String, Integer> vitoriasPorComportamento = new HashMap<>();


    public Simulador() {
        this.tabuleiro = new Tabuleiro("gameConfig.txt");
    }

    public void executarSimulacoes(int numeroDeSimulacoes) {
        int partidasTimeout = 0;
        int totalTurnos = 0;

        for (int i = 0; i < numeroDeSimulacoes; i++) {
            inicializarJogadores();
            int turnos = executarPartida();
            totalTurnos += turnos;

            if (turnos >= 1000) {
                partidasTimeout++;
            }
        }

        exibirResultados(numeroDeSimulacoes, partidasTimeout, totalTurnos);
    }

    private void inicializarJogadores() {
        jogadores = new ArrayList<>();
        jogadores.add(new JogadorImpulsivo());
        jogadores.add(new JogadorExigente());
        jogadores.add(new JogadorCauteloso());
        jogadores.add(new JogadorAleatorio());

        for (Jogador jogador : jogadores) {
            vitoriasPorComportamento.putIfAbsent(jogador.comportamento, 0);
        }
    }

    private boolean verificaFimDoJogo() {
        int jogadoresComSaldo = 0;
        for (Jogador jogador : jogadores) {
            if (jogador.getSaldo() > 0) {
                jogadoresComSaldo++;
            }
        }
        return jogadoresComSaldo <= 1;
    }

    private int executarPartida() {
        Dado dado = new Dado();
        int turnos = 0;

        while (turnos < 1000 && !verificaFimDoJogo()) {
            for (Jogador jogador : new ArrayList<>(jogadores)) {
                if (jogador.getSaldo() > 0) {
                    jogador.mover(dado, tabuleiro);

                    Propriedade propriedade = tabuleiro.getPropriedade(jogador.posicao);

                    if (propriedade.temDono()) {
                        if (propriedade.getDono() != jogador) {
                            jogador.pagar(propriedade.getValorAluguel());
                            propriedade.getDono().receber(propriedade.getValorAluguel());
                            if (jogador.getSaldo() < 0) {
                                tabuleiro.liberarPropriedades(jogador);
                                jogadores.remove(jogador);
                            }
                        }
                    } else {
                        jogador.decidirCompra(propriedade);
                    }
                }
            }
            turnos++;
        }

        for (Jogador jogador : jogadores) {
            if (jogador.getSaldo() > 0) {
                vitoriasPorComportamento.put(jogador.comportamento,
                    vitoriasPorComportamento.get(jogador.comportamento) + 1);
                break;
            }
        }

        return turnos;
    }


    private void exibirResultados(int numeroDeSimulacoes, int partidasTimeout, int totalTurnos) {
        System.out.println("Partidas que terminaram por timeout: " + partidasTimeout);
        System.out.println("Média de turnos por partida: " + (totalTurnos / (double) numeroDeSimulacoes));

        System.out.println("Porcentagem de vitórias por comportamento:");
        String comportamentoVencedor = null;
        int maiorVitorias = 0;

        for (Map.Entry<String, Integer> entrada : vitoriasPorComportamento.entrySet()) {
            String comportamento = entrada.getKey();
            int vitorias = entrada.getValue();
            double porcentagem = (vitorias / (double) numeroDeSimulacoes) * 100;

            System.out.printf("- %s: %.2f%%%n", comportamento, porcentagem);

            if (vitorias > maiorVitorias) {
                maiorVitorias = vitorias;
                comportamentoVencedor = comportamento;
            }
        }

        System.out.println("Comportamento que mais vence: " + comportamentoVencedor);
    }
}
