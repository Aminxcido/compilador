package org.xtext.example.mathlang.generator
import org.eclipse.xtext.nodemodel.util.NodeModelUtils

import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.AbstractGenerator
import org.eclipse.xtext.generator.IGeneratorContext
import org.eclipse.xtext.generator.IFileSystemAccess2
import org.xtext.example.mathlang.mathLang.Model
import org.xtext.example.mathlang.mathLang.DelimitedExpr
import java.util.regex.Pattern
import java.io.File
import java.io.PrintWriter
import java.awt.Desktop
import java.net.URI

class MathLangGenerator extends AbstractGenerator {

    override void doGenerate(Resource resource, IFileSystemAccess2 fsa, IGeneratorContext context) {
        val model = resource.contents.head as Model
       // if (model.expressions.empty) return

        val expr = model.expressions.head as DelimitedExpr
		val rawExpr = NodeModelUtils.getTokenText(NodeModelUtils.findActualNodeFor(expr.expr)).trim
        val latexExpr = "$" + rawExpr + "@"

        val funciones = countMatches(rawExpr, "\\\\sin|\\\\cos|\\\\tan|\\\\log|\\\\ln|\\\\sqrt|\\\\arcsin|\\\\arccos|\\\\arctan")
        val parentesis = countMatches(rawExpr, "\\(|\\)")
        val score = funciones + parentesis
        val dificultad = if (score <= 2) "baja" else if (score <= 5) "media" else "alta"
        val graficable = rawExpr.contains("x")

        var funcionJS = ""
        if (graficable) {
            funcionJS = rawExpr
    .replaceAll("\\\\?log", "Math.log10")
    .replaceAll("\\\\?ln", "Math.log")
    .replaceAll("\\\\?sqrt", "Math.sqrt")
    .replaceAll("\\\\?sin", "Math.sin")
    .replaceAll("\\\\?cos", "Math.cos")
    .replaceAll("\\\\?tan", "Math.tan")

                .replace("^", "**")
            funcionJS = '''return «funcionJS»;'''
        }

        val outputPath = "D:\\SEMESTRE 1 2025\\COMPILACION\\reporte.html"
        val file = new File(outputPath)
        val html = new PrintWriter(file)

        html.println('''<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Reporte MathLang</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        body { font-family: Arial; margin: 20px; background: #f9f9f9; color: #333; }
        .item { margin-bottom: 10px; font-size: 18px; }
        .label { font-weight: bold; color: #444; }
        .low { color: green; }
        .medium { color: orange; }
        .high { color: red; }
    </style>
</head>
<body>
    <h1>Reporte de Expresión Matemática</h1>

    <div class="item">
        <span class="label">Expresión LaTeX:</span>
        <div style="font-size:20px; margin-top:5px;">''' + escapeHTML(latexExpr) + '''</div>
    </div>

    <div class="item">
        <span class="label">¿Graficable?:</span> ''' + (if (graficable) "Sí" else "No") + '''
    </div>

    <div class="item">
        <span class="label">Dificultad:</span> 
        <span class="''' + dificultadClass(dificultad) + '''">''' + dificultad + '''</span>
    </div>''')

        if (graficable) {
            html.println('''
    <h2>Gráfico de la función</h2>
    <canvas id="grafico" width="600" height="400"></canvas>
    <script>
        const ctx = document.getElementById('grafico').getContext('2d');
        function f(x) { ''' + funcionJS + ''' }
        const data = [];
        for (let x = -10; x <= 10; x += 0.1) { data.push({x: x, y: f(x)}); }
        new Chart(ctx, {
            type: 'line',
            data: { datasets: [{ label: 'f(x)', data: data, borderColor: 'blue', borderWidth: 2, fill: false }] },
            options: { scales: { x: { type: 'linear', position: 'bottom' } } }
        });
    </script>''')
        }

        html.println("</body></html>")
        html.close()

        println("✅ HTML generado en: " + outputPath)

        // Abrir archivo automáticamente
        try {
            Desktop.getDesktop().browse(file.toURI)
        } catch (Exception e) {
            println("❌ No se pudo abrir el archivo automáticamente: " + e.message)
        }
    }

    def countMatches(String text, String regex) {
        val matcher = Pattern.compile(regex).matcher(text)
        var count = 0
        while (matcher.find) count++
        count
    }

    def escapeHTML(String text) {
        text.replace("&", "&amp;")
            .replace("<", "&lt;")
            .replace(">", "&gt;")
            .replace("\"", "&quot;")
            .replace("'", "&apos;")
    }

    def dificultadClass(String dificultad) {
        switch dificultad {
            case "baja": "low"
            case "media": "medium"
            default: "high"
        }
    }
}
