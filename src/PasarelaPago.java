import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class PasarelaPago extends IntegracionAPI {
    private BigDecimal saldoReintegrable = new BigDecimal("0.00");
    protected PasarelaPago(String id, String nombre, String endpoint, double latencia) {
        super(id, nombre, endpoint, latencia);
    }
    public BigDecimal getSaldoReintegrable() { return saldoReintegrable; }
    @Override public String cobrar(BigDecimal monto) {
        if (!estaConectada()) return "No se pudo cobrar: API desconectada.";
        if (monto == null || monto.signum() <= 0 || monto.scale() > 2)
            return "Monto inválido: use un valor positivo con máximo dos decimales.";
        monto = monto.setScale(2, RoundingMode.UNNECESSARY);
        saldoReintegrable = saldoReintegrable.add(monto);
        return "Cobro simulado de Q" + monto.toPlainString() + " mediante " + getNombre() + ".";
    }
    @Override public String reintegrar(BigDecimal monto) {
        if (!estaConectada()) return "No se pudo reintegrar: API desconectada.";
        if (monto == null || monto.signum() <= 0 || monto.scale() > 2)
            return "Monto inválido: use un valor positivo con máximo dos decimales.";
        monto = monto.setScale(2, RoundingMode.UNNECESSARY);
        if (monto.compareTo(saldoReintegrable) > 0)
            return "Reintegro rechazado: supera el saldo cobrado disponible (Q"
                + saldoReintegrable.toPlainString() + ").";
        saldoReintegrable = saldoReintegrable.subtract(monto);
        return "Reintegro simulado de Q" + monto.toPlainString() + " mediante " + getNombre() + ".";
    }
    @Override public String obtenerConfiguracion() {
        return super.obtenerConfiguracion() + "\nSaldo reintegrable: Q" + saldoReintegrable.toPlainString();
    }
}
