import java.util.*;

public class Analizador_sintactico {

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

    enum TokenType {
        DELIMITADOR_INICIAL, DELIMITADOR_FINAL,
        IDENT, CONST,
        FUNCION, OPERADOR,
        SIMBOLO,
        EOF
    }

    static class Token {
        TokenType type;
        String lexema;

        Token(TokenType t, String l) {
            this.type = t;
            this.lexema = l;
        }

        @Override
        public String toString() {
            return type + "('" + lexema + "')";
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

        boolean isLetter(char c) {
            return Character.isLetter(c);
        }

        boolean isDigit(char c) {
            return Character.isDigit(c);
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

                String opStr = String.valueOf(c);
                if (OPERADORES.contains(opStr)) {
                    tokens.add(new Token(TokenType.OPERADOR, opStr));
                    pos++;
                    continue;
                }

                if (c == '\\') {
                    int start = pos;
                    pos++;
                    while (pos < length && isLetter(input.charAt(pos))) {
                        pos++;
                    }
                    String func = input.substring(start, pos);
                    if (FUNCIONES_VALIDAS.contains(func)) {
                        tokens.add(new Token(TokenType.FUNCION, func));
                    } else {
                        throw new RuntimeException("Función inválida: " + func);
                    }
                    continue;
                }

                if (isLetter(c)) {
                    tokens.add(new Token(TokenType.IDENT, Character.toString(c)));
                    pos++;
                    continue;
                }

                if (isDigit(c)) {
                    int start = pos;
                    while (pos < length && isDigit(input.charAt(pos))) {
                        pos++;
                    }
                    String num = input.substring(start, pos);
                    tokens.add(new Token(TokenType.CONST, num));
                    continue;
                }

                throw new RuntimeException("Carácter inválido en la entrada: " + c);
            }

            tokens.add(new Token(TokenType.EOF, "EOF"));
        }
    }

    static class Parser {
        List<Token> tokens;
        int index = 0;
        Stack<String> stack = new Stack<>();

        Parser(List<Token> tokens) {
            this.tokens = tokens;
        }

        Token lookahead() {
            return (index < tokens.size()) ? tokens.get(index) : new Token(TokenType.EOF, "EOF");
        }

        void match(TokenType expected) {
            Token actual = lookahead();
            if (actual.type == expected) {
                index++;
            } else {
                throw new RuntimeException("Error de sintaxis. Se esperaba " + expected + " pero se encontró " + actual.type);
            }
        }

        void parse() {
            stack.clear();
            stack.push("S");

            int paso = 1;

            while (!stack.isEmpty()) {
                String tope = stack.peek();
                Token actual = lookahead();

                System.out.printf("Paso %2d | Pila: %-50s | Entrada: %-50s | Acción: ", paso, stackToString(), tokensToStringFrom(index));

                if (isTerminal(tope)) {
                    if (terminalMatches(tope, actual)) {
                        System.out.println("Desapilar terminal '" + tope + "'");
                        stack.pop();
                        match(actual.type);
                    } else {
                        throw new RuntimeException("Error: terminal esperado '" + tope + "', encontrado '" + actual.lexema + "'");
                    }
                } else {
                    System.out.println("Expandir no terminal '" + tope + "'");
                    applyProduction(tope, actual);
                }
                paso++;
            }

            if (lookahead().type != TokenType.EOF) {
                throw new RuntimeException("Error: tokens sobrantes al finalizar análisis");
            }

            System.out.println("✔️ Cadena aceptada.");
        }

        boolean isTerminal(String simbolo) {
            return simbolo.equals("DELIMITADOR_INICIAL") || simbolo.equals("DELIMITADOR_FINAL") ||
                    simbolo.equals("IDENT") || simbolo.equals("CONST") ||
                    simbolo.equals("FUNCION_SIMBOLO") || simbolo.equals("OPERADOR") ||
                    simbolo.equals("(") || simbolo.equals(")") || simbolo.equals("SIMBOLO");
        }

        boolean terminalMatches(String simbolo, Token token) {
            switch (simbolo) {
                case "DELIMITADOR_INICIAL":
                    return token.type == TokenType.DELIMITADOR_INICIAL;
                case "DELIMITADOR_FINAL":
                    return token.type == TokenType.DELIMITADOR_FINAL;
                case "IDENT":
                    return token.type == TokenType.IDENT;
                case "CONST":
                    return token.type == TokenType.CONST;
                case "FUNCION_SIMBOLO":
                    return token.type == TokenType.FUNCION;
                case "OPERADOR":
                    return token.type == TokenType.OPERADOR;
                case "(":
                    return token.type == TokenType.SIMBOLO && token.lexema.equals("(");
                case ")":
                    return token.type == TokenType.SIMBOLO && token.lexema.equals(")");
                case "SIMBOLO":
                    return token.type == TokenType.SIMBOLO;
                default:
                    return false;
            }
        }

        void applyProduction(String noTerminal, Token token) {
            stack.pop();

            switch (noTerminal) {
                case "S":
                    if (token.type == TokenType.DELIMITADOR_INICIAL) {
                        stack.push("DELIMITADOR_FINAL");
                        stack.push("EXPRESION");
                        stack.push("DELIMITADOR_INICIAL");
                    } else {
                        errorExpected("DELIMITADOR_INICIAL");
                    }
                    break;

                case "EXPRESION":
                    if (firstElemento(token)) {
                        stack.push("EXPRESION_PRIMA");
                        stack.push("ELEMENTO");
                    } else {
                        errorExpected("ELEMENTO");
                    }
                    break;

                case "EXPRESION_PRIMA":
                    if (token.type == TokenType.OPERADOR) {
                        stack.push("EXPRESION_PRIMA");
                        stack.push("ELEMENTO");
                        stack.push("OPERADOR");
                    } else if (followExpresionPrima(token)) {
                        System.out.print("Aplicar ε (vacío)\n");
                    } else {
                        errorExpected("OPERADOR o fin de expresión");
                    }
                    break;

                case "ELEMENTO":
                    if (token.type == TokenType.FUNCION) {
                        stack.push(")");
                        stack.push("ARGUMENTOS");
                        stack.push("(");
                        stack.push("FUNCION_SIMBOLO");
                    } else if (token.type == TokenType.IDENT) {
                        stack.push("IDENT");
                    } else if (token.type == TokenType.CONST) {
                        stack.push("CONST");
                    } else if (token.type == TokenType.SIMBOLO && token.lexema.equals("(")) {
                        stack.push(")");
                        stack.push("EXPRESION");
                        stack.push("(");
                    } else {
                        errorExpected("FUNCION, IDENT, CONST o '('");
                    }
                    break;

                case "FUNCION_SIMBOLO":
                    if (token.type != TokenType.FUNCION) {
                        errorExpected("FUNCION");
                    }
                    break;

                case "ARGUMENTOS":
                    if (firstElemento(token)) {
                        stack.push("ARGUMENTOS_PRIMA");
                        stack.push("EXPRESION");
                    } else if (token.type == TokenType.SIMBOLO && token.lexema.equals(")")) {
                        System.out.print("Aplicar ε (vacío)\n");
                    } else {
                        errorExpected("EXPRESION o ')'");
                    }
                    break;

                case "ARGUMENTOS_PRIMA":
                    if (token.type == TokenType.SIMBOLO && token.lexema.equals(",")) {
                        stack.push("ARGUMENTOS");
                        stack.push("EXPRESION");
                        stack.push(",");
                    } else if (token.type == TokenType.SIMBOLO && token.lexema.equals(")")) {
                        System.out.print("Aplicar ε (vacío)\n");
                    } else {
                        errorExpected("',' o ')'");
                    }
                    break;

                case "OPERADOR":
                    if (token.type != TokenType.OPERADOR) {
                        errorExpected("OPERADOR");
                    }
                    break;

                default:
                    stack.push(noTerminal);
            }
        }

        boolean firstElemento(Token token) {
            return token.type == TokenType.FUNCION ||
                    token.type == TokenType.IDENT ||
                    token.type == TokenType.CONST ||
                    (token.type == TokenType.SIMBOLO && token.lexema.equals("("));
        }

        boolean followExpresionPrima(Token token) {
            return token.type == TokenType.DELIMITADOR_FINAL ||
                    (token.type == TokenType.SIMBOLO && (token.lexema.equals(")") || token.lexema.equals(",")));
        }

        void errorExpected(String esperado) {
            throw new RuntimeException("Error de sintaxis: se esperaba " + esperado);
        }

        String stackToString() {
            List<String> elems = new ArrayList<>(stack);
            Collections.reverse(elems);
            return elems.toString();
        }

        String tokensToStringFrom(int start) {
            StringBuilder sb = new StringBuilder();
            for (int i = start; i < tokens.size(); i++) {
                Token t = tokens.get(i);
                if (t.type == TokenType.EOF) break;
                sb.append(t.lexema).append(" ");
            }
            return sb.toString().trim();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese la cadena a analizar:");
        String entrada = sc.nextLine();

        if (!entrada.startsWith("$") || !entrada.endsWith("@")) {
            System.err.println("❌ Error: la cadena debe iniciar con '$' y terminar con '@'");
            return;
        }

        Lexer lexer = new Lexer(entrada);
        try {
            lexer.tokenize();
        } catch (RuntimeException e) {
            System.err.println("Error en tokenización: " + e.getMessage());
            return;
        }

        System.out.println("\nTokens generados:");
        for (int i = 0; i < lexer.tokens.size(); i++) {
            Token tk = lexer.tokens.get(i);
            if (tk.type == TokenType.EOF) break;
            System.out.printf("%d\t%s\t%s\n", i, tk.lexema, tk.type);
        }

        Parser parser = new Parser(lexer.tokens);
        try {
            parser.parse();
        } catch (RuntimeException e) {
            System.err.println("Error en parsing: " + e.getMessage());
        }
    }
}
