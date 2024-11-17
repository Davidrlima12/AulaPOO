package bankrupt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {
    private List<Propriedade> propriedades;

    public Tabuleiro(String caminhoArquivo) {
        propriedades = new ArrayList<>();
        carregarPropriedades(caminhoArquivo);
    }

    public int getNumeroDePropriedades() {
        return propriedades.size();
    }

    private void carregarPropriedades(String caminhoArquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;

                String[] partes = linha.split(" ");
                if (partes.length == 2) {
                    try {
                        int valorVenda = Integer.parseInt(partes[0]);
                        int valorAluguel = Integer.parseInt(partes[1]);
                        propriedades.add(new Propriedade(valorVenda, valorAluguel));
                    } catch (NumberFormatException e) {
                        System.err.println("Erro ao converter valores na linha: " + linha);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo de configuração: " + e.getMessage());
        }
    }

    public Propriedade getPropriedade(int posicao) {
        return propriedades.get(posicao % propriedades.size());
    }

    public void liberarPropriedades(Jogador jogador) {
        for (Propriedade propriedade : propriedades) {
            if (propriedade.getDono() == jogador) {
                propriedade.setDono(null);
            }
        }
    }
}
