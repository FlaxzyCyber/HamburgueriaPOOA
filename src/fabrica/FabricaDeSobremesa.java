package fabrica;

import construtor.BuilderSobremesa;
import modelo.ItemCardapio;
import modelo.Sobremesa;

public class FabricaDeSobremesa extends FabricaItemCardapio<BuilderSobremesa> {

    @Override
    protected ItemCardapio instanciarItem(String nomeItemCardapio, BuilderSobremesa builderSobremesa) {
        double precoBase = switch (nomeItemCardapio) {
            case "Sorvete" -> 12.0;
            case "Açaí" -> 16.0;
            case "Pizza Doce" -> 20.0;
            default -> 12.0;
        };

        double pesoBase = switch (nomeItemCardapio) {
            case "Sorvete" -> 200.0;
            case "Açaí" -> 300.0;
            case "Pizza Doce" -> 350.0;
            default -> 200.0;
        };

        double precoFinal = precoBase;
        if (builderSobremesa.isTemJujuba()) {
            precoFinal += 2.0;
        }
        if (builderSobremesa.isTemNutella()) {
            precoFinal += 4.0;
        }

        return new Sobremesa(nomeItemCardapio, pesoBase, precoFinal,
                builderSobremesa.getSabor(), builderSobremesa.getSaborCalda(),
                builderSobremesa.isTemJujuba(), builderSobremesa.isTemNutella());
    }
}