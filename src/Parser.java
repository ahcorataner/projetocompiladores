public class Parser {
    private final char[] input; // agora usando char[] para facilitar o manuseio
    private int current;        // índice atual

    public Parser(char[] input) {
        this.input = input;
    }

    public void parse() {
        expr(); // ponto de entrada da gramática
    }

    // expr → digit oper
    void expr() {
        digit();
        oper();
    }

    // digit → 0 | .. | 9
    void digit() {
        if (Character.isDigit(peek())) {
            System.out.println("push " + peek()); // imprime a ação de empilhar o dígito
            match(peek()); // consome o caractere
        } else {
            throw new IllegalArgumentException("syntax error: expected digit, found '" + peek() + "'");
        }
    }

    // oper → + digit oper | - digit oper | ε
    void oper() {
        if (peek() == '+') {
            match('+');
            digit();
            System.out.println("add");
            oper();
        } else if (peek() == '-') {
            match('-');
            digit();
            System.out.println("sub");
            oper();
        }
        // Se não for '+' ou '-', ε (vazio) — termina sem erro
    }

    // retorna o caractere atual sem consumir
    private char peek() {
        if (current < input.length)
            return input[current];
        return '\0'; // fim da expressão
    }

    // consome o caractere se ele for o esperado
    private void match(char c) {
        if (c == peek()) {
            current++;
        } else {
            throw new IllegalArgumentException("syntax error: expected '" + c + "', found '" + peek() + "'");
        }
    }
}

