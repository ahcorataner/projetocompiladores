public class Main {
    public static void main(String[] args) {
        // Teste 1: Entrada válida
        String input = "8+5-7+9";
        System.out.println("Testando expressão válida: " + input);
        Parser p = new Parser(input);
        p.parse();

        // Teste 2: Entrada inválida com caractere inesperado
        String invalidInput = "8+5-7+9$";  // O caractere $ é inválido
        System.out.println("\nTestando expressão inválida: " + invalidInput);
        p = new Parser(invalidInput);
        p.parse();

        // Teste 3: Entrada inválida com sintaxe incorreta
        String invalidSyntaxInput = "8++5-7";  // O operador '+' está repetido
        System.out.println("\nTestando expressão com sintaxe incorreta: " + invalidSyntaxInput);
        p = new Parser(invalidSyntaxInput);
        p.parse();
    }
}

