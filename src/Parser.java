import java.util.ArrayList;
import java.util.List;

public class Parser {

    private final Scanner scanner;
    private Token currentToken;
    private final List<String> output = new ArrayList<>();

    public Parser(byte[] input) {
        this.scanner = new Scanner(input);
        this.currentToken = scanner.nextToken();
    }

    public void parse() {
        while (currentToken.getType() != Token.Type.EOF) {
            if (match(Token.Type.LET)) {
                parseLet();
            } else if (match(Token.Type.PRINT)) {
                parsePrint();
            } else {
                throw new RuntimeException("Comando inválido: " + currentToken.getValue());
            }
        }
    }

    public String output() {
        return String.join(System.lineSeparator(), output);
    }

    private void parseLet() {
        String varName = consume(Token.Type.IDENTIFIER, "Esperado nome da variável").getValue();
        consume(Token.Type.EQUAL, "Esperado '=' após o nome da variável");

        parseExpression();

        output.add("pop " + varName);

        consume(Token.Type.SEMICOLON, "Esperado ';' ao final da instrução");
    }

    private void parsePrint() {
        parseExpression();
        output.add("print");
        consume(Token.Type.SEMICOLON, "Esperado ';' ao final do print");
    }

    private void parseExpression() {
        parseTerm();
        while (match(Token.Type.PLUS) || match(Token.Type.MINUS)) {
            Token operator = currentToken;
            advance();
            parseTerm();
            if (operator.getType() == Token.Type.PLUS) {
                output.add("add");
            } else {
                output.add("sub");
            }
        }
    }

    private void parseTerm() {
        if (match(Token.Type.NUMBER)) {
            output.add("push " + currentToken.getValue());
            advance();
        } else if (match(Token.Type.IDENTIFIER)) {
            output.add("push " + currentToken.getValue());
            advance();
        } else {
            throw new RuntimeException("Esperado número ou identificador, mas encontrei: " + currentToken.getValue());
        }
    }

    private boolean match(Token.Type type) {
        return currentToken.getType() == type;
    }

    private Token consume(Token.Type type, String errorMessage) {
        if (currentToken.getType() != type) {
            throw new RuntimeException(errorMessage + " Mas encontrei: " + currentToken.getValue());
        }
        Token token = currentToken;
        advance();
        return token;
    }

    private void advance() {
        currentToken = scanner.nextToken();
    }
}

