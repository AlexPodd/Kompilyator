package org.example.vscodeIntegration;

import org.eclipse.lsp4j.*;
import org.eclipse.lsp4j.services.LanguageClient;
import org.eclipse.lsp4j.services.LanguageClientAware;
import org.eclipse.lsp4j.services.WorkspaceService;

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

    public void setClient(RusLangClient client) {
        this.client = client;
    }
}
