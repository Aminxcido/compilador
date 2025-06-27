import java.util.*;

public class Main {

    enum Estado {
        S0, S1, S2, ERROR
    }

    // Tabla de transiciones explícita
    static Map<Estado, Map<Character, Estado>> tablaTransiciones = new HashMap<>();

    // Inicializar tabla
    static void inicializarTabla() {
        tablaTransiciones.put(Estado.S0, Map.of('0', Estado.S0, '1', Estado.S1));
        tablaTransiciones.put(Estado.S1, Map.of('0', Estado.S0, '1', Estado.S2));
        tablaTransiciones.put(Estado.S2, Map.of('0', Estado.S0, '1', Estado.ERROR));
        tablaTransiciones.put(Estado.ERROR, Map.of('0', Estado.ERROR, '1', Estado.ERROR));
    }

    // Transición utilizando tabla
    static Estado transicion(Estado estadoActual, char simbolo) {
        // Si el estado o símbolo no existen en la tabla, es error
        if (!tablaTransiciones.containsKey(estadoActual) ||
                !tablaTransiciones.get(estadoActual).containsKey(simbolo)) {
            return Estado.ERROR;
        }
        return tablaTransiciones.get(estadoActual).get(simbolo);
    }

    // Evaluación de la cadena con trazado
    static boolean esComandoValido(String cadena) {
        Estado estado = Estado.S0;
        System.out.print("Ruta de estados: " + estado);

        for (char simbolo : cadena.toCharArray()) {
            if (simbolo != '0' && simbolo != '1') {
                System.out.println(" → símbolo inválido '" + simbolo + "'");
                return false;
            }
            estado = transicion(estado, simbolo);
            System.out.print(" → " + estado);
        }
        System.out.println();
        return estado != Estado.ERROR;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        inicializarTabla();  // Muy importante

        while (true) {
            System.out.print("\nIngrese cadena binaria (o 'salir'): ");
            String entrada = scanner.nextLine();
            if (entrada.equalsIgnoreCase("salir")) break;

            if (esComandoValido(entrada)) {
                System.out.println("Comando válido.");
            } else {
                System.out.println("Rechazado (contiene '111').");
            }
        }
    }
}
