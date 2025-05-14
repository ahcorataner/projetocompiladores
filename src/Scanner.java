// Scanner.java
public class Scanner {
    private final String input;
    private int currentIndex = 0;

    public Scanner(String input) {
        this.input = input;
    }

    public Token nextToken() {
        while (currentIndex < input.length() && Character.isWhitespace(input.charAt(currentIndex))) {
            currentIndex++;  // Ignora espaços em branco
        }

        if (currentIndex >= input.length()) {
            return new Token(TokenType.EOF, ""); // Fim do arquivo
        }

        char currentChar = input.charAt(currentIndex);

        // Identificadores e números
        if (Character.isLetter(currentChar)) {
            StringBuilder lexeme = new StringBuilder();
            while (currentIndex < input.length() && Character.isLetterOrDigit(input.charAt(currentIndex))) {
                lexeme.append(input.charAt(currentIndex));
                currentIndex++;
            }
            String lexemeStr = lexeme.toString();
            if (lexemeStr.equals("print")) {
                return new Token(TokenType.PRINT, lexemeStr);
            } else if (lexemeStr.equals("let")) {
                return new Token(TokenType.LET, lexemeStr);
            } else {
                return new Token(TokenType.IDENT, lexemeStr);
            }
        }

        // Números
        if (Character.isDigit(currentChar)) {
            StringBuilder lexeme = new StringBuilder();
            while (currentIndex < input.length() && Character.isDigit(input.charAt(currentIndex))) {
                lexeme.append(input.charAt(currentIndex));
                currentIndex++;
            }
            return new Token(TokenType.NUMBER, lexeme.toString());
        }

        // Operadores e outros caracteres
        switch (currentChar) {
            case '+':
                currentIndex++;
                return new Token(TokenType.PLUS, "+");
            case '-':
                currentIndex++;
                return new Token(TokenType.MINUS, "-");
            case '=':
                currentIndex++;
                return new Token(TokenType.EQ, "=");
            case ';':
                currentIndex++;
                return new Token(TokenType.SEMICOLON, ";");
            default:
                throw new Error("Unexpected character: " + currentChar);
        }
    }
}
