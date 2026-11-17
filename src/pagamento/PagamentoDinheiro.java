package pagamento;

public class PagamentoDinheiro implements Pagamento {
    private final double valorEntregue;

    public PagamentoDinheiro(double valorEntregue) {
        this.valorEntregue = valorEntregue;
    }

    @Override
    public void realizarPagamento(double valorPago) {
        if (valorEntregue < valorPago) {
            throw new IllegalArgumentException("Dinheiro insuficiente. Faltam R$ " + (valorPago - valorEntregue));
        }

        double troco = valorEntregue - valorPago;
        System.out.println("Pagamento em dinheiro realizado, total: R$ " + valorPago);
        if (troco > 0) {
            System.out.println("Troco: R$ " + troco);
        }
    }
}