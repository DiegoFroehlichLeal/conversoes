package conversoes;

import java.util.Arrays;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;


//pratica para memoria muscular, copiado de Michel Rollan "The Algorithms/ JAVA"


public class QualquerBase {
    static final int BASE_MINIMA = 2;
    static final int BASE_MAXIMA = 36;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String n;
        int b1, b2;
        while (true) {
            try {
                System.out.print("Digite o numero que quer converter: ");
                n = in.next();
                System.out.print("Digite sua base (entre " + BASE_MINIMA + " e " + BASE_MAXIMA + "): ");
                b1 = in.nextInt();
                if (b1 > BASE_MAXIMA || b1 < BASE_MINIMA) {
                    System.out.println("Base inválida!");
                    continue;
                }
                if (!validaBase(n, b1)) {
                    System.out.println("O numero é invalido para esta base!");
                    continue;
                }
                System.out.print("Digite para qual base será convertida (entre " + BASE_MINIMA + " e " + BASE_MAXIMA + "): ");
                b2 = in.nextInt();
                if (b2 > BASE_MAXIMA || b2 < BASE_MINIMA) {
                    System.out.println("Base Invalida!");
                    continue;
                }
                break;

            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida!");
                in.next();

            }
        }
        System.out.println(BaseParaBase(n, b1, b2));
        in.close();
    }

    public static boolean validaBase(String n, int base) {
        char[] digitosValidos = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
                'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
                'U', 'V', 'W', 'X', 'Y', 'Z'
        };
        char[] digitosParaBase = Arrays.copyOfRange(digitosValidos, 0, base);
        HashSet<Character> listaDigitos = new HashSet<>();
        for (int i = 0; i < digitosParaBase.length; i++) listaDigitos.add(digitosParaBase[i]);
        for (char c : n.toCharArray()) if (!listaDigitos.contains(c)) return false;
        return true;


    }

    public static String BaseParaBase(String n, int b1, int b2) {
        int valorDecimal = 0, charB2;
        char charB1;
        String saida = "";

        for (int i = 0; i < n.length(); i++) {
            charB1 = n.charAt(i);
            if (charB1 >= 'A' && charB1 <= 'Z') charB2 = 10 + (charB1 - 'A');
            else charB2 = charB1 - '0';
            valorDecimal = valorDecimal * b1 + charB2;

        }
        if (0 == valorDecimal) return "0";
        while (valorDecimal != 0) {
            if (valorDecimal % b2 < 10) saida = Integer.toString(valorDecimal % b2) + saida;
            else saida = (char) ((valorDecimal % b2) + 55) + saida;
            valorDecimal /= b2;
        }
        return saida;
    }
}
