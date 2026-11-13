package br.edu.psoft.atividade5;

public interface ModalidadeEntrega {
    double calcularCusto(double distanciaKm);

    String calcularPrazo(double distanciaKm);
}
