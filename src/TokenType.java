public enum TokenType {
    // Tipos de tokens para operadores
    PLUS,          // Para o operador '+'
    MINUS,         // Para o operador '-'
    EQ,            // Para o operador '='
    SEMICOLON,     // Para o ponto e vírgula ';'

    // Tipos de tokens para palavras reservadas
    PRINT,         // Para o comando 'print'
    LET,           // Para o comando 'let'

    // Tipos de tokens para valores
    NUMBER,        // Para números (ex: 42, 5)
    IDENT,         // Para identificadores (ex: a, b)

    // Para indicar o final da entrada
    EOF            // Fim do arquivo (end of file)
}
