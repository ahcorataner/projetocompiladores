package tradutor;

public class Main {
    public static void main(String[] args) {
        // Uso correto de println (sem formatação)
        System.out.println("Mensagem simples com println");

        // Uso redundante de printf (evitável)
        System.out.printf("Mensagem simples com printf e \\n\n"); // Redundante

        // Uso correto de printf com formatação
        String operador = "+";
        int operando1 = 3;
        int operando2 = 5;
        int resultado = operando1 + operando2;

        System.out.printf("Expressão: %d %s %d = %d\n", operando1, operador, operando2, resultado);
    }
}


