public class CertificadorFEL extends IntegracionAPI {
    private final String nitEmisor;
    public CertificadorFEL(String id, String nombre, String endpoint, double latencia, String nit) {
        super(id, nombre, endpoint, latencia);
        if (nit == null || nit.trim().isEmpty()) throw new IllegalArgumentException("NIT obligatorio.");
        nitEmisor = nit.trim();
    }
    public String getNitEmisor() { return nitEmisor; }
    @Override public String generarDocumento(String referencia) {
        if (!estaConectada()) return "No se pudo generar XML: API desconectada.";
        if (referencia == null || referencia.trim().isEmpty()) return "Indique una referencia.";
        String ref = referencia.trim().replace("&", "&amp;").replace("<", "&lt;")
            .replace(">", "&gt;").replace("\"", "&quot;").replace("'", "&apos;");
        String nit = nitEmisor.replace("&", "&amp;").replace("<", "&lt;")
            .replace(">", "&gt;").replace("\"", "&quot;").replace("'", "&apos;");
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
            + "<FacturaSimulada nitEmisor=\"" + nit + "\" validezLegal=\"false\">"
            + "<Referencia>" + ref + "</Referencia></FacturaSimulada>";
    }
    @Override public String obtenerConfiguracion() {
        return super.obtenerConfiguracion() + "\nNIT emisor: " + nitEmisor;
    }
}
