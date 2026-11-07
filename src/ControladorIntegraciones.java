public class ControladorIntegraciones {
    private final CatalogoIntegraciones catalogo;
    private final VistaConsola vista;
    private final MotorCheckout motor;
    public ControladorIntegraciones(CatalogoIntegraciones catalogo, VistaConsola vista, MotorCheckout motor) {
        this.catalogo = catalogo;
        this.vista = vista;
        this.motor = motor;
    }
    public void iniciar() {
        vista.mostrar("Se cargaron " + catalogo.getCantidad() + " integraciones simuladas.");
        try {
            int opcion;
            do {
                vista.mostrarMenu();
                opcion = vista.leerEntero("Seleccione una opción: ");
                ejecutar(opcion);
            } while (opcion != 0);
        } catch (java.util.NoSuchElementException error) {
            vista.mostrar("Fin de la entrada. Programa cerrado.");
        }
    }
    private IntegracionAPI solicitarAPI() {
        IntegracionAPI api = catalogo.buscar(vista.leerTexto("Identificador (ej. PAG-01): "));
        if (api == null) vista.mostrar("No existe una API con ese identificador.");
        return api;
    }
    private void ejecutar(int opcion) {
        switch (opcion) {
            case 0: vista.mostrar("Programa finalizado."); return;
            case 1: vista.mostrar(catalogo.listar()); return;
            case 3:
                catalogo.ordenarPorLatencia();
                vista.mostrar("Catálogo ordenado de menor a mayor latencia:\n" + catalogo.listar());
                return;
            case 12: vista.mostrar(catalogo.listar(true)); return;
            default:
                if (opcion < 2 || opcion > 11) { vista.mostrar("Opción inválida."); return; }
        }
        IntegracionAPI api = solicitarAPI();
        if (api == null) return;
        String resultado;
        switch (opcion) {
            case 2: resultado = api.obtenerConfiguracion(); break;
            case 4: resultado = api.consultarEstado(); break;
            case 5: resultado = motor.procesar(api, vista.leerDecimal("Monto del cobro (Q): ")); break;
            case 6: resultado = motor.procesar(api, vista.leerDecimal("Monto del reintegro (Q): "), true); break;
            case 7: resultado = motor.documentar(api, vista.leerTexto("Referencia del pedido: ")); break;
            case 8: vista.mostrar(motor.auditar(api)); return;
            case 9:
                int estado = vista.leerEntero("1. Conectar / 2. Desconectar: ");
                if (estado != 1 && estado != 2) { vista.mostrar("Estado inválido."); return; }
                api.setEstado(estado == 1 ? EstadoConexion.CONECTADA : EstadoConexion.DESCONECTADA);
                resultado = api.consultarEstado(); break;
            case 10:
            case 11:
                // Solo las consultas logísticas específicas necesitan comprobar el tipo.
                // Cobros, reintegros, documentos y auditoría usan directamente interfaces.
                if (!(api instanceof ProveedorLogistico)) {
                    resultado = "IGNORADO: la API seleccionada no ofrece consultas logísticas.";
                } else {
                    ProveedorLogistico logistica = (ProveedorLogistico) api;
                    resultado = opcion == 10
                        ? logistica.cotizar(vista.leerDecimal("Peso en kg: "))
                        : logistica.rastrear(vista.leerTexto("Número de guía: "));
                }
                break;
            default: return;
        }
        api.registrarEvento(resultado);
        vista.mostrar(resultado);
    }
}
