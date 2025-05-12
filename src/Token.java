public class Token {

    public enum Type {
        NUMBER,     // número
        PLUS,       // +
        MINUS,      // -
        LET,        // let
        IDENTIFIER, // variáveis
        EQUAL,      // =
        PRINT,      // print
        SEMICOLON,  // ;
        EOF         // fim da entrada
    }

    private final Type type;
    private final String value;

    public Token(Type type, String value) {
        this.type = type;
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Token{" +
                "type=" + type +
                ", value='" + value + '\'' +
                '}';
    }
}

