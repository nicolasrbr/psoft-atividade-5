package br.edu.psoft.atividade5;

public final class EntregaEconomica implements ModalidadeEntrega {
    private static final double VALOR_POR_KM = 0.8;

    @Override
    public double calcularCusto(double distanciaKm) {
        return VALOR_POR_KM * distanciaKm;
    }

    @Override
    public String calcularPrazo(double distanciaKm) {
        if (distanciaKm <= 10) {
            return "12 dias";
        }
        if (distanciaKm <= 100) {
            return "20 dias";
        }
        return "30 dias";
    }
}
