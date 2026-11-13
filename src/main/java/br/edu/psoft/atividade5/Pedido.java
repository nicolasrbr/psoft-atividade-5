package br.edu.psoft.atividade5;

import java.util.Objects;

public final class Pedido {
    private final int id;
    private final Cliente cliente;
    private final String enderecoOrigem;
    private final String enderecoDestino;
    private final double pesoKg;
    private final double distanciaKm;
    private ModalidadeEntrega modalidadeEntrega;
    private boolean despachado;

    public Pedido(
            int id,
            Cliente cliente,
            String enderecoOrigem,
            String enderecoDestino,
            double pesoKg,
            double distanciaKm) {
        if (id <= 0) {
            throw new IllegalArgumentException("id deve ser positivo");
        }
        if (!Double.isFinite(pesoKg) || pesoKg <= 0) {
            throw new IllegalArgumentException("peso deve ser positivo e finito");
        }
        if (!Double.isFinite(distanciaKm) || distanciaKm <= 0) {
            throw new IllegalArgumentException("distância deve ser positiva e finita");
        }

        this.id = id;
        this.cliente = Objects.requireNonNull(cliente, "cliente não pode ser nulo");
        this.enderecoOrigem = validarEndereco(enderecoOrigem, "origem");
        this.enderecoDestino = validarEndereco(enderecoDestino, "destino");
        this.pesoKg = pesoKg;
        this.distanciaKm = distanciaKm;
    }

    private static String validarEndereco(String endereco, String tipo) {
        Objects.requireNonNull(endereco, "endereço de " + tipo + " não pode ser nulo");
        String enderecoValidado = endereco.trim();
        if (enderecoValidado.isEmpty()) {
            throw new IllegalArgumentException("endereço de " + tipo + " não pode ser vazio");
        }
        return enderecoValidado;
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getEnderecoOrigem() {
        return enderecoOrigem;
    }

    public String getEnderecoDestino() {
        return enderecoDestino;
    }

    public double getPesoKg() {
        return pesoKg;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public synchronized void definirModalidade(ModalidadeEntrega modalidadeEntrega) {
        if (despachado) {
            throw new IllegalStateException("não é possível alterar a modalidade após o despacho");
        }
        this.modalidadeEntrega =
                Objects.requireNonNull(modalidadeEntrega, "modalidade não pode ser nula");
    }

    public synchronized double calcularCusto() {
        return getModalidade().calcularCusto(distanciaKm);
    }

    public synchronized String calcularPrazoEstimado() {
        return getModalidade().calcularPrazo(distanciaKm);
    }

    public synchronized boolean isDespachado() {
        return despachado;
    }

    public synchronized void despachar() {
        getModalidade();
        despachado = true;
    }

    private ModalidadeEntrega getModalidade() {
        if (modalidadeEntrega == null) {
            throw new IllegalStateException("modalidade de entrega não definida");
        }
        return modalidadeEntrega;
    }
}
