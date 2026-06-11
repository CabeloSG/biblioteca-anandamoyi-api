package br.com.biblioteca.anandamoyi.infra.storage;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class UploadImagemService {

    private static final String PASTA_UPLOAD = "uploads/livros";

    public String salvar(MultipartFile arquivo) {

        try {

            if (arquivo.isEmpty()) {
                throw new IllegalArgumentException("Arquivo vazio");
            }

            String nomeOriginal = arquivo.getOriginalFilename();

            String extensao = "";

            if (nomeOriginal != null && nomeOriginal.contains(".")) {
                extensao =
                        nomeOriginal.substring(
                                nomeOriginal.lastIndexOf(".")
                        );
            }

            String nomeArquivo =
                    UUID.randomUUID() + extensao;

            Path pasta =
                    Paths.get(PASTA_UPLOAD);

            if (!Files.exists(pasta)) {
                Files.createDirectories(pasta);
            }

            Path destino =
                    pasta.resolve(nomeArquivo);

            arquivo.transferTo(destino);

            return "/uploads/livros/" + nomeArquivo;

        } catch (IOException e) {
            throw new RuntimeException(
                    "Erro ao salvar imagem",
                    e
            );
        }
    }
}