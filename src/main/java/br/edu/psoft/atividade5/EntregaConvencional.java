package br.edu.psoft.atividade5;

public final class EntregaConvencional implements ModalidadeEntrega {
    private static final double TARIFA_FIXA = 10.0;
    private static final double VALOR_POR_KM = 1.0;

    @Override
    public double calcularCusto(double distanciaKm) {
        return TARIFA_FIXA + VALOR_POR_KM * distanciaKm;
    }

    @Override
    public String calcularPrazo(double distanciaKm) {
        if (distanciaKm <= 10) {
            return "1 dia";
        }
        if (distanciaKm <= 100) {
            return "3 dias";
        }
        return "7 dias";
    }
}
