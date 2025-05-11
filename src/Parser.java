public class Parser {
    private final Lexer lexer;
    private Token currentToken;

    public Parser(String input) {
        this.lexer = new Lexer(input);
        this.currentToken = lexer.getNextToken();
    }

    public void parse() {
        try {
            expr();
            if (currentToken.getType() != Token.Type.EOF) {
                throw new RuntimeException("Erro de sintaxe: entrada extra após o fim da expressão");
            }
        } catch (RuntimeException e) {
            System.err.println("Erro de análise: " + e.getMessage());
        }
    }

    private void expr() {
        number();
        oper();
    }

    private void oper() {
        while (currentToken.getType() == Token.Type.PLUS ||
                currentToken.getType() == Token.Type.MINUS ||
                currentToken.getType() == Token.Type.TIMES ||
                currentToken.getType() == Token.Type.DIVIDE) {
            String operador = currentToken.getValue();
            advance(); // consome o operador
            number();  // espera outro número após o operador
            System.out.println(operador); // imprime o operador na sequência pós-fixada
        }
    }

    private void number() {
        if (currentToken.getType() == Token.Type.NUMBER) {
            System.out.println(currentToken.getValue()); // imprime o número
            advance(); // consome o número
        } else {
            throw new RuntimeException("Erro de sintaxe: esperado número, encontrado " + currentToken.getType());
        }
    }

    private void advance() {
        currentToken = lexer.getNextToken();
    }
}
