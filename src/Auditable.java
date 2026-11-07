public interface Auditable {
    void registrarEvento(String evento);
    String consultarHistorial();
}
