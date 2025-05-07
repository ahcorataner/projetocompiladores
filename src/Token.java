public class Token {

    // Enumeração dos tipos de token
    public enum Type {
        NUMBER,  // Para números
        PLUS,    // Para o operador '+'
        MINUS,   // Para o operador '-'
        EOF      // Para o marcador de final de entrada
    }

    private final Type type;
    private final String value;

    // Construtor para criar um Token com tipo e valor
    public Token(Type type, String value) {
        this.type = type;
        this.value = value;
    }

    // Métodos getters
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
