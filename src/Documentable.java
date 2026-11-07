public interface Documentable {
    default String generarDocumento(String referencia) {
        return "IGNORADO: esta integración no genera documentos.";
    }
}
