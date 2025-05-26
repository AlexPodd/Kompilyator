package org.example.vscodeIntegration;

import org.eclipse.lsp4j.*;
import org.eclipse.lsp4j.services.LanguageClient;
import org.eclipse.lsp4j.services.LanguageClientAware;
import org.eclipse.lsp4j.services.WorkspaceService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class RusWorkspaceService implements WorkspaceService {

    private RusLSPServer server;
    private RusLangClient client;

    public RusWorkspaceService(RusLSPServer server) {
        this.server = server;
    }

    @Override
    public void didChangeConfiguration(DidChangeConfigurationParams params) {
        client.showMessage(new MessageParams(MessageType.Info, "Конфигурация изменена!"));
    }

    @Override
    public void didChangeWatchedFiles(DidChangeWatchedFilesParams params) {
        for (FileEvent event : params.getChanges()) {
            String message = null;

            switch (event.getType()) {
                case Created -> {
                    message = "Файл создан: " + event.getUri();
                }
                case Changed -> {
                    message = "Файл изменён: " + event.getUri();
                }
                case Deleted -> {
                    server.getContextHashMap().remove(event.getUri());
                    message = "Файл удалён: " + event.getUri();
                }
            };
            client.showMessage(new MessageParams(MessageType.Info, message));
        }
    }

    @Override
    public CompletableFuture<Object> executeCommand(ExecuteCommandParams params) {
        if ("generateCode".equals(params.getCommand())) {
            TextDocumentIdentifier document = (TextDocumentIdentifier) params.getArguments().get(0);
            return server.getTextDocumentService().generateCode(document)
                    .thenCompose(code -> {
                        TextEdit edit = new TextEdit(
                                new Range(
                                        new Position(0, 0),
                                        new Position(Integer.MAX_VALUE, Integer.MAX_VALUE)
                                ),
                                code
                        );

                        WorkspaceEdit workspaceEdit = new WorkspaceEdit();
                        workspaceEdit.setChanges(Map.of(
                                document.getUri(),
                                List.of(edit)
                        ));

                        return client.applyEdit(new ApplyWorkspaceEditParams(workspaceEdit))
                                .thenApply(response -> {
                                    if (!response.isApplied()) {
                                        client.showMessage(new MessageParams(
                                                MessageType.Error,
                                                "Failed to apply generated code"
                                        ));
                                    }
                                    return null;
                                });
                    });
        }
        return CompletableFuture.completedFuture(null);
    }
    public void setClient(RusLangClient client) {
        this.client = client;
    }
}
