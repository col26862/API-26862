import java.math.BigDecimal;

public class MotorCheckout {
    public String procesar(Transaccional servicio, BigDecimal monto) {
        return procesar(servicio, monto, false);
    }
    public String procesar(Transaccional servicio, BigDecimal monto, boolean esReintegro) {
        if (servicio == null) return "Servicio no disponible.";
        return esReintegro ? servicio.reintegrar(monto) : servicio.cobrar(monto);
    }
    public String documentar(Documentable servicio, String referencia) {
        return servicio == null ? "Servicio no disponible." : servicio.generarDocumento(referencia);
    }
    public String auditar(Auditable servicio) {
        return servicio == null ? "Servicio no disponible." : servicio.consultarHistorial();
    }
}
