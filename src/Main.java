public class Main {
    public static void main(String[] args) {
        CatalogoIntegraciones catalogo = new CatalogoIntegraciones();
        CargaInicial.cargar(catalogo);
        VistaConsola vista = new VistaConsola();
        MotorCheckout motor = new MotorCheckout();
        new ControladorIntegraciones(catalogo, vista, motor).iniciar();
    }
}
