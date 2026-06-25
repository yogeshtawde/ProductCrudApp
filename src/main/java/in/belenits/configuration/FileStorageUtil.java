package in.belenits.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;

@Component
public class FileStorageUtil {

    @Value("${file.upload.path}")
    private String uploadPath;

    public String saveFile(
            MultipartFile file,
            String folderName)
            throws IOException {

        Path folder =
                Paths.get(uploadPath, folderName);

        if (!Files.exists(folder)) {
            Files.createDirectories(folder);
        }

        String fileName =
                System.currentTimeMillis()
                        + "_"
                        + file.getOriginalFilename();

        Path filePath =
                folder.resolve(fileName);

        Files.copy(
                file.getInputStream(),
                filePath,
                StandardCopyOption.REPLACE_EXISTING);

        return filePath.toString();
    }
}