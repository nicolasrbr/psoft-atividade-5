package br.edu.psoft.atividade5;

public final class EntregaExpressa implements ModalidadeEntrega {
    private static final double TARIFA_FIXA = 10.0;
    private static final double VALOR_POR_KM = 1.5;

    @Override
    public double calcularCusto(double distanciaKm) {
        return TARIFA_FIXA + VALOR_POR_KM * distanciaKm;
    }

    @Override
    public String calcularPrazo(double distanciaKm) {
        if (distanciaKm <= 10) {
            return "12 horas";
        }
        if (distanciaKm <= 100) {
            return "24 horas";
        }
        return "48 horas";
    }
}
