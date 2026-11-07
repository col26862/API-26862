import java.util.ArrayList;
import java.util.Collections;

public class CatalogoIntegraciones {
    private final ArrayList<IntegracionAPI> integraciones = new ArrayList<>();
    public CatalogoIntegraciones() { }
    public boolean agregar(IntegracionAPI api) {
        if (api == null || buscar(api.getIdentificador()) != null) return false;
        integraciones.add(api);
        return true;
    }
    public IntegracionAPI buscar(String identificador) {
        if (identificador == null) return null;
        for (IntegracionAPI api : integraciones)
            if (api.getIdentificador().equalsIgnoreCase(identificador.trim())) return api;
        return null;
    }
    public void ordenarPorLatencia() { Collections.sort(integraciones); }
    public String listar() { return listar(false); }
    public String listar(boolean soloConectadas) {
        StringBuilder resultado = new StringBuilder();
        for (IntegracionAPI api : integraciones)
            if (!soloConectadas || api.estaConectada()) resultado.append(api).append('\n');
        return resultado.length() == 0 ? "No hay integraciones para mostrar." : resultado.toString();
    }
    public int getCantidad() { return integraciones.size(); }
}
