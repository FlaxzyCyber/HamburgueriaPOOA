package pagamento;

import modelo.Pedido;

public class PagamentoPix implements Pagamento{
    final double descontoPix = 0.95;
    @Override
    public void realizarPagamento(double valorPago) {
        valorPago = valorPago * descontoPix;
        System.out.println("Pagamento em pix realizado, total: " + valorPago);
    }
}
