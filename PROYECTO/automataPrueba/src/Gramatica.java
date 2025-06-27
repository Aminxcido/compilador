import java.util.*;

public class Gramatica {

    enum Estado {
        q0, q1, q2, q3, q4, q5, ACEPTADO, RECHAZADO
    }

    public static Estado verificar(String binario) {
        List<Estado> recorrido = new ArrayList<>();
        Estado estado = Estado.q0;
        recorrido.add(estado);

        int len = binario.length();
//        if (len < 1 || len > 5 || !binario.matches("[01]+")) {
//            System.out.println("Entrada inválida");
//            return Estado.RECHAZADO;
//        }

        for (int i = 0; i < len; i++) {
            estado = Estado.values()[estado.ordinal() + 1];
            recorrido.add(estado);
        }

        // Verificar si es palíndromo
        boolean esPalindromo = true;
        for (int i = 0; i < len / 2; i++) {
            if (binario.charAt(i) != binario.charAt(len - 1 - i)) {
                esPalindromo = false;
                break;
            }
        }

        if (esPalindromo) {
            recorrido.add(Estado.ACEPTADO);
            System.out.println("Palíndromo binario válido.");
        } else {
            recorrido.add(Estado.RECHAZADO);
            System.out.println("No es palíndromo binario.");
        }

        // Mostrar recorrido
        System.out.println("Recorrido de estados:");
        for (int i = 0; i < recorrido.size(); i++) {
            if (i < binario.length()) {
                System.out.printf(" %s --[%c]--> ", recorrido.get(i), binario.charAt(i));
            } else {
                System.out.println(" " + recorrido.get(i));
            }
        }

        return esPalindromo ? Estado.ACEPTADO : Estado.RECHAZADO;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese un número binario (1 a 5 dígitos):");
        String input = scanner.nextLine();
        verificar(input);
    }

    public static class gramatica {
    }
}
