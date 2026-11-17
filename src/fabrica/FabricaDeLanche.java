package fabrica;

import construtor.BuilderLanche;
import modelo.ItemCardapio;
import modelo.Lanche;

public class FabricaDeLanche extends FabricaItemCardapio<BuilderLanche> {

    @Override
    protected ItemCardapio instanciarItem(String nomeItemCardapio, BuilderLanche builderLanche) {
        double precoBase = switch (nomeItemCardapio) {
            case "X-Burger" -> 22.0;
            case "X-Salada" -> 25.0;
            case "X-Tudo" -> 32.0;
            default -> 22.0;
        };

        double pesoBase = switch (nomeItemCardapio) {
            case "X-Burger" -> 280.0;
            case "X-Salada" -> 320.0;
            case "X-Tudo" -> 420.0;
            default -> 280.0;
        };

        int extrasCarne = builderLanche.getQuantidade_hamburgueres() - 1;
        double precoFinal = precoBase + (extrasCarne * 8.0);
        double pesoFinal = pesoBase + (extrasCarne * 120.0);

        return new Lanche(nomeItemCardapio, pesoFinal, precoFinal,
                builderLanche.getTipoPao(), builderLanche.getQuantidade_hamburgueres(),
                builderLanche.isTemCebola(), builderLanche.isCondimentos(), builderLanche.isKetchup());
    }
}