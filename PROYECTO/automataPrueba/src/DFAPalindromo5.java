import java.util.Scanner;

public class DFAPalindromo5 {

    public static boolean esPalindromoBinario5(String cadena) {
        // Validación de longitud y caracteres
        if (cadena.length() != 5 || !cadena.matches("[01]+")) {
            return false;
        }

        // Verificar palindromicidad
        return cadena.charAt(0) == cadena.charAt(4) &&
                cadena.charAt(1) == cadena.charAt(3);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese un número binario de 5 dígitos:");
        String input = scanner.nextLine();

        if (esPalindromoBinario5(input)) {
            System.out.println("Cadena aceptada: es un palíndromo binario de 5 dígitos.");
        } else {
            System.out.println("Cadena rechazada: no es un palíndromo binario válido de 5 dígitos.");
        }
    }
}
