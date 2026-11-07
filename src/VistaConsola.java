import java.math.BigDecimal;
import java.util.Scanner;

/** Única clase que lee y escribe en consola. */
public class VistaConsola {
    private final Scanner entrada = new Scanner(System.in);
    public void mostrarMenu() {
        mostrar("\n=== ADMINISTRADOR DE INTEGRACIONES (SIMULACIÓN) ==="
            + "\n1. Listar todas las integraciones y su estado"
            + "\n2. Buscar por identificador y ver configuración"
            + "\n3. Ordenar por latencia (menor a mayor)"
            + "\n4. Consultar estado de una API"
            + "\n5. Simular cobro"
            + "\n6. Simular reintegro"
            + "\n7. Generar documento simulado"
            + "\n8. Consultar historial de auditoría"
            + "\n9. Cambiar estado de conexión"
            + "\n10. Cotizar envío"
            + "\n11. Rastrear paquete"
            + "\n12. Listar solo conectadas"
            + "\n0. Salir");
    }
    public void mostrar(String texto) { System.out.println(texto); }
    public String leerTexto(String mensaje) {
        System.out.print(mensaje);
        if (!entrada.hasNextLine()) throw new java.util.NoSuchElementException("Entrada finalizada.");
        return entrada.nextLine().trim();
    }
    public int leerEntero(String mensaje) {
        while (true) {
            try { return Integer.parseInt(leerTexto(mensaje)); }
            catch (NumberFormatException error) { mostrar("Ingrese un número entero válido."); }
        }
    }
    public BigDecimal leerDecimal(String mensaje) {
        while (true) {
            try {
                BigDecimal valor = new BigDecimal(leerTexto(mensaje).replace(',', '.'));
                if (valor.signum() > 0 && valor.scale() <= 2) return valor;
            } catch (NumberFormatException error){
                
            }
            mostrar("Ingrese un número positivo con máximo dos decimales.");
        }
    }
}
