package fabrica;

import modelo.ItemCardapio;

public abstract class FabricaItemCardapio<T> {
    // MUDANÇA: A fábrica agora recebe apenas o nome do produto escolhido e o seu Builder,
    // ficando responsável por definir o peso e o preço base de cada categoria.
    protected abstract ItemCardapio instanciarItem(String nomeItemCardapio, T builder);

    public ItemCardapio criarItem(String nomeItemCardapio, T builder) {
        return instanciarItem(nomeItemCardapio, builder);
    }
}