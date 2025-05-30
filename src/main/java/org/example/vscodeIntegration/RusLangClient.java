    package org.example.vscodeIntegration;

    import org.eclipse.lsp4j.*;
    import org.eclipse.lsp4j.jsonrpc.services.JsonNotification;
    import org.eclipse.lsp4j.jsonrpc.services.JsonRequest;
    import org.eclipse.lsp4j.jsonrpc.validation.NonNull;
    import org.eclipse.lsp4j.services.LanguageClient;

    import java.util.List;
    import java.util.concurrent.CompletableFuture;

    public interface RusLangClient extends LanguageClient {
        @JsonNotification(value = "textDocument/didChange")
        void documentIsChange();

        @JsonNotification(value = "textDocument/didOpen")
        void documentIsOpen();

        @JsonRequest("textDocument/generateCode")
        CompletableFuture<String> generateCode(@NonNull TextDocumentIdentifier document);
    }
