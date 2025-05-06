public class Main {
    public static void main(String[] args) {
        String input = "8+5-7+9"; // A entrada como string
        Parser p = new Parser(input.toCharArray()); // converte para char[]
        p.parse(); // chama o método parse para iniciar o processamento
    }
}
