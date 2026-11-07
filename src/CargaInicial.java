import java.math.BigDecimal;

public class CargaInicial {
    public static void cargar(CatalogoIntegraciones catalogo) {
        catalogo.agregar(new PasarelaTarjeta("PAG-01", "Tarjeta Uno", "https://tarjeta1.example/api", 130));
        catalogo.agregar(new PasarelaTarjeta("PAG-02", "Tarjeta Dos", "https://tarjeta2.example/api", 220));
        catalogo.agregar(new TransferenciaBancaria("PAG-03", "Banco Demo", "https://banco.example/api", 310));
        catalogo.agregar(new BilleteraDigital("PAG-04", "Billetera Demo", "https://billetera.example/api", 90));
        catalogo.agregar(new ProveedorLogistico("LOG-01", "Envíos Uno", "https://envios1.example/api", 450, new BigDecimal("12.50")));
        catalogo.agregar(new ProveedorLogistico("LOG-02", "Envíos Dos", "https://envios2.example/api", 170, new BigDecimal("10.00")));
        catalogo.agregar(new ProveedorLogistico("LOG-03", "Última Milla", "https://milla.example/api", 75, new BigDecimal("8.00")));
        catalogo.agregar(new CertificadorFEL("FEL-01", "Certificador Uno", "https://fel1.example/api", 260, "1234567-8"));
        catalogo.agregar(new CertificadorFEL("FEL-02", "Certificador Dos", "https://fel2.example/api", 180, "1234567-8"));
        catalogo.agregar(new CertificadorFEL("FEL-03", "Certificador Tres", "https://fel3.example/api", 340, "1234567-8"));
    }
}
