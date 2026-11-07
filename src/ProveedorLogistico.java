import java.math.BigDecimal;
import java.math.RoundingMode;

public class ProveedorLogistico extends IntegracionAPI {
    private final BigDecimal tarifaPorKg;
    public ProveedorLogistico(String id, String nombre, String endpoint, double latencia,
            BigDecimal tarifaPorKg) {
        super(id, nombre, endpoint, latencia);
        if (tarifaPorKg == null || tarifaPorKg.signum() <= 0)
            throw new IllegalArgumentException("La tarifa debe ser positiva.");
        this.tarifaPorKg = tarifaPorKg;
    }
    public BigDecimal getTarifaPorKg() { return tarifaPorKg; }
    public String cotizar(BigDecimal pesoKg) {
        if (!estaConectada()) return "No se pudo cotizar: API desconectada.";
        if (pesoKg == null || pesoKg.signum() <= 0) return "El peso debe ser positivo.";
        return "Cotización simulada: Q" + tarifaPorKg.multiply(pesoKg)
            .setScale(2, RoundingMode.HALF_UP).toPlainString();
    }
    public String rastrear(String guia) {
        if (!estaConectada()) return "No se pudo rastrear: API desconectada.";
        if (guia == null || guia.trim().isEmpty()) return "Indique una guía.";
        return "Guía " + guia.trim() + ": EN TRÁNSITO (dato simulado).";
    }
    @Override public String generarDocumento(String referencia) {
        if (!estaConectada()) return "No se pudo generar la guía: API desconectada.";
        if (referencia == null || referencia.trim().isEmpty()) return "Indique una referencia.";
        return "GUÍA DE ENVÍO SIMULADA\nProveedor: " + getNombre()
            + "\nReferencia: " + referencia.trim() + "\nSin validez comercial.";
    }
    @Override public String obtenerConfiguracion() {
        return super.obtenerConfiguracion() + "\nTarifa por kg: Q" + tarifaPorKg.toPlainString();
    }
}
