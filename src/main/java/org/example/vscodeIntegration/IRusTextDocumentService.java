package org.example.vscodeIntegration;

import org.eclipse.lsp4j.TextDocumentIdentifier;
import org.eclipse.lsp4j.jsonrpc.services.JsonRequest;

import java.util.concurrent.CompletableFuture;

public interface IRusTextDocumentService extends org.eclipse.lsp4j.services.TextDocumentService {
    @JsonRequest("textDocument/generateCode")
    CompletableFuture<String> generateCode(TextDocumentIdentifier document);
}