package org.example.vscodeIntegration;
import org.eclipse.lsp4j.InitializeParams;
import org.eclipse.lsp4j.InitializeResult;
import org.eclipse.lsp4j.ServerCapabilities;
import org.eclipse.lsp4j.TextDocumentSyncKind;
import org.eclipse.lsp4j.services.LanguageClient;
import org.eclipse.lsp4j.services.LanguageClientAware;
import org.eclipse.lsp4j.services.LanguageServer;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.eclipse.lsp4j.*;
import org.eclipse.lsp4j.jsonrpc.Launcher;
import org.example.MyLangLexer;
import org.example.MyLangParser1;
import org.example.semantic.SemanticVisitor;
import org.example.semantic.SymbolTable;
import org.example.semantic.Types.TypeFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Future;

public class RusLSPServer implements LanguageServer, LanguageClientAware {
    private RusLangClient client;
    private RusTextDocumentService textDocumentService;
    private RusWorkspaceService workspaceService;
 private MyLangLexer lexer;
 private MyLangParser1 parser;
 private SemanticVisitor visitor;
    private HashMap<URI, String> contextHashMap;

    public RusLSPServer() {
        lexer = new MyLangLexer(null);
        parser = new MyLangParser1(null);
        TypeFactory factory = new TypeFactory();
        SymbolTable globalTable = new SymbolTable(null, "global", 0, true);
        visitor = new SemanticVisitor(factory, globalTable);

        contextHashMap = new HashMap<>();
        this.textDocumentService = new RusTextDocumentService(this);
        this.workspaceService = new RusWorkspaceService(this);
    }

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(9091)) {
            System.out.println("LSP Server is running on port 5007...");
            Socket clientSocket = serverSocket.accept();
            InputStream in = clientSocket.getInputStream();
            OutputStream out = clientSocket.getOutputStream();

            RusLSPServer server = new RusLSPServer();



            Launcher<RusLangClient> launcher = Launcher.createLauncher(server, RusLangClient.class, in, out);
            server.connect(launcher.getRemoteProxy());

            Future<Void> future  = launcher.startListening();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    @Override
    public CompletableFuture<InitializeResult> initialize(InitializeParams params) {
        ServerCapabilities capabilities = new ServerCapabilities();
        capabilities.setTextDocumentSync(TextDocumentSyncKind.Full);
        SemanticTokensLegend legend = new SemanticTokensLegend(
                List.of("keyword", "variable", "function", "string", "number", "operator", "comment"),
                List.of()
        );
        capabilities.setSemanticTokensProvider(new SemanticTokensWithRegistrationOptions(legend, true));
        capabilities.setCompletionProvider(new CompletionOptions());

        return CompletableFuture.supplyAsync(() -> new InitializeResult(capabilities));
    }

    @Override
    public CompletableFuture<Object> shutdown() {
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public void exit() {
        System.exit(0);
    }

    @Override
    public RusTextDocumentService getTextDocumentService() {
        return textDocumentService;
    }


    @Override
    public RusWorkspaceService getWorkspaceService() {
        return workspaceService;
    }


    public LanguageClient getClient() {
        return client;
    }

    public MyLangLexer getLexer() {
        return lexer;
    }


    public HashMap<URI, String> getContextHashMap() {
        return contextHashMap;
    }

    public MyLangParser1 getParser() {
        return parser;
    }

    public SemanticVisitor getVisitor() {
        return visitor;
    }

    @Override
    public void connect(LanguageClient languageClient) {
            this.client = (RusLangClient) languageClient;
            workspaceService.setClient(client);
            textDocumentService.setClient(client);
            client.showMessage(new MessageParams(MessageType.Info, "LSP Server connected!"));
    }
}