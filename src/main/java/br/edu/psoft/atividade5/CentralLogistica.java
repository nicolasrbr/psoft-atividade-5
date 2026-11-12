package br.edu.psoft.atividade5;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class CentralLogistica {
    private static final CentralLogistica INSTANCIA = new CentralLogistica();

    private final Map<Integer, Pedido> pedidos = new ConcurrentHashMap<>();

    private CentralLogistica() {
    }

    public static CentralLogistica getInstance() {
        return INSTANCIA;
    }

    public void registrarPedido(Pedido pedido) {
        if (pedido == null) {
            throw new IllegalArgumentException("pedido não pode ser nulo");
        }
        Pedido pedidoAnterior = pedidos.putIfAbsent(pedido.getId(), pedido);
        if (pedidoAnterior != null) {
            throw new IllegalArgumentException("já existe um pedido com o id " + pedido.getId());
        }
    }

    public void selecionarModalidade(int idPedido, ModalidadeEntrega modalidadeEntrega) {
        buscarPedido(idPedido).definirModalidade(modalidadeEntrega);
    }

    public double consultarCusto(int idPedido) {
        return buscarPedido(idPedido).calcularCusto();
    }

    public String consultarPrazoEstimado(int idPedido) {
        return buscarPedido(idPedido).calcularPrazoEstimado();
    }

    public void despacharPedido(int idPedido) {
        buscarPedido(idPedido).despachar();
    }

    public boolean acompanharDespacho(int idPedido) {
        return buscarPedido(idPedido).isDespachado();
    }

    private Pedido buscarPedido(int idPedido) {
        Pedido pedido = pedidos.get(idPedido);
        if (pedido == null) {
            throw new IllegalArgumentException("pedido não encontrado: " + idPedido);
        }
        return pedido;
    }
}
