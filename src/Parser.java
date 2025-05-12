public class Parser {
    private final Lexer lexer;
    private Token currentToken;

    public Parser(String input) {
        this.lexer = new Lexer(input);
        this.currentToken = lexer.getNextToken();
    }

    public void parse() {
        statements();
    }

    private void statements() {
        while (currentToken.getType() != Token.Type.EOF) {
            statement();
        }
    }

    private void statement() {
        if (currentToken.getType() == Token.Type.LET) {
            letStatement();
        } else if (currentToken.getType() == Token.Type.PRINT) {
            printStatement();
        } else {
            throw new RuntimeException("Erro de sintaxe: comando inesperado '" + currentToken.getValue() + "'");
        }
    }

    private void letStatement() {
        match(Token.Type.LET);
        String varName = currentToken.getValue();
        match(Token.Type.IDENTIFIER);
        match(Token.Type.EQUAL);
        expr();
        System.out.println("pop " + varName);
        match(Token.Type.SEMICOLON);
    }

    private void printStatement() {
        match(Token.Type.PRINT);
        expr();
        System.out.println("print");
        match(Token.Type.SEMICOLON);
    }

    private void expr() {
        term();
        while (currentToken.getType() == Token.Type.PLUS || currentToken.getType() == Token.Type.MINUS) {
            Token.Type op = currentToken.getType();
            advance();
            term();
            if (op == Token.Type.PLUS) {
                System.out.println("add");
            } else {
                System.out.println("sub");
            }
        }
    }

    private void term() {
        if (currentToken.getType() == Token.Type.NUMBER) {
            System.out.println("push " + currentToken.getValue());
            advance();
        } else if (currentToken.getType() == Token.Type.IDENTIFIER) {
            System.out.println("push " + currentToken.getValue());
            advance();
        } else {
            throw new RuntimeException("Erro de sintaxe: esperado número ou variável, encontrado " + currentToken.getType());
        }
    }

    private void match(Token.Type expected) {
        if (currentToken.getType() == expected) {
            advance();
        } else {
            throw new RuntimeException("Erro de sintaxe: esperado " + expected + ", encontrado " + currentToken.getType());
        }
    }

    private void advance() {
        currentToken = lexer.getNextToken();
    }
}

