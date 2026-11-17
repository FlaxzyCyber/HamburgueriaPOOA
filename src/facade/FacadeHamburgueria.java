package facade;

import construtor.BuilderBebida;
import construtor.BuilderLanche;
import construtor.BuilderPedido;
import construtor.BuilderSobremesa;
import decorador.ExtraBacon;
import decorador.ExtraQueijo;
import fabrica.FabricaDeBebida;
import fabrica.FabricaDeLanche;
import fabrica.FabricaDeSobremesa;
import modelo.ItemCardapio;
import modelo.Pedido;
import observador.NotificacaoCliente;
import observador.NotificacaoCozinha;
import pagamento.Pagamento;

import java.util.ArrayList;
import java.util.List;

public class FacadeHamburgueria {

    private static final String NOME_LANCHE = "X-Burger";
    private static final double PESO_LANCHE = 300.0;
    private static final double PRECO_LANCHE = 25.0;
    private static final String NOME_BEBIDA = "Refrigerante";
    private static final double PESO_BEBIDA = 350.0;
    private static final double PRECO_BEBIDA = 7.0;
    private static final String NOME_SOBREMESA = "Sorvete";
    private static final double PESO_SOBREMESA = 200.0;
    private static final double PRECO_SOBREMESA = 12.0;
    private static final double ADICIONAL_HAMBURGUER = 8.0;
    private static final double VALOR_ENTREGA = 5.0;

    private final FabricaDeLanche fabricaLanche = new FabricaDeLanche();
    private final FabricaDeBebida fabricaBebida = new FabricaDeBebida();
    private final FabricaDeSobremesa fabricaSobremesa = new FabricaDeSobremesa();

    private final List<ItemCardapio> itensEmMontagem = new ArrayList<>();
    private List<ItemCardapio> itensDoPedido = new ArrayList<>();
    private Pedido pedido;
    private boolean pago;

    public void adicionarLanche(BuilderLanche opcoes, int quantidade, boolean comQueijo, boolean comBacon) {
        exigirQuantidade(quantidade);

        double preco = PRECO_LANCHE + (opcoes.getQuantidade_hamburgueres() - 1) * ADICIONAL_HAMBURGUER;

        ItemCardapio lanche = fabricaLanche.criarItem(NOME_LANCHE, PESO_LANCHE, preco, opcoes);
        lanche.setQuantidade(quantidade);
        if (comQueijo) {
            lanche = new ExtraQueijo(lanche);
        }
        if (comBacon) {
            lanche = new ExtraBacon(lanche);
        }
        itensEmMontagem.add(lanche);
    }

    public void adicionarBebida(BuilderBebida opcoes, int quantidade) {
        exigirQuantidade(quantidade);
        ItemCardapio bebida = fabricaBebida.criarItem(NOME_BEBIDA, PESO_BEBIDA, PRECO_BEBIDA, opcoes);
        bebida.setQuantidade(quantidade);
        itensEmMontagem.add(bebida);
    }

    public void adicionarSobremesa(BuilderSobremesa opcoes, int quantidade) {
        exigirQuantidade(quantidade);
        ItemCardapio sobremesa = fabricaSobremesa.criarItem(NOME_SOBREMESA, PESO_SOBREMESA, PRECO_SOBREMESA, opcoes);
        sobremesa.setQuantidade(quantidade);
        itensEmMontagem.add(sobremesa);
    }

    public void fazerPedido(String endereco, String observacoes) {
        BuilderPedido builder = new BuilderPedido()
                .adicionarEndereco(endereco)
                .adicionarObservacoes(observacoes)
                .adicionarValorEntrega(VALOR_ENTREGA);
        for (ItemCardapio item : itensEmMontagem) {
            builder.adicionarItem(item);
        }

        pedido = builder.build();
        pedido.adicionarObservador(new NotificacaoCozinha());
        pedido.adicionarObservador(new NotificacaoCliente());

        itensDoPedido = new ArrayList<>(itensEmMontagem);
        itensEmMontagem.clear();
        pago = false;
    }

    public void pagar(Pagamento pagamento) {
        exigirPedido();
        if (pago) {
            throw new IllegalStateException("Este pedido já foi pago.");
        }
        pagamento.realizarPagamento(pedido.valorTotalPedido());
        pago = true;
    }

    public void avancarStatus() {
        exigirPedido();
        pedido.avancarStatus();
    }

    public void resumo() {
        exigirPedido();
        System.out.println("== Resumo do pedido ==");
        for (ItemCardapio item : itensDoPedido) {
            System.out.println(item.getDescricao() + " x" + item.getQuantidade()
                    + " = R$ " + item.getPrecoItem() * item.getQuantidade());
        }
        System.out.println("VALOR DA ENTREGA R$: " + VALOR_ENTREGA);
        System.out.println("VALOR TOTAL R$: " + pedido.valorTotalPedido());
        System.out.println("STATUS: " + pedido.getStatus());
    }

    private void exigirQuantidade(int quantidade) {
        if (quantidade < 1) {
            throw new IllegalArgumentException("A quantidade deve ser pelo menos 1.");
        }
    }

    private void exigirPedido() {
        if (pedido == null) {
            throw new IllegalStateException("Nenhum pedido foi finalizado ainda.");
        }
    }
}