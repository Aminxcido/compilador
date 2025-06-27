import java.util.*;

public class mateamina {

    // === LÉXICO ===
    enum Estado {
        INICIO, PESO, FUNCION_INICIO, FUNCION_INVALIDA, LETRA, DIGITO, SIMBOLO,
        TRIGFUNC, LOGFUNC, OTRASFUNC, SIMBFUNC, CONTENIDO, FIN, ERROR
    }

    enum TokenType {
        DELIMITADOR_INICIAL, DELIMITADOR_FINAL,
        IDENT, CONST,
        FUNCION, OPERADOR,
        SIMBOLO,
        EOF
    }


    static final Set<String> FUNCIONES_VALIDAS = new HashSet<>(Arrays.asList(
            "\\sin", "\\cos", "\\tan", "\\arcsin", "\\arccos", "\\arctan", "\\cot", "\\csc", "\\sec",
            "\\sinh", "\\cosh", "\\tanh", "\\coth",
            "\\log", "\\ln", "\\lg",
            "\\frac", "\\cdot", "\\exp", "\\deg", "\\det", "\\dim", "\\gcd", "\\hom", "\\inf", "\\sup",
            "\\ker", "\\lim", "\\liminf", "\\limsup",
            "\\max", "\\min", "\\Pr", "\\to", "\\arg",
            "\\theta", "\\alpha", "\\beta", "\\gamma", "\\delta", "\\epsilon", "\\pi", "\\mu", "\\nu",
            "\\rho", "\\sigma", "\\phi", "\\psi", "\\omega"
    ));

    static final Set<String> OPERADORES = Set.of("+", "-", "*", "/", "^", "=");

    static Map<Estado, Map<String, Estado>> transiciones = new HashMap<>();

    static {
        add(Estado.INICIO, "$", Estado.PESO);
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

    static void add(Estado from, String inputType, Estado to) {
        transiciones.putIfAbsent(from, new HashMap<>());
        transiciones.get(from).put(inputType, to);
    }

    static boolean esValido(String input, boolean debug) {
        if (!input.startsWith("$") || !input.endsWith("@")) {
            if (debug) System.out.println("Error: la cadena debe iniciar con '$' y terminar con '@'");
            return false;
        }

        Estado estado = Estado.INICIO;
        int i = 0;
        while (i < input.length()) {
            String tipoEntrada;
            char c = input.charAt(i);

            if (i == 0 && c == '$') tipoEntrada = "$";
            else if (i == input.length() - 1 && c == '@') tipoEntrada = "$final";
            else if (c == '\\') {
                int j = i + 1;
                while (j < input.length() && Character.isLetter(input.charAt(j))) j++;
                String funcion = input.substring(i, j);
                i = j - 1;
                tipoEntrada = FUNCIONES_VALIDAS.contains(funcion) ? "funcion_valida" : "funcion_invalida";
            } else if (Character.isLetter(c)) {
                int j = i;
                while (j < input.length() && Character.isLetter(input.charAt(j))) j++;
                String posibleFuncion = "\\" + input.substring(i, j);
                if (FUNCIONES_VALIDAS.contains(posibleFuncion)) {
                    if (debug) {
                        System.out.println("Error: función válida escrita sin barra invertida: " + input.substring(i, j));
                    }
                    return false;
                }
                tipoEntrada = "letra";
            } else if (Character.isDigit(c)) tipoEntrada = "digito";
            else if ("^_{}=+-*/()., ".indexOf(c) != -1) tipoEntrada = "simbolo";
            else tipoEntrada = "invalido";

            if (debug) System.out.printf("-> Estado actual: %-18s Entrada: %-12s → ", estado, tipoEntrada);

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

    static class ErrorGeneral {
        String tipo; int linea; String descripcion; String sugerencia;
        public ErrorGeneral(String tipo, int linea, String descripcion, String sugerencia) {
            this.tipo = tipo; this.linea = linea; this.descripcion = descripcion; this.sugerencia = sugerencia;
        }
        public String toString() {
            return String.format("%-10s | %-6d | %-50s | %s", tipo, linea, descripcion, sugerencia);
        }
    }

    static List<ErrorGeneral> tablaErrores = new ArrayList<>();

    // === TOKENS ===
    static class Token {
        TokenType type;
        String lexema;

        Token(TokenType t, String l) {
            this.type = t;
            this.lexema = l;
        }
    }

    static class Lexer {
        String input;
        int pos = 0;
        int length;
        List<Token> tokens = new ArrayList<>();

        Lexer(String input) {
            this.input = input;
            this.length = input.length();
        }

        void tokenize() {
            while (pos < length) {
                char c = input.charAt(pos);
                if (Character.isWhitespace(c)) {
                    pos++;
                    continue;
                }

                if (c == '$') {
                    tokens.add(new Token(TokenType.DELIMITADOR_INICIAL, "$"));
                    pos++;
                    continue;
                }
                if (c == '@') {
                    tokens.add(new Token(TokenType.DELIMITADOR_FINAL, "@"));
                    pos++;
                    continue;
                }
                if ("(),{}".indexOf(c) != -1) {
                    tokens.add(new Token(TokenType.SIMBOLO, Character.toString(c)));
                    pos++;
                    continue;
                }

                if (OPERADORES.contains(Character.toString(c))) {
                    tokens.add(new Token(TokenType.OPERADOR, Character.toString(c)));
                    pos++;
                    continue;
                }

                if (c == '\\') {
                    int start = pos++;
                    while (pos < length && Character.isLetter(input.charAt(pos))) pos++;
                    String func = input.substring(start, pos);
                    if (!FUNCIONES_VALIDAS.contains(func)) throw new RuntimeException("Función inválida: " + func);
                    tokens.add(new Token(TokenType.FUNCION, func));
                    continue;
                }

                if (Character.isLetter(c)) {
                    tokens.add(new Token(TokenType.IDENT, Character.toString(c)));
                    pos++;
                    continue;
                }

                if (Character.isDigit(c)) {
                    int start = pos;
                    while (pos < length && Character.isDigit(input.charAt(pos))) pos++;
                    tokens.add(new Token(TokenType.CONST, input.substring(start, pos)));
                    continue;
                }

                throw new RuntimeException("Carácter inválido: " + c);
            }

            tokens.add(new Token(TokenType.EOF, "EOF"));
        }
    }

    // === PARSER ===
    static class Parser {
        List<Token> tokens;
        int index = 0;
        Stack<String> stack = new Stack<>();

        Parser(List<Token> tokens) {
            this.tokens = tokens;
        }
        String stackToString() {
            List<String> elems = new ArrayList<>(stack);
            Collections.reverse(elems);
            return String.join(" ", elems);
        }

        Token lookahead() {
            return index < tokens.size() ? tokens.get(index) : new Token(TokenType.EOF, "EOF");
        }

        void match(TokenType expected) {
            if (lookahead().type == expected) index++;
            else throw new RuntimeException("Se esperaba " + expected + " pero se encontró " + lookahead().type);
        }

        void parse() {
            stack.push("S");
            int paso = 1;

            while (!stack.isEmpty()) {
                String tope = stack.peek();
                Token actual = lookahead();
                if (paso == 1) {
                    System.out.println("Paso | Pila                                     | Entrada");
                    System.out.println("-----+------------------------------------------+------------------------------------------");
                }
                System.out.printf("%4d | %-40s | %-40s\n", paso, stackToString(), tokensDesde(index));

                if (isTerminal(tope)) {
                    if (terminalMatches(tope, actual)) {
                        stack.pop();
                        match(actual.type);
                    } else {
                        throw new RuntimeException("Error: esperado '" + tope + "', encontrado '" + actual.lexema + "'");
                    }
                } else {
                    applyProduction(tope, actual);
                }
                paso++;
            }

            if (lookahead().type != TokenType.EOF) {
                throw new RuntimeException("Error: tokens sobrantes tras análisis");
            }

            System.out.println("Cadena aceptada.");
        }

        boolean isTerminal(String simbolo) {
            return simbolo.equals("DELIMITADOR_INICIAL") || simbolo.equals("DELIMITADOR_FINAL") ||
                    simbolo.equals("IDENT") || simbolo.equals("CONST") ||
                    simbolo.equals("FUNCION_SIMBOLO") || simbolo.equals("OPERADOR") ||
                    simbolo.equals("(") || simbolo.equals(")") || simbolo.equals("SIMBOLO");
        }

        boolean terminalMatches(String simbolo, Token token) {
            return switch (simbolo) {
                case "DELIMITADOR_INICIAL" -> token.type == TokenType.DELIMITADOR_INICIAL;
                case "DELIMITADOR_FINAL" -> token.type == TokenType.DELIMITADOR_FINAL;
                case "IDENT" -> token.type == TokenType.IDENT;
                case "CONST" -> token.type == TokenType.CONST;
                case "FUNCION_SIMBOLO" -> token.type == TokenType.FUNCION;
                case "OPERADOR" -> token.type == TokenType.OPERADOR;
                case "(" -> token.type == TokenType.SIMBOLO && token.lexema.equals("(");
                case ")" -> token.type == TokenType.SIMBOLO && token.lexema.equals(")");
                case "SIMBOLO" -> token.type == TokenType.SIMBOLO;
                default -> false;
            };
        }

        void applyProduction(String nt, Token token) {
            stack.pop();
            switch (nt) {
                case "S" -> {
                    if (token.type == TokenType.DELIMITADOR_INICIAL)
                        stack.push("DELIMITADOR_FINAL");
                    stack.push("EXPRESION");
                    stack.push("DELIMITADOR_INICIAL");
                }
                case "EXPRESION" -> {
                    if (firstElemento(token)) stack.push("EXPRESION_PRIMA");
                    stack.push("ELEMENTO");
                }
                case "EXPRESION_PRIMA" -> {
                    if (token.type == TokenType.OPERADOR) {
                        stack.push("EXPRESION_PRIMA");
                        stack.push("ELEMENTO");
                        stack.push("OPERADOR");
                    } else if (followExpresionPrima(token)) System.out.print("Aplicar ε (vacío)\n");
                    else throw new RuntimeException("Error en EXPRESION_PRIMA");
                }
                case "ELEMENTO" -> {
                    if (token.type == TokenType.FUNCION) {
                        stack.push(")");
                        stack.push("ARGUMENTOS");
                        stack.push("(");
                        stack.push("FUNCION_SIMBOLO");
                    } else if (token.type == TokenType.IDENT) stack.push("IDENT");
                    else if (token.type == TokenType.CONST) stack.push("CONST");
                    else if (token.type == TokenType.SIMBOLO && token.lexema.equals("(")) {
                        stack.push(")");
                        stack.push("EXPRESION");
                        stack.push("(");
                    } else throw new RuntimeException("Elemento inválido");
                }
                case "FUNCION_SIMBOLO" -> {
                }
                case "ARGUMENTOS" -> {
                    if (firstElemento(token)) {
                        stack.push("ARGUMENTOS_PRIMA");
                        stack.push("EXPRESION");
                    } else if (token.lexema.equals(")")) System.out.print("Aplicar ε (vacío)\n");
                }
                case "ARGUMENTOS_PRIMA" -> {
                    if (token.lexema.equals(",")) {
                        stack.push("ARGUMENTOS");
                        stack.push("EXPRESION");
                        stack.push(",");
                    } else if (token.lexema.equals(")")) System.out.print("Aplicar ε (vacío)\n");
                }
                case "OPERADOR" -> stack.push("OPERADOR");
                default -> throw new RuntimeException("No se reconoce: " + nt);
            }
        }

        boolean firstElemento(Token t) {
            return t.type == TokenType.FUNCION || t.type == TokenType.IDENT ||
                    t.type == TokenType.CONST || (t.type == TokenType.SIMBOLO && t.lexema.equals("("));
        }

        boolean followExpresionPrima(Token t) {
            return t.type == TokenType.DELIMITADOR_FINAL ||
                    (t.type == TokenType.SIMBOLO && (t.lexema.equals(")") || t.lexema.equals(",")));
        }

        String tokensDesde(int i) {
            StringBuilder sb = new StringBuilder();
            for (; i < tokens.size(); i++) {
                Token t = tokens.get(i);
                if (t.type == TokenType.EOF) break;
                sb.append(t.lexema).append(" ");
            }
            return sb.toString().trim();
        }
    }
    // === ANÁLISIS SEMÁNTICO ===
    // === ANALIZADOR SEMÁNTICO ===
    static class AnalizadorSemantico {

        List<Token> tokens;
        Set<String> variablesDefinidas = new HashSet<>();
        List<String> errores = new ArrayList<>();

        AnalizadorSemantico(List<Token> tokens) {
            this.tokens = tokens;
        }

        void analizar() {
            Stack<String> parenStack = new Stack<>();
            boolean esperandoAsignacion = false;
            String variableActual = null;

            for (int i = 0; i < tokens.size(); i++) {
                Token t = tokens.get(i);

                // Balanceo de paréntesis y corchetes
                if (t.type == TokenType.SIMBOLO) {
                    if (t.lexema.equals("(") || t.lexema.equals("{")) {
                        parenStack.push(t.lexema);
                    } else if (t.lexema.equals(")")) {
                        if (parenStack.isEmpty() || !parenStack.peek().equals("(")) {
                            errores.add("Paréntesis ')' sin apertura correspondiente en posición " + i);
                        } else parenStack.pop();
                    } else if (t.lexema.equals("}")) {
                        if (parenStack.isEmpty() || !parenStack.peek().equals("{")) {
                            errores.add("Corchete '}' sin apertura correspondiente en posición " + i);
                        } else parenStack.pop();
                    }
                }

                // Variables: detectar asignación x = ... y marcar x como definida
                if (t.type == TokenType.IDENT) {
                    if (i + 1 < tokens.size() && tokens.get(i + 1).type == TokenType.OPERADOR && tokens.get(i + 1).lexema.equals("=")) {
                        variableActual = t.lexema;
                        esperandoAsignacion = true;
                        i++; // saltar '='
                    }
                    // Si no es asignación, no hago nada (ya no valido si está inicializada)
                }


                // Cuando terminamos la asignación
                if (esperandoAsignacion && t.type != TokenType.OPERADOR && t.type != TokenType.SIMBOLO && t.type != TokenType.DELIMITADOR_FINAL) {
                    // ya vimos el primer token del lado derecho de la asignación
                    variablesDefinidas.add(variableActual);
                    esperandoAsignacion = false;
                    variableActual = null;
                }
            }

            // Si quedan paréntesis o corchetes abiertos
            while (!parenStack.isEmpty()) {
                String abierto = parenStack.pop();
                errores.add("Falta cerrar " + (abierto.equals("(") ? "paréntesis" : "corchete"));
            }

            // Mostrar resultados
            System.out.println("\nANÁLISIS SEMÁNTICO:");
            if (errores.isEmpty()) {
                System.out.println("✔ No se encontraron errores semánticos.");
            } else {
                for (String err : errores) {
                    System.out.println("✘ " + err);
                }
            }
            for (String err : errores) {
                tablaErrores.add(new ErrorGeneral("Semántico", -1, err, "Revise la expresión"));
            }
        }

        boolean isNumeric(Token t) {
            return t.type == TokenType.CONST;
        }

        boolean isIdentOrConst(Token t) {
            return t.type == TokenType.CONST || t.type == TokenType.IDENT;
        }
    }

    // === MAIN ===
    public static void main(String[] args) {
        if (args.length == 0) {
            return;
        }

        String archivo = args[0];
        StringBuilder entrada = new StringBuilder();

        try (Scanner fileScanner = new Scanner(new java.io.File(archivo))) {
            while (fileScanner.hasNextLine()) {
                entrada.append(fileScanner.nextLine().trim()).append(" ");
            }
        } catch (Exception e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return;
        }

        String expresion = entrada.toString().trim();
        System.out.println("Expresión leída del archivo:\n" + expresion + "\n");

        if (!esValido(expresion, true)) {
            System.out.println("Cadena rechazada por el AFD léxico.");
            return;
        }

        Lexer lexer = new Lexer(expresion);
        try {
            lexer.tokenize();
        } catch (RuntimeException e) {
            tablaErrores.add(new ErrorGeneral("Léxico", -1, e.getMessage(), "Corrija el token inválido"));
            System.err.println("Error en tokenización: " + e.getMessage());
        }

        System.out.println("\nTokens generados:");
        for (int i = 0; i < lexer.tokens.size(); i++) {
            Token tk = lexer.tokens.get(i);
            if (tk.type == TokenType.EOF) break;
            System.out.printf("%2d: %-10s %-15s\n", i, tk.lexema, tk.type);
        }

        System.out.println("\nAnálisis sintáctico:");
        Parser parser = new Parser(lexer.tokens);
        try {
            try {
                parser.parse();
            } catch (RuntimeException e) {
                tablaErrores.add(new ErrorGeneral("Sintáctico", -1, e.getMessage(), "Revise la estructura de la expresión"));
                System.err.println("Error en parsing: " + e.getMessage());
            }
        } catch (RuntimeException e) {
            System.err.println("Error en parsing: " + e.getMessage());
        }
        System.out.println("\nAnálisis semántico:");
        AnalizadorSemantico semantico = new AnalizadorSemantico(lexer.tokens);
        semantico.analizar();

        System.out.println("\n--- Tabla General de Errores ---");
        if (tablaErrores.isEmpty()) {
            System.out.println("✔ No se encontraron errores.");
        } else {
            System.out.printf("%-10s | %-6s | %-50s | %s\n", "Tipo", "Línea", "Descripción", "Sugerencia");
            for (ErrorGeneral e : tablaErrores) {
                System.out.println(e);
            }
        }


        System.out.println("\nAnálisis completado.");
    }
}