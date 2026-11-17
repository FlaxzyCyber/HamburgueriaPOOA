package modelo;

import observador.ObservadorPedido;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
   private List<ItemCardapio> itens;
   private String endereco;
   private String observacoes;
   private double valorEntrega;
   private StatusPedido status;
   private List<ObservadorPedido> observadores = new ArrayList<>();

    public Pedido(List<ItemCardapio> itens, String endereco, String observacoes, double valorEntrega) {
        this.itens = new ArrayList<>(itens);
        this.endereco = endereco;
        this.observacoes = observacoes;
        this.valorEntrega = valorEntrega;
        this.status = StatusPedido.RECEBIDO;
    }

    public void adicionarObservador(ObservadorPedido observador) {
        this.observadores.add(observador);
    }

    public void setStatus(StatusPedido novoStatus) {
      this.status = novoStatus;
      notificarObservadores();
    }

    private void notificarObservadores() {
      for (ObservadorPedido obs : observadores) {
        obs.atualizar(this);
      }
    }

    public StatusPedido getStatus() {
      return status;
    }

    public double valorTotalPedido(){
        double total = valorEntrega;
        for (ItemCardapio item : itens) {
            total += item.getPrecoItem() * item.getQuantidade();
        }
        return total;
    }

    public void avancarStatus() {
        StatusPedido[] todos = StatusPedido.values();
        int proximo = status.ordinal() + 1;
        if (proximo >= todos.length) {
            throw new IllegalStateException("O pedido já foi entregue.");
        }
        setStatus(todos[proximo]);
    }
}
