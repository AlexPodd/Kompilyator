package org.example.vscodeIntegration;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.eclipse.lsp4j.*;
import org.eclipse.lsp4j.jsonrpc.messages.Either;
import org.eclipse.lsp4j.jsonrpc.services.JsonSegment;
import org.eclipse.lsp4j.services.TextDocumentService;
import org.example.MyLangLexer;
import org.example.error.MyError;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@JsonSegment("textDocument")
public class RusTextDocumentService implements TextDocumentService{

    // variable - 1 function - 2 keyword - 0 comment - 6 string - 3 number - 4 operator - 5

    private final ArrayList<Integer> lspTokens;

    private static final List<String> KEYWORDS = List.of(
            "если", "иначе_если", "иначе", "тогда", "цикл", "вернуть", "стоп", "пропустить",
            "процедура", "конец_процедуры", "массив", "строка", "логическая", "целое",
            "вещественный", "ничего", "правда", "ложь", "вывод"
    );
    private RusLangClient client;

    private final RusLSPServer server;

    public RusTextDocumentService(RusLSPServer server) {
        lspTokens = new ArrayList<>();
        this.server = server;

    }




    private void fillToken(String text){
        server.getLexer().clearError();
        server.getParser().clearError();
        server.getVisitor().clearError();


        CharStream input = CharStreams.fromString(text);
        server.getLexer().setInputStream(input);
        CommonTokenStream tokens = new CommonTokenStream(server.getLexer());
        tokens.fill();
        lspTokens.clear();
        lspTokens.addAll(convertTokensToLSP(tokens.getTokens()));
        server.getParser().reset();

        server.getParser().setTokenStream(tokens);
        server.getVisitor().visit(server.getParser().program());

    }

        @Override
        public void didOpen(DidOpenTextDocumentParams params) {
            URI uri = URI.create(params.getTextDocument().getUri());
            server.getContextHashMap().put(uri,params.getTextDocument().getText());
            fillToken(server.getContextHashMap().get(uri));

            List<Diagnostic> diagnostics = new ArrayList<>();
            generateDiagnostic(diagnostics, server.getLexer().getErrors(), "MyLangLexer");
            generateDiagnostic(diagnostics, server.getParser().getErrors(), "MyLangParser");
            generateDiagnostic(diagnostics, server.getVisitor().getErrors(), "Semantic");


            client.publishDiagnostics(new PublishDiagnosticsParams(uri.toString(), diagnostics));

            client.documentIsOpen();
            client.showMessage(new MessageParams(MessageType.Info, "Файл открыт: " + params.getTextDocument().getUri()));
        }


        @Override
        public void didChange(DidChangeTextDocumentParams params) {
            URI uri = URI.create(params.getTextDocument().getUri());
            for (TextDocumentContentChangeEvent change : params.getContentChanges()) {
               server.getContextHashMap().put(uri, change.getText());
            }
            fillToken(server.getContextHashMap().get(uri));


            List<Diagnostic> diagnostics = new ArrayList<>();
            generateDiagnostic(diagnostics, server.getLexer().getErrors(), "MyLangLexer");
            generateDiagnostic(diagnostics, server.getParser().getErrors(), "MyLangParser");
            generateDiagnostic(diagnostics, server.getVisitor().getErrors(), "Semantic");


            client.publishDiagnostics(new PublishDiagnosticsParams(uri.toString(), diagnostics));


            client.documentIsChange();
        }
        private <T extends MyError> void generateDiagnostic(List<Diagnostic> diagnostics, List<T> errors, String source){
            for (MyError error : errors) {
                Diagnostic diagnostic = new Diagnostic(
                        new Range(new Position(error.getLine() - 1, error.getStartCharPosition()),
                                new Position(error.getLine() - 1, error.getEndCharPosition())),
                        error.getMessage(),
                        DiagnosticSeverity.Error,
                        source
                );
                diagnostics.add(diagnostic);
            }
        }


        @Override
        public void didClose(DidCloseTextDocumentParams params) {

            client.showMessage(new MessageParams(MessageType.Info, "Файл закрыт: " + params.getTextDocument().getUri()));
        }

        @Override
        public void didSave(DidSaveTextDocumentParams params) {
            client.showMessage(new MessageParams(MessageType.Info, "Файл сохранен: " + params.getTextDocument().getUri()));
        }


    public void setClient(RusLangClient client) {
        this.client = client;
    }




    @Override
    public CompletableFuture<Either<List<CompletionItem>, CompletionList>> completion(CompletionParams params) {
        List<CompletionItem> completions = new ArrayList<>();


        for (String keyword : KEYWORDS) {
            CompletionItem item = new CompletionItem();
            item.setLabel(keyword);
            completions.add(item);
        }
        for (String var: server.getVisitor().getAllDeclarated()){
            CompletionItem item = new CompletionItem();
            item.setLabel(var);
            completions.add(item);
        }

        return CompletableFuture.completedFuture(Either.forLeft(completions));
    }


    private int getLspTokenType(int MyLexerTokenType) {
        switch (MyLexerTokenType) {
            case MyLangLexer.IF, MyLangLexer.FOR, MyLangLexer.RETURN,
                    MyLangLexer.BREAK, MyLangLexer.CONTINUE, MyLangLexer.PROCEDURE_END,
                    MyLangLexer.PRINT, MyLangLexer.ELSE, MyLangLexer.ELSE_IF,
                    MyLangLexer.BOOLEAN, MyLangLexer.FLOAT, MyLangLexer.INTEGER,
                    MyLangLexer.STRING, MyLangLexer.ARRAY, MyLangLexer.NULL_LITERAL:
                return 0;
            case MyLangLexer.IDENTIFIER:
                return 1;
            case MyLangLexer.PROCEDURE:
                return 2;
            case MyLangLexer.STRING_LITERAL, MyLangLexer.CHAR_LITERAL, MyLangLexer.BLOCK_TEXT:
                return 3;
            case MyLangLexer.INTEGER_LITERAL, MyLangLexer.FLOAT_LITERAL, MyLangLexer.BOOLEAN_LITERAL:
                return 4;
            case MyLangLexer.ADD, MyLangLexer.SUBTRACT, MyLangLexer.MULTIPLY,
                    MyLangLexer.DIVIDE, MyLangLexer.MODULO, MyLangLexer.ASSIGN,
                    MyLangLexer.GREATER, MyLangLexer.LESS, MyLangLexer.NOT,
                    MyLangLexer.COLON, MyLangLexer.EQUAL, MyLangLexer.LESS_EQUAL,
                    MyLangLexer.GREATER_EQUAL, MyLangLexer.NOT_EQUAL,
                    MyLangLexer.AND, MyLangLexer.OR, MyLangLexer.ADD_ASSIGN,
                    MyLangLexer.SUBTRACT_ASSIGN, MyLangLexer.MULTIPLY_ASSIGN,
                    MyLangLexer.DIVIDE_ASSIGN, MyLangLexer.MODULO_ASSIGN:
                return 5;
            case MyLangLexer.COMMENT:
                return 6;
            default:
                return -1;
        }
    }
    private List<Integer> convertTokensToLSP(List<Token> tokens) {
        List<Integer> result = new ArrayList<>();
        int prevLine = 0, prevChar = 0;

        for (Token token : tokens) {
            int line = token.getLine() - 1;
            int charPosition = token.getCharPositionInLine();
            int length = token.getText().length();
            int tokenType = getLspTokenType(token.getType());
            int tokenModifiers = 0;

            if (tokenType == -1) continue;

            int deltaLine = line - prevLine;
            int deltaStart = (deltaLine == 0) ? (charPosition - prevChar) : charPosition;

            result.add(deltaLine);
            result.add(deltaStart);
            result.add(length);
            result.add(tokenType);
            result.add(tokenModifiers);

            prevLine = line;
            prevChar = charPosition;
        }

        return result;
    }



    @Override
    public CompletableFuture<SemanticTokens> semanticTokensFull(SemanticTokensParams params) {
        return CompletableFuture.completedFuture(new SemanticTokens(lspTokens));
    }
}
