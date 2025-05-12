public class Scanner {

    private final byte[] input;
    private int current;

    public Scanner(byte[] input) {
        this.input = input;
        this.current = 0;
    }

    public Token nextToken() {
        skipWhitespace();

        if (isAtEnd()) {
            return new Token(Token.Type.EOF, "");
        }

        char c = (char) input[current];

        // Números
        if (Character.isDigit(c)) {
            return number();
        }

        // Identificadores ou palavras-chave
        if (Character.isLetter(c)) {
            return identifierOrKeyword();
        }

        // Símbolos únicos (usando o novo switch)
        return switch (c) {
            case '+' -> new Token(Token.Type.PLUS, "+");
            case '-' -> new Token(Token.Type.MINUS, "-");
            case '=' -> new Token(Token.Type.EQUAL, "=");
            case ';' -> new Token(Token.Type.SEMICOLON, ";");
            default -> throw new RuntimeException("Caractere inesperado: " + c);
        };
    }

    private Token number() {
        return new Token(Token.Type.NUMBER, consumeWhile(Character::isDigit));
    }

    private Token identifierOrKeyword() {
        String value = consumeWhile(Character::isLetter);
        return switch (value) {
            case "let" -> new Token(Token.Type.LET, value);
            case "print" -> new Token(Token.Type.PRINT, value);
            default -> new Token(Token.Type.IDENTIFIER, value);
        };
    }

    private String consumeWhile(java.util.function.Predicate<Character> condition) {
        StringBuilder sb = new StringBuilder();
        while (!isAtEnd() && condition.test((char) input[current])) {
            sb.append((char) input[current]);
            current++;
        }
        return sb.toString();
    }

    private void skipWhitespace() {
        while (!isAtEnd() && Character.isWhitespace((char) input[current])) {
            current++;
        }
    }

    private boolean isAtEnd() {
        return current >= input.length;
    }
}


