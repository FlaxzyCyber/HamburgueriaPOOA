package fabrica;

import construtor.BuilderBebida;
import modelo.Bebida;
import modelo.ItemCardapio;

public class FabricaDeBebida extends FabricaItemCardapio<BuilderBebida> {

    @Override
    protected ItemCardapio instanciarItem(String nomeItemCardapio, BuilderBebida builderBebida) {
        double precoBase = switch (nomeItemCardapio) {
            case "Água" -> 4.0;
            case "Refrigerante" -> 7.0;
            case "Suco Natural" -> 9.0;
            case "Milk-Shake" -> 15.0;
            default -> 7.0;
        };

        double adicionalTamanho = 0.0;
        if (builderBebida.getVolumeMl() == 500.0) {
            adicionalTamanho = 3.0;
        } else if (builderBebida.getVolumeMl() >= 1000.0) {
            adicionalTamanho = 7.0;
        }

        double precoFinal = precoBase + adicionalTamanho;
        double pesoFinal = builderBebida.getVolumeMl();

        return new Bebida(nomeItemCardapio, pesoFinal, precoFinal,
                builderBebida.getVolumeMl(), builderBebida.getMarca(),
                builderBebida.getSabor(), builderBebida.isQuererCopo(), builderBebida.isQuererCanudo());
    }
}