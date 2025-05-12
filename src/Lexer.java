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

            if (Character.isLetter(currentChar)) {
                return identifier();
            }

            if (currentChar == '+') {
                currentPos++;
                return new Token(Token.Type.PLUS, "+");
            }

            if (currentChar == '-') {
                currentPos++;
                return new Token(Token.Type.MINUS, "-");
            }

            if (currentChar == '=') {
                currentPos++;
                return new Token(Token.Type.EQUAL, "=");
            }

            if (currentChar == ';') {
                currentPos++;
                return new Token(Token.Type.SEMICOLON, ";");
            }

            if (Character.isWhitespace(currentChar)) {
                currentPos++;
                continue;
            }

            throw new RuntimeException("Erro léxico: caractere inválido '" + currentChar + "'");
        }

        return new Token(Token.Type.EOF, "");
    }

    private Token number() {
        StringBuilder value = new StringBuilder();
        while (currentPos < input.length && Character.isDigit(input[currentPos])) {
            value.append(input[currentPos++]);
        }
        return new Token(Token.Type.NUMBER, value.toString());
    }

    private Token identifier() {
        StringBuilder value = new StringBuilder();
        while (currentPos < input.length && Character.isLetterOrDigit(input[currentPos])) {
            value.append(input[currentPos++]);
        }
        String word = value.toString();
        return switch (word) {
            case "let" -> new Token(Token.Type.LET, word);
            case "print" -> new Token(Token.Type.PRINT, word);
            default -> new Token(Token.Type.IDENTIFIER, word);
        };
    }
}
