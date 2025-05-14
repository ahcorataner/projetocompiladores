public class Main {
    public static void main(String[] args) {

        String codigo = """
            PUSH 10
            PUSH 5
            ADD
            POP x
            PUSH x
            PUSH 2
            SUB
            PRINT
        """;

        Interpretador interpretador = new Interpretador(codigo);
        interpretador.run();
    }
}
