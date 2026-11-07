import java.math.BigDecimal;

public interface Transaccional {
    default String cobrar(BigDecimal monto) {
        return "IGNORADO: esta integración no admite cobros.";
    }
    default String reintegrar(BigDecimal monto) {
        return "IGNORADO: esta integración no admite reintegros.";
    }
}
