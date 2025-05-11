public class Lexer {
    private final char[] input;
    private int currentPos = 0;

    public Lexer(String input) {
        this.input = input.toCharArray();
    }

    public Token getNextToken() {
        while (currentPos < input.length) {
            char currentChar = input[currentPos];

            if (Character.isDigit(currentChar)) {
                return number();
            }

            if (currentChar == '+') {
                currentPos++;
                return new Token(Token.Type.PLUS, "+");
            }

            if (currentChar == '-') {
                currentPos++;
                return new Token(Token.Type.MINUS, "-");
            }

            if (currentChar == '*') {
                currentPos++;
                return new Token(Token.Type.TIMES, "*");
            }

            if (currentChar == '/') {
                currentPos++;
                return new Token(Token.Type.DIVIDE, "/");
            }

            // Ignorar espaços em branco
            if (Character.isWhitespace(currentChar)) {
                currentPos++;
                continue;
            }

            // Se encontrar um caractere inesperado
            throw new RuntimeException("Erro léxico: caractere inválido '" + currentChar + "'");
        }

        return new Token(Token.Type.EOF, "");
    }

    private Token number() {
        StringBuilder numberValue = new StringBuilder();
        while (currentPos < input.length && Character.isDigit(input[currentPos])) {
            numberValue.append(input[currentPos]);
            currentPos++;
        }
        return new Token(Token.Type.NUMBER, numberValue.toString());
    }
}
