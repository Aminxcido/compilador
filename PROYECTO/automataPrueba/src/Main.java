import java.util.*;

public class Main {

    enum Estado {
        INICIO, PESO, FUNCION_INICIO, FUNCION_INVALIDA, LETRA, DIGITO, SIMBOLO,
        TRIGFUNC, LOGFUNC, OTRASFUNC, SIMBFUNC, CONTENIDO, FIN, ERROR
    }

    static final Set<String> FUNCIONES_VALIDAS = new HashSet<>(Arrays.asList(
            "\\sin", "\\cos", "\\tan", "\\arcsin", "\\arccos", "\\arctan", "\\cot", "\\csc", "\\sec", "\\sinh", "\\cosh", "\\tanh", "\\coth",
            "\\log", "\\ln", "\\lg",
            "\\frac", "\\cdot", "\\exp", "\\deg", "\\det", "\\dim", "\\gcd", "\\hom", "\\inf", "\\sup", "\\ker", "\\lim", "\\liminf", "\\limsup",
            "\\max", "\\min", "\\Pr", "\\to", "\\arg",
            "\\theta", "\\alpha", "\\beta", "\\gamma", "\\delta", "\\epsilon", "\\pi", "\\mu", "\\nu", "\\rho", "\\sigma", "\\phi", "\\psi", "\\omega"
    ));

    static Map<Estado, Map<String, Estado>> transiciones = new HashMap<>(); //matriz de transiciones

    static {
        add(Estado.INICIO, "$", Estado.PESO);
        add(Estado.INICIO, "otro", Estado.ERROR);

        add(Estado.PESO, "(", Estado.FUNCION_INICIO);
        add(Estado.PESO, "letra", Estado.LETRA);
        add(Estado.PESO, "digito", Estado.DIGITO);
        add(Estado.PESO, "simbolo", Estado.SIMBOLO);
        add(Estado.PESO, "funcion_valida", Estado.TRIGFUNC);
        add(Estado.PESO, "funcion_invalida", Estado.FUNCION_INVALIDA);
        add(Estado.PESO, "invalido", Estado.ERROR);
        add(Estado.PESO, "cualquier", Estado.CONTENIDO);

        for (Estado s : List.of(Estado.LETRA, Estado.DIGITO, Estado.SIMBOLO, Estado.FUNCION_INICIO, Estado.TRIGFUNC, Estado.LOGFUNC, Estado.OTRASFUNC, Estado.SIMBFUNC)) {
            add(s, "cualquier", Estado.CONTENIDO);
        }

        add(Estado.CONTENIDO, "letra", Estado.CONTENIDO);
        add(Estado.CONTENIDO, "digito", Estado.CONTENIDO);
        add(Estado.CONTENIDO, "simbolo", Estado.CONTENIDO);
        add(Estado.CONTENIDO, "funcion_valida", Estado.CONTENIDO);
        add(Estado.CONTENIDO, "$final", Estado.FIN);
        add(Estado.CONTENIDO, "invalido", Estado.ERROR);
    }

    //inicialización de la matriz
    private static void add(Estado from, String inputType, Estado to) {
        transiciones.putIfAbsent(from, new HashMap<>());
        transiciones.get(from).put(inputType, to);
    }

    public static boolean esValido(String input, boolean debug) {
        Estado estado = Estado.INICIO;
        int i = 0;

        while (i < input.length()) {
            String tipoEntrada;
            char c = input.charAt(i);

            //Clasificacion del tipo de entrada

            if (c == '$') {
                tipoEntrada = (i == 0) ? "$" : "$final";
            } else if (c == '\\') {
                int j = i + 1;
                while (j < input.length() && Character.isLetter(input.charAt(j))) j++;
                String funcion = input.substring(i, j);
                i = j - 1;
                tipoEntrada = FUNCIONES_VALIDAS.contains(funcion) ? "funcion_valida" : "funcion_invalida";
            } else if (Character.isLetter(c)) {
                if ((estado == Estado.PESO || estado == Estado.LETRA) && i + 1 < input.length() && Character.isLetter(input.charAt(i + 1))) {
                    tipoEntrada = "invalido";
                } else {
                    tipoEntrada = "letra";
                }
            } else if (Character.isDigit(c)) {
                tipoEntrada = "digito";
            } else if ("^_{}=+-*/()., ".indexOf(c) != -1) {
                tipoEntrada = "simbolo";
            } else {
                tipoEntrada = "invalido";
            }

            if (debug) {
                System.out.printf("🡒 Estado actual: %-18s Entrada: %-12s → ", estado, tipoEntrada);
            }

            //Consulta de la matriz para transicion
            Map<String, Estado> posibles = transiciones.getOrDefault(estado, new HashMap<>());
            Estado siguiente = posibles.getOrDefault(tipoEntrada, posibles.get("cualquier"));

            if (siguiente == null) {
                if (debug) System.out.println("ERROR (sin transición)");
                return false;
            }

            estado = siguiente;
            if (debug) System.out.println(estado);
            i++;
        }

        return estado == Estado.FIN;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese expresiones LaTeX. Escriba 's' para salir.");

        while (true) {
            System.out.print("Expresión: ");
            String input = sc.nextLine().trim();
            if (input.equals("s")) break;

            boolean valido = esValido(input, true);
            System.out.println(valido ? "✔️ Cadena aceptada.\n" : "❌ Cadena rechazada.\n");
        }

        System.out.println("Programa finalizado.");
    }
}
