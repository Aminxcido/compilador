import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AnalizadorLexico {

    enum Estado {
        INICIO, PESO, FUNCION_INICIO, FUNCION_INVALIDA, LETRA, DIGITO, SIMBOLO,
        TRIGFUNC, LOGFUNC, OTRASFUNC, SIMBFUNC, CONTENIDO, FIN, ERROR
    }

    static class Token {
        String lexema, tipo;
        int posicion;

        Token(String lexema, String tipo, int posicion) {
            this.lexema = lexema;
            this.tipo = tipo;
            this.posicion = posicion;
        }
    }

    static class ErrorLexico {
        String id, nombre, descripcion, valor, solucion;
        int posicion;

        ErrorLexico(String id, String nombre, String descripcion, String valor, int posicion, String solucion) {
            this.id = id;
            this.nombre = nombre;
            this.descripcion = descripcion;
            this.valor = valor;
            this.posicion = posicion;
            this.solucion = solucion;
        }
    }

    static final Set<String> FUNCIONES_VALIDAS = new HashSet<>(Arrays.asList(
            "\\sin", "\\cos", "\\tan", "\\arcsin", "\\arccos", "\\arctan", "\\cot", "\\csc", "\\sec", "\\sinh", "\\cosh", "\\tanh", "\\coth",
            "\\log", "\\ln", "\\lg",
            "\\frac", "\\cdot", "\\exp", "\\deg", "\\det", "\\dim", "\\gcd", "\\hom", "\\inf", "\\sup", "\\ker", "\\lim", "\\liminf", "\\limsup",
            "\\max", "\\min", "\\Pr", "\\to", "\\arg",
            "\\theta", "\\alpha", "\\beta", "\\gamma", "\\delta", "\\epsilon", "\\pi", "\\mu", "\\nu", "\\rho", "\\sigma", "\\phi", "\\psi", "\\omega"
    ));

    static Map<Estado, Map<String, Estado>> transiciones = new HashMap<>();

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

    private static void add(Estado from, String inputType, Estado to) {
        transiciones.putIfAbsent(from, new HashMap<>());
        transiciones.get(from).put(inputType, to);
    }

    public static void analizarLinea(String linea) {
        Estado estado = Estado.INICIO;
        List<Token> tablaSimbolos = new ArrayList<>();
        List<ErrorLexico> errores = new ArrayList<>();

        int i = 0;
        while (i < linea.length()) {
            String tipoEntrada = null;
            char c = linea.charAt(i);

            if (c == '$') {
                tipoEntrada = (i == 0) ? "$" : "$final";
                tablaSimbolos.add(new Token("$", i == 0 ? "DELIMITADOR_INI" : "DELIMITADOR_FIN", i));
            } else if (Character.isLetter(c)) {
                int j = i;
                while (j < linea.length() && Character.isLetter(linea.charAt(j))) j++;
                String lexema = linea.substring(i, j);
                boolean esFuncionEsperada = FUNCIONES_VALIDAS.contains("\\" + lexema);

                if (lexema.length() > 1) {
                    if (esFuncionEsperada) {
                        errores.add(new ErrorLexico("E3", "Falta barra inversa", "Una función debe comenzar con '\\'", lexema, i, "Agregue '\\' antes de la función"));
                    } else {
                        errores.add(new ErrorLexico("E2", "Variable inválida", "Una variable debe tener solo una letra", lexema, i, "Use una sola letra"));
                    }
                    tablaSimbolos.add(new Token(lexema, "ERROR", i));
                } else {
                    tipoEntrada = "letra";
                    tablaSimbolos.add(new Token(lexema, "IDENTIFICADOR", i));
                }
                i = j - 1;
            } else if (c == '\\') {
                int j = i + 1;
                while (j < linea.length() && Character.isLetter(linea.charAt(j))) j++;
                String funcion = linea.substring(i, j);
                boolean valida = FUNCIONES_VALIDAS.contains(funcion);
                tipoEntrada = valida ? "funcion_valida" : "funcion_invalida";
                tablaSimbolos.add(new Token(funcion, valida ? "FUNCION" : "FUNCION_INVALIDA", i));
                if (!valida) {
                    errores.add(new ErrorLexico("E4", "Función inválida", "La función no existe en el lenguaje", funcion, i, "Verifique el nombre de la función"));
                }
                i = j - 1;
            } else if (Character.isDigit(c)) {
                tipoEntrada = "digito";
                tablaSimbolos.add(new Token(String.valueOf(c), "CONST_ENTERA", i));
            } else if ("^_{}=+-*/()., ".indexOf(c) != -1) {
                tipoEntrada = "simbolo";
                tablaSimbolos.add(new Token(String.valueOf(c), "OPERADOR/SIMBOLO", i));
            } else {
                tipoEntrada = "invalido";
                errores.add(new ErrorLexico("E1", "Símbolo desconocido", "Carácter no reconocido por el lenguaje", String.valueOf(c), i, "Escriba un carácter válido"));
                tablaSimbolos.add(new Token(String.valueOf(c), "ERROR", i));
            }

            Map<String, Estado> posibles = transiciones.getOrDefault(estado, new HashMap<>());
            Estado siguiente = posibles.getOrDefault(tipoEntrada, posibles.get("cualquier"));

            if (siguiente == null) {
                errores.add(new ErrorLexico("E5", "Transición inválida", "No hay transición desde el estado actual", String.valueOf(c), i, "Verifique el orden de los símbolos"));
                estado = Estado.ERROR;
                break;
            }

            estado = siguiente;
            if (estado == Estado.ERROR) break;
            i++;
        }

        System.out.println("Expresión: " + linea);
        System.out.println("\nTabla de símbolos:");
        System.out.printf("%-4s | %-8s | %-22s | %-8s\n", "Nº", "Lexema", "Token", "Posición");
        System.out.println("-----|----------|------------------------|----------");
        int n = 1;
        for (Token t : tablaSimbolos) {
            System.out.printf("%-4d | %-8s | %-22s | %-8d\n", n++, t.lexema, t.tipo, t.posicion);
        }

        if (estado == Estado.FIN && errores.isEmpty()) {
            System.out.println("\n✔️ Cadena aceptada.\n");
        } else {
            System.out.println("\n❌ Cadena rechazada.");
            System.out.println("\nTabla de errores:");
            System.out.printf("%-4s | %-22s | %-28s | %-5s | %-4s | %-30s\n", "ID", "Nombre", "Descripcion", "Val", "Pos", "Solucion");
            System.out.println("-----|------------------------|------------------------------|------|------|------------------------------");
            for (ErrorLexico e : errores) {
                System.out.printf("%-4s | %-22s | %-28s | %-5s | %-4d | %-30s\n", e.id, e.nombre, e.descripcion, e.valor, e.posicion, e.solucion);
            }
        }

        System.out.println("\n----------------------------------------\n");
    }

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("src/expresiones.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                analizarLinea(linea.trim());
            }
        } catch (IOException e) {
            System.out.println("❌ Error al leer el archivo: " + e.getMessage());
        }
    }
}
