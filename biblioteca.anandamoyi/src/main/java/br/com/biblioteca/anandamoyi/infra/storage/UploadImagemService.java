package br.com.biblioteca.anandamoyi.infra.storage;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class UploadImagemService {

    private final Cloudinary cloudinary;

    public UploadImagemService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public String salvar(MultipartFile arquivo) {

        try {

            Map<?, ?> resultado = cloudinary.uploader().upload(
                    arquivo.getBytes(),
                    ObjectUtils.asMap(
                            "folder", "biblioteca/livros"
                    )
            );

            return resultado.get("secure_url").toString();

        } catch (Exception e) {

            e.printStackTrace();

            throw new RuntimeException(
                    e.getMessage(),
                    e
            );
        }
    }
}