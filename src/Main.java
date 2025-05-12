public class Main {
    public static void main(String[] args) {
        String code = "let x = 10;\nprint x;";
        Lexer lexer = new Lexer(code);

        Token token;
        while ((token = lexer.getNextToken()).getType() != Token.Type.EOF) {
            System.out.println(token);
        }
    }
}
