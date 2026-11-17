import construtor.BuilderBebida;
import construtor.BuilderLanche;
import construtor.BuilderSobremesa;
import facade.FacadeHamburgueria;
import pagamento.Pagamento;
import pagamento.PagamentoCartao;
import pagamento.PagamentoDinheiro;
import pagamento.PagamentoPix;

import java.util.Scanner;

public class Main {
    private static final Scanner entrada = new Scanner(System.in);
    private static final FacadeHamburgueria facade = new FacadeHamburgueria();

    public static void main(String[] args) {
        int opcao;
        do {
            mostrarMenu();
            opcao = lerInteiro("Escolha: ");
            try {
                executar(opcao);
            } catch (IllegalStateException | IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (opcao != 0);
    }

    private static void mostrarMenu() {
        System.out.println("\n=== Hamburgueria ===");
        System.out.println("1 - Adicionar lanche");
        System.out.println("2 - Adicionar bebida");
        System.out.println("3 - Adicionar sobremesa");
        System.out.println("4 - Finalizar pedido");
        System.out.println("5 - Pagar");
        System.out.println("6 - Avançar status");
        System.out.println("7 - Ver resumo");
        System.out.println("0 - Sair");
    }

    private static void executar(int opcao) {
        switch (opcao) {
            case 1 -> {
                BuilderLanche opcoes = new BuilderLanche();
                String pao = lerTexto("Tipo de pão (Enter = padrão): ");
                if (!pao.isEmpty()) {
                    opcoes.tipoPao(pao);
                }
                opcoes.quantidadeHamburguer(lerInteiroPositivo("Quantos hambúrgueres no lanche? "));
                opcoes.temCebola(lerSimNao("Com cebola? (s/n): "));
                opcoes.temCodimentos(lerSimNao("Com condimentos? (s/n): "));
                opcoes.temKetchup(lerSimNao("Com ketchup? (s/n): "));
                int quantidade = lerInteiroPositivo("Quantos lanches desses? ");
                boolean queijo = lerSimNao("Extra de queijo? (s/n): ");
                boolean bacon = lerSimNao("Extra de bacon? (s/n): ");
                facade.adicionarLanche(opcoes, quantidade, queijo, bacon);
                System.out.println("Lanche adicionado!");
            }
            case 2 -> {
                BuilderBebida opcoes = new BuilderBebida();
                opcoes.tamanhoBebida(lerValor("Tamanho em ml: "));
                String marca = lerTexto("Marca (Enter = padrão): ");
                if (!marca.isEmpty()) {
                    opcoes.marcaBebida(marca);
                }
                String sabor = lerTexto("Sabor (Enter = padrão): ");
                if (!sabor.isEmpty()) {
                    opcoes.saborBebida(sabor);
                }
                opcoes.terCopo(lerSimNao("Com copo? (s/n): "));
                opcoes.terCanudo(lerSimNao("Com canudo? (s/n): "));
                facade.adicionarBebida(opcoes, lerInteiroPositivo("Quantas bebidas dessas? "));
                System.out.println("Bebida adicionada!");
            }
            case 3 -> {
                BuilderSobremesa opcoes = new BuilderSobremesa();
                String sabor = lerTexto("Sabor da sobremesa (Enter = padrão): ");
                if (!sabor.isEmpty()) {
                    opcoes.saborSobremesa(sabor);
                }
                String calda = lerTexto("Sabor da calda (Enter = padrão): ");
                if (!calda.isEmpty()) {
                    opcoes.saborCalda(calda);
                }
                opcoes.temJujuba(lerSimNao("Com jujuba? (s/n): "));
                opcoes.temNutella(lerSimNao("Com Nutella? (s/n): "));
                facade.adicionarSobremesa(opcoes, lerInteiroPositivo("Quantas sobremesas dessas? "));
                System.out.println("Sobremesa adicionada!");
            }
            case 4 -> {
                System.out.print("Endereço de entrega: ");
                String endereco = entrada.nextLine();
                System.out.print("Observações (pode deixar vazio): ");
                String observacoes = entrada.nextLine();
                facade.fazerPedido(endereco, observacoes);
                System.out.println("Pedido criado!");
            }
            case 5 -> facade.pagar(escolherPagamento());
            case 6 -> facade.avancarStatus();
            case 7 -> facade.resumo();
            case 0 -> System.out.println("Até logo!");
            default -> System.out.println("Opção inválida.");
        }
    }

    private static Pagamento escolherPagamento() {
        System.out.println("1 - Pix | 2 - Cartão | 3 - Dinheiro");
        int forma = lerInteiro("Forma de pagamento: ");
        return switch (forma) {
            case 1 -> new PagamentoPix();
            case 2 -> new PagamentoCartao();
            case 3 -> new PagamentoDinheiro(lerValor("Quanto o cliente entregou? R$ "));
            default -> throw new IllegalArgumentException("Forma de pagamento inválida.");
        };
    }

    private static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return entrada.nextLine().trim();
    }

    private static int lerInteiroPositivo(String mensagem) {
        int numero = lerInteiro(mensagem);
        while (numero < 1) {
            System.out.println("Digite um número maior que zero.");
            numero = lerInteiro(mensagem);
        }
        return numero;
    }

    private static int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                return Integer.parseInt(entrada.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido.");
            }
        }
    }

    private static double lerValor(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                return Double.parseDouble(entrada.nextLine().trim().replace(',', '.'));
            } catch (NumberFormatException e) {
                System.out.println("Digite um valor válido.");
            }
        }
    }

    private static boolean lerSimNao(String mensagem) {
        System.out.print(mensagem);
        return entrada.nextLine().trim().equalsIgnoreCase("s");
    }
}