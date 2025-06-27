package org.xtext.example.mathlang.generator;

import java.awt.Desktop;
import java.io.File;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.AbstractGenerator;
import org.eclipse.xtext.generator.IFileSystemAccess2;
import org.eclipse.xtext.generator.IGeneratorContext;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.xtext.example.mathlang.mathLang.DelimitedExpr;
import org.xtext.example.mathlang.mathLang.Model;

@SuppressWarnings("all")
public class MathLangGenerator extends AbstractGenerator {
  @Override
  public void doGenerate(final Resource resource, final IFileSystemAccess2 fsa, final IGeneratorContext context) {
    try {
      EObject _head = IterableExtensions.<EObject>head(resource.getContents());
      final Model model = ((Model) _head);
      DelimitedExpr _head_1 = IterableExtensions.<DelimitedExpr>head(model.getExpressions());
      final DelimitedExpr expr = ((DelimitedExpr) _head_1);
      final String rawExpr = NodeModelUtils.getTokenText(NodeModelUtils.findActualNodeFor(expr.getExpr())).trim();
      final String latexExpr = (("$" + rawExpr) + "@");
      final int funciones = this.countMatches(rawExpr, "\\\\sin|\\\\cos|\\\\tan|\\\\log|\\\\ln|\\\\sqrt|\\\\arcsin|\\\\arccos|\\\\arctan");
      final int parentesis = this.countMatches(rawExpr, "\\(|\\)");
      final int score = (funciones + parentesis);
      String _xifexpression = null;
      if ((score <= 2)) {
        _xifexpression = "baja";
      } else {
        String _xifexpression_1 = null;
        if ((score <= 5)) {
          _xifexpression_1 = "media";
        } else {
          _xifexpression_1 = "alta";
        }
        _xifexpression = _xifexpression_1;
      }
      final String dificultad = _xifexpression;
      final boolean graficable = rawExpr.contains("x");
      String funcionJS = "";
      if (graficable) {
        funcionJS = rawExpr.replaceAll("\\\\?log", "Math.log10").replaceAll("\\\\?ln", "Math.log").replaceAll("\\\\?sqrt", "Math.sqrt").replaceAll("\\\\?sin", "Math.sin").replaceAll("\\\\?cos", "Math.cos").replaceAll("\\\\?tan", "Math.tan").replace("^", "**");
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("return ");
        _builder.append(funcionJS);
        _builder.append(";");
        funcionJS = _builder.toString();
      }
      final String outputPath = "D:\\SEMESTRE 1 2025\\COMPILACION\\reporte.html";
      final File file = new File(outputPath);
      final PrintWriter html = new PrintWriter(file);
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("<!DOCTYPE html>");
      _builder_1.newLine();
      _builder_1.append("<html lang=\"es\">");
      _builder_1.newLine();
      _builder_1.append("<head>");
      _builder_1.newLine();
      _builder_1.append("    ");
      _builder_1.append("<meta charset=\"UTF-8\">");
      _builder_1.newLine();
      _builder_1.append("    ");
      _builder_1.append("<title>Reporte MathLang</title>");
      _builder_1.newLine();
      _builder_1.append("    ");
      _builder_1.append("<script src=\"https://cdn.jsdelivr.net/npm/chart.js\"></script>");
      _builder_1.newLine();
      _builder_1.append("    ");
      _builder_1.append("<style>");
      _builder_1.newLine();
      _builder_1.append("        ");
      _builder_1.append("body { font-family: Arial; margin: 20px; background: #f9f9f9; color: #333; }");
      _builder_1.newLine();
      _builder_1.append("        ");
      _builder_1.append(".item { margin-bottom: 10px; font-size: 18px; }");
      _builder_1.newLine();
      _builder_1.append("        ");
      _builder_1.append(".label { font-weight: bold; color: #444; }");
      _builder_1.newLine();
      _builder_1.append("        ");
      _builder_1.append(".low { color: green; }");
      _builder_1.newLine();
      _builder_1.append("        ");
      _builder_1.append(".medium { color: orange; }");
      _builder_1.newLine();
      _builder_1.append("        ");
      _builder_1.append(".high { color: red; }");
      _builder_1.newLine();
      _builder_1.append("    ");
      _builder_1.append("</style>");
      _builder_1.newLine();
      _builder_1.append("</head>");
      _builder_1.newLine();
      _builder_1.append("<body>");
      _builder_1.newLine();
      _builder_1.append("    ");
      _builder_1.append("<h1>Reporte de Expresión Matemática</h1>");
      _builder_1.newLine();
      _builder_1.newLine();
      _builder_1.append("    ");
      _builder_1.append("<div class=\"item\">");
      _builder_1.newLine();
      _builder_1.append("        ");
      _builder_1.append("<span class=\"label\">Expresión LaTeX:</span>");
      _builder_1.newLine();
      _builder_1.append("        ");
      _builder_1.append("<div style=\"font-size:20px; margin-top:5px;\">");
      String _escapeHTML = this.escapeHTML(latexExpr);
      String _plus = (_builder_1.toString() + _escapeHTML);
      StringConcatenation _builder_2 = new StringConcatenation();
      _builder_2.append("</div>");
      _builder_2.newLine();
      _builder_2.append("    ");
      _builder_2.append("</div>");
      _builder_2.newLine();
      _builder_2.newLine();
      _builder_2.append("    ");
      _builder_2.append("<div class=\"item\">");
      _builder_2.newLine();
      _builder_2.append("        ");
      _builder_2.append("<span class=\"label\">¿Graficable?:</span> ");
      String _plus_1 = (_plus + _builder_2);
      String _xifexpression_2 = null;
      if (graficable) {
        _xifexpression_2 = "Sí";
      } else {
        _xifexpression_2 = "No";
      }
      String _plus_2 = (_plus_1 + _xifexpression_2);
      StringConcatenation _builder_3 = new StringConcatenation();
      _builder_3.append("</div>");
      _builder_3.newLine();
      _builder_3.newLine();
      _builder_3.append("<div class=\"item\">");
      _builder_3.newLine();
      _builder_3.append("    ");
      _builder_3.append("<span class=\"label\">Dificultad:</span> ");
      _builder_3.newLine();
      _builder_3.append("    ");
      _builder_3.append("<span class=\"");
      String _plus_3 = (_plus_2 + _builder_3);
      String _dificultadClass = this.dificultadClass(dificultad);
      String _plus_4 = (_plus_3 + _dificultadClass);
      StringConcatenation _builder_4 = new StringConcatenation();
      _builder_4.append("\">");
      String _plus_5 = (_plus_4 + _builder_4);
      String _plus_6 = (_plus_5 + dificultad);
      StringConcatenation _builder_5 = new StringConcatenation();
      _builder_5.append("</span>");
      _builder_5.newLine();
      _builder_5.append("    ");
      _builder_5.append("</div>");
      String _plus_7 = (_plus_6 + _builder_5);
      html.println(_plus_7);
      if (graficable) {
        StringConcatenation _builder_6 = new StringConcatenation();
        _builder_6.append("<h2>Gráfico de la función</h2>");
        _builder_6.newLine();
        _builder_6.append("<canvas id=\"grafico\" width=\"600\" height=\"400\"></canvas>");
        _builder_6.newLine();
        _builder_6.append("<script>");
        _builder_6.newLine();
        _builder_6.append("    ");
        _builder_6.append("const ctx = document.getElementById(\'grafico\').getContext(\'2d\');");
        _builder_6.newLine();
        _builder_6.append("    ");
        _builder_6.append("function f(x) { ");
        String _plus_8 = (_builder_6.toString() + funcionJS);
        StringConcatenation _builder_7 = new StringConcatenation();
        _builder_7.append(" ");
        _builder_7.append("}");
        _builder_7.newLine();
        _builder_7.append("        ");
        _builder_7.append("const data = [];");
        _builder_7.newLine();
        _builder_7.append("        ");
        _builder_7.append("for (let x = -10; x <= 10; x += 0.1) { data.push({x: x, y: f(x)}); }");
        _builder_7.newLine();
        _builder_7.append("        ");
        _builder_7.append("new Chart(ctx, {");
        _builder_7.newLine();
        _builder_7.append("            ");
        _builder_7.append("type: \'line\',");
        _builder_7.newLine();
        _builder_7.append("            ");
        _builder_7.append("data: { datasets: [{ label: \'f(x)\', data: data, borderColor: \'blue\', borderWidth: 2, fill: false }] },");
        _builder_7.newLine();
        _builder_7.append("            ");
        _builder_7.append("options: { scales: { x: { type: \'linear\', position: \'bottom\' } } }");
        _builder_7.newLine();
        _builder_7.append("        ");
        _builder_7.append("});");
        _builder_7.newLine();
        _builder_7.append("    ");
        _builder_7.append("</script>");
        String _plus_9 = (_plus_8 + _builder_7);
        html.println(_plus_9);
      }
      html.println("</body></html>");
      html.close();
      InputOutput.<String>println(("✅ HTML generado en: " + outputPath));
      try {
        Desktop.getDesktop().browse(file.toURI());
      } catch (final Throwable _t) {
        if (_t instanceof Exception) {
          final Exception e = (Exception)_t;
          String _message = e.getMessage();
          String _plus_10 = ("❌ No se pudo abrir el archivo automáticamente: " + _message);
          InputOutput.<String>println(_plus_10);
        } else {
          throw Exceptions.sneakyThrow(_t);
        }
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }

  public int countMatches(final String text, final String regex) {
    int _xblockexpression = (int) 0;
    {
      final Matcher matcher = Pattern.compile(regex).matcher(text);
      int count = 0;
      while (matcher.find()) {
        count++;
      }
      _xblockexpression = count;
    }
    return _xblockexpression;
  }

  public String escapeHTML(final String text) {
    return text.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;").replace("\'", "&apos;");
  }

  public String dificultadClass(final String dificultad) {
    String _switchResult = null;
    if (dificultad != null) {
      switch (dificultad) {
        case "baja":
          _switchResult = "low";
          break;
        case "media":
          _switchResult = "medium";
          break;
        default:
          _switchResult = "high";
          break;
      }
    } else {
      _switchResult = "high";
    }
    return _switchResult;
  }
}
