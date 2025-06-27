import java.io.*;
import java.util.regex.*;

public class GeneradorReporte {
    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println("Uso: java GeneradorReporte resultado.txt archivo.mt");
            return;
        }

        String resultadoFile = args[0]; // no lo usamos
        String mtFile = args[1];

        System.out.println("Leyendo archivo de entrada: " + mtFile);

        File file = new File(mtFile);
        if (!file.exists()) {
            System.out.println("ERROR: No se encontro el archivo " + mtFile);
            return;
        }

        String expresion = "";

        // Leer .mt → primera línea con $ ... @
        BufferedReader brMt = new BufferedReader(new FileReader(file));
        String linea;
        while ((linea = brMt.readLine()) != null) {
            linea = linea.trim();
            if (linea.startsWith("$") && linea.endsWith("@")) {
                expresion = linea;
                break;
            }
        }
        brMt.close();

        System.out.println("Expresion encontrada: " + expresion);

        // Determinar dificultad
        int funciones = countMatches(expresion, "\\\\sin|\\\\cos|\\\\tan|\\\\log|\\\\ln|\\\\sqrt|\\\\arcsin|\\\\arccos|\\\\arctan");
        int parentesis = countMatches(expresion, "\\(|\\)");
        int score = funciones + parentesis;
        String dificultad = (score <= 2) ? "baja" : (score <= 5) ? "media" : "alta";

        // Determinar si es graficable (contiene x)
        boolean graficable = expresion.contains("x");

        // Generar JS "función" → muy básico (mejorarlo en el futuro)
        String funcionJS = "";
        if (graficable) {
            // Elimina $ y @
            String exprLimpia = expresion.replace("$", "").replace("@", "").trim();
            // Reemplazos básicos
            exprLimpia = exprLimpia.replaceAll("\\\\log", "Math.log10");
            exprLimpia = exprLimpia.replaceAll("\\\\ln", "Math.log");
            exprLimpia = exprLimpia.replaceAll("\\\\sqrt", "Math.sqrt");
            exprLimpia = exprLimpia.replaceAll("\\\\sin", "Math.sin");
            exprLimpia = exprLimpia.replaceAll("\\\\cos", "Math.cos");
            exprLimpia = exprLimpia.replaceAll("\\\\tan", "Math.tan");
            exprLimpia = exprLimpia.replace("^", "**"); // operador potencia en JS
            funcionJS = "return " + exprLimpia.replace(";", "") + ";";
        }

        // Generar HTML bonito con gráfico
        PrintWriter html = new PrintWriter("resultado.html");
        html.println("<!DOCTYPE html>");
        html.println("<html lang=\"es\">");
        html.println("<head>");
        html.println("<meta charset=\"UTF-8\">");
        html.println("<title>Reporte de Expresión Matemática</title>");
        html.println("<script src=\"https://cdn.jsdelivr.net/npm/chart.js\"></script>");
        html.println("<style>");
        html.println("body { font-family: Arial; margin: 20px; background: #f9f9f9; color: #333; }");
        html.println(".item { margin-bottom: 10px; font-size: 18px; }");
        html.println(".label { font-weight: bold; color: #444; }");
        html.println(".low { color: green; }");
        html.println(".medium { color: orange; }");
        html.println(".high { color: red; }");
        html.println("</style>");
        html.println("</head>");
        html.println("<body>");
        html.println("<h1>Reporte de Expresión Matemática</h1>");

        html.println("<div class=\"item\">");
        html.println("<span class=\"label\">Expresión LaTeX:</span>");
        html.println("<div style=\"font-size:20px; margin-top:5px;\">" + escapeHTML(expresion) + "</div>");
        html.println("</div>");

        html.println("<div class=\"item\">");
        html.println("<span class=\"label\">¿Graficable?:</span> " + (graficable ? "Sí" : "No"));
        html.println("</div>");

        html.println("<div class=\"item\">");
        html.println("<span class=\"label\">Dificultad:</span> ");
        html.println("<span class=\"" + dificultadClass(dificultad) + "\">" + dificultad + "</span>");
        html.println("</div>");

        // Si es graficable → insertar canvas de Chart.js
        if (graficable) {
            html.println("<h2>Gráfico de la función</h2>");
            html.println("<canvas id=\"grafico\" width=\"600\" height=\"400\"></canvas>");
            html.println("<script>");
            html.println("const ctx = document.getElementById('grafico').getContext('2d');");
            html.println("function f(x) { " + funcionJS + " }");
            html.println("const data = [];");
            html.println("for (let x = -10; x <= 10; x += 0.1) { data.push({x: x, y: f(x)}); }");
            html.println("new Chart(ctx, {");
            html.println("    type: 'line',");
            html.println("    data: { datasets: [{ label: 'f(x)', data: data, borderColor: 'blue', borderWidth: 2, fill: false }] },");
            html.println("    options: { scales: { x: { type: 'linear', position: 'bottom' } } }");
            html.println("});");
            html.println("</script>");
        }

        html.println("</body>");
        html.println("</html>");
        html.close();

        System.out.println("✅ HTML generado.");
    }

    private static int countMatches(String text, String regex) {
        Matcher m = Pattern.compile(regex).matcher(text);
        int count = 0;
        while (m.find()) count++;
        return count;
    }

    private static String escapeHTML(String text) {
        return text.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&apos;");
    }

    private static String dificultadClass(String dificultad) {
        if (dificultad.equals("baja")) return "low";
        if (dificultad.equals("media")) return "medium";
        return "high";
    }
}
