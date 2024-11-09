import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Telefonia {
  private int numPrePagos;
  private int numPosPagos;
  private ArrayList<Assinante> assinanteLista = new ArrayList<Assinante>();

  public Telefonia() {
    this.numPrePagos = 0;
    this.numPosPagos = 0;
  }

  public void cadastrarAssinante() {
    Scanner scan = new Scanner(System.in);

    System.out.print("\nInforme o nome do assinante: ");
    String nome = scan.nextLine();
    
    System.out.print("\nInforme o CPF do assinante: ");
    String cpf = scan.nextLine();
    
    System.out.print("\nInforme o número do assinante: ");
    long numero = scan.nextLong();

    scan.nextLine();

    System.out.print("\nEscolha seu tipo de plano: \n1 - PrePago \n2 - PosPago \n\n");
    int escolha = scan.nextInt();

    if (escolha == 1) {
      System.out.println("\nDigite o valor da recarga inicial: ");
      float recargaInicial = scan.nextFloat();
      PrePago assinante = new PrePago(cpf, nome, numero, recargaInicial);
      assinanteLista.add(assinante);
      numPrePagos++;
    }
    else if (escolha == 2) {
      System.out.println("\nDigite o valor da assinatura: ");
      float assinatura = scan.nextFloat();
      
      PosPago assinante = new PosPago(cpf, nome, numero, assinatura);
      assinanteLista.add(assinante);
      numPosPagos++;
    }
    
    System.out.println("\nAssinante cadastrado com sucesso!\n\n");
  }

  public void listarAssinantes() {
    for (Assinante assinante : assinanteLista) {
      System.out.println(assinante.toString());
    }
  }

  public void fazerChamada(Assinante usuario) {
      Scanner scan = new Scanner(System.in);
      
      try {
        usuario = localizarPrePago(usuario.getCpf());
      } catch (IllegalArgumentException e) {
        try {
            usuario = localizarPosPago(usuario.getCpf());
        } catch (IllegalArgumentException e2) {
            System.out.println("Assinante não encontrado!");
            return; 
        }
      }
      System.out.print("\nInforme a data da chamada (dd/MM/yyyy): ");
      String dataString = scan.nextLine();

      System.out.print("\nInforme a duração da chamada em minutos: ");
      int duracao = scan.nextInt();

      Date data = null;
      try {
        data = new SimpleDateFormat("dd/MM/yyyy").parse(dataString);
      } 
      catch (ParseException e) {
        System.out.println("Formato inválido. Use: dd/MM/yyyy.");

        if (usuario instanceof PrePago) {        
          ((PrePago) usuario).fazerChamada(data, duracao);      
        } else if (usuario instanceof PosPago) {
          ((PosPago) usuario).fazerChamada(data, duracao);
        }
      }
  }

  public void fazerRecarga(Assinante usuario) {
    Scanner scan = new Scanner(System.in);

    try {
      usuario = localizarPrePago(usuario.getCpf());
    } catch (IllegalArgumentException e) {
      try {
          usuario = localizarPosPago(usuario.getCpf());
      } catch (IllegalArgumentException e2) {
          System.out.println("Assinante não encontrado!");
          return; 
      }
    }
    System.out.print("\nInforme a data da recarga (dd/MM/yyyy): ");
    String dataString = scan.nextLine();

    System.out.print("\nInforme o valor da recarga: ");
    float recarga = scan.nextFloat();

    Date data = null;
    try {
      data = new SimpleDateFormat("dd/MM/yyyy").parse(dataString);
    } 
    catch (ParseException e) {
      System.out.println("Formato inválido. Use: dd/MM/yyyy.");

      if (usuario instanceof PrePago) {        
        ((PrePago) usuario).recarregar(data, recarga); 
      }
    }
  }

  public PrePago localizarPrePago(String cpf) {
    for (Assinante assinante : assinanteLista) {
        if (assinante instanceof PrePago && assinante.getCpf().equals(cpf)) {
            return (PrePago) assinante;
        }
    }
    return null; // Retorna null se não encontrar um assinante PrePago com o CPF informado
  }

  public PosPago localizarPosPago(String cpf) {
    for (Assinante assinante : assinanteLista) {
        if (assinante instanceof PosPago && assinante.getCpf().equals(cpf)) {
            return (PosPago) assinante;
        }
    }
    return null; // Retorna null se não encontrar um assinante PosPago com o CPF informado
  }

  public void imprimirFaturas() {
    for (Assinante assinante : assinanteLista) {
      for (int mes = 1; mes <= 12; mes++) {
          if (assinante instanceof PrePago) {
              ((PrePago) assinante).imprimirFatura(mes); 
          } else if (assinante instanceof PosPago) {
              ((PosPago) assinante).imprimirFatura(mes);
          }
        System.out.println("\n\n");
      }
    }
  }

  public void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    Telefonia telefonia = new Telefonia();
    
    telefonia.cadastrarAssinante();
    telefonia.cadastrarAssinante();
    telefonia.listarAssinantes();

    System.out.print("\nInforme o CPF para localizar o assinante e fazer operações: ");
      String cpf = scan.nextLine();

    // Localizar assinante e escolher operação
    Assinante usuario = telefonia.localizarPrePago(cpf);
    if (usuario == null) {
        usuario = telefonia.localizarPosPago(cpf);
    }
    boolean sair = false;
    
while (!sair) {
        if (usuario instanceof PrePago) {
            System.out.println("\nEscolha a operação:\n1 - Fazer Chamada\n2 - Fazer Recarga\n3 - Imprimir Fatura\n4 - Sair\n");
            int opcao = scan.nextInt();
            scan.nextLine();  // Consumir a nova linha

            switch (opcao) {
                case 1:
                    telefonia.fazerChamada(usuario);
                    break;
                case 2:
                    telefonia.fazerRecarga(usuario);
                    break;
                case 3:
                    ((PrePago) usuario).imprimirFatura(12); // Exemplo: imprimir fatura do mês 12
                    break;
                case 4:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } else if (usuario instanceof PosPago) {
            System.out.println("\nEscolha a operação:\n1 - Fazer Chamada\n2 - Imprimir Fatura\n3 - Sair\n");
            int opcao = scan.nextInt();
            scan.nextLine();  // Consumir a nova linha

            switch (opcao) {
                case 1:
                    telefonia.fazerChamada(usuario);
                    break;
                case 2:
                    ((PosPago) usuario).imprimirFatura(12); // Exemplo: imprimir fatura do mês 12
                    break;
                case 3:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
  }
}