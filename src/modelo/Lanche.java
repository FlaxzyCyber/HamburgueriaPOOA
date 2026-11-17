package modelo;

public class Lanche extends ItemCardapio {
    String tipoPao = "pão italiano";
    int quantidade_hamburgueres = 1;
    boolean temCebola = true;
    boolean condimentos = true;
    boolean ketchup = true;

    public Lanche(String nomeItemCardapio, double pesoItem, double precoItem, String tipoPao,
                  int quantidadeHamburgueres, boolean temCebola, boolean condimentos, boolean ketchup) {
        this.nomeItemCardapio = nomeItemCardapio;
        this.pesoItem = pesoItem;
        this.precoItem = precoItem;
        this.tipoPao = tipoPao;
        this.quantidade_hamburgueres = quantidadeHamburgueres;
        this.temCebola = temCebola;
        this.condimentos = condimentos;
        this.ketchup = ketchup;
    }

    // MUDANÇA: Exibe no resumo o nome do lanche junto com o pão e a quantidade de carnes.
    @Override
    public String getNomeItemCardapio() {
        return super.getNomeItemCardapio() + " (" + tipoPao + ", " + quantidade_hamburgueres + " hamb.)";
    }
}