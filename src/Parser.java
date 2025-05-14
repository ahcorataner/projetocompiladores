// Parser.java
public class Parser {
    private final Scanner scanner;
    private Token currentToken;

    public Parser(String input) {
        this.scanner = new Scanner(input);
        this.currentToken = scanner.nextToken();
    }

    private void match(TokenType expected) {
        if (currentToken.type == expected) {
            currentToken = scanner.nextToken();
        } else {
            throw new Error("Expected " + expected + " but found " + currentToken.type);
        }
    }

    private void expr() {
        term();
        while (currentToken.type == TokenType.PLUS || currentToken.type == TokenType.MINUS) {
            TokenType op = currentToken.type;
            match(op);
            term();
            System.out.println(op == TokenType.PLUS ? "add" : "sub");
        }
    }

    private void term() {
        if (currentToken.type == TokenType.NUMBER) {
            System.out.println("push " + currentToken.lexeme);
            match(TokenType.NUMBER);
        } else if (currentToken.type == TokenType.IDENT) {
            System.out.println("push " + currentToken.lexeme);
            match(TokenType.IDENT);
        } else {
            throw new Error("Expected number or identifier");
        }
    }

    private void letStatement() {
        match(TokenType.LET);
        String varName = currentToken.lexeme;
        match(TokenType.IDENT);
        match(TokenType.EQ);
        expr();
        System.out.println("pop " + varName);
        match(TokenType.SEMICOLON);
    }

    private void printStatement() {
        match(TokenType.PRINT);
        expr();
        System.out.println("print");
        match(TokenType.SEMICOLON);
    }

    private void statement() {
        if (currentToken.type == TokenType.LET) {
            letStatement();
        } else if (currentToken.type == TokenType.PRINT) {
            printStatement();
        } else {
            throw new Error("Unexpected statement");
        }
    }

    private void statements() {
        while (currentToken.type != TokenType.EOF) {
            statement();
        }
    }

    public void parse() {
        statements();
    }
}
