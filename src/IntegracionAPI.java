import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;


public abstract class IntegracionAPI implements Transaccional, Documentable,
        Auditable, Comparable<IntegracionAPI> {
    private final String identificador;
    private final String nombre;
    private final String endpoint;
    private EstadoConexion estado;
    private double latenciaPromedio;
    private final StringBuilder historial = new StringBuilder();

    protected IntegracionAPI(String id, String nombre, String endpoint, double latencia) {
        if (id == null || id.trim().isEmpty() || nombre == null || nombre.trim().isEmpty()
                || endpoint == null || endpoint.trim().isEmpty()) {
            throw new IllegalArgumentException("La configuración no puede estar vacía.");
        }
        identificador = id.trim().toUpperCase(Locale.ROOT);
        this.nombre = nombre.trim();
        this.endpoint = endpoint.trim();
        setLatenciaPromedio(latencia);
        estado = EstadoConexion.CONECTADA;
        registrarEvento("Integración registrada (simulación).");
    }
    public String getIdentificador() { return identificador; }
    public String getNombre() { return nombre; }
    public String getEndpoint() { return endpoint; }
    public EstadoConexion getEstado() { return estado; }
    public double getLatenciaPromedio() { return latenciaPromedio; }
    public void setEstado(EstadoConexion estado) {
        this.estado = Objects.requireNonNull(estado, "Estado obligatorio.");
        registrarEvento("Estado cambiado a " + estado);
    }
    public void setLatenciaPromedio(double latencia) {
        if (!Double.isFinite(latencia) || latencia < 0) {
            throw new IllegalArgumentException("Latencia no negativa y finita requerida.");
        }
        latenciaPromedio = latencia;
    }
    public boolean estaConectada() { return estado == EstadoConexion.CONECTADA; }
    public String consultarEstado() {
        return identificador + ": " + estado + " (estado simulado)";
    }
    public String obtenerConfiguracion() {
        return "ID: " + identificador + "\nNombre: " + nombre
            + "\nTipo: " + getClass().getSimpleName() + "\nEndpoint: " + endpoint
            + "\nEstado: " + estado + "\nLatencia: " + latenciaPromedio + " ms";
    }
    @Override public void registrarEvento(String evento) {
        historial.append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                 .append(" | ").append(evento).append('\n');
    }
    @Override public String consultarHistorial() { return historial.toString(); }
    @Override public int compareTo(IntegracionAPI otra) {
        int resultado = Double.compare(latenciaPromedio, otra.latenciaPromedio);
        return resultado != 0 ? resultado : identificador.compareTo(otra.identificador);
    }
    @Override public boolean equals(Object objeto) {
        if (this == objeto) return true;
        if (!(objeto instanceof IntegracionAPI)) return false;
        return identificador.equals(((IntegracionAPI) objeto).identificador);
    }
    @Override public int hashCode() { return identificador.hashCode(); }
    @Override public String toString() {
        return String.format(Locale.ROOT, "%s | %s | %s | %.1f ms | %s",
            identificador, nombre, estado, latenciaPromedio, getClass().getSimpleName());
    }
}
