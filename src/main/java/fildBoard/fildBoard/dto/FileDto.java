package fildBoard.fildBoard.dto;

import fildBoard.fildBoard.domain.File;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class FileDto {

    private UUID uuid;
    private String fileName;
    private String contentType;

    public FileDto(UUID uuid, String fileName, String contentType) {
        this.uuid = uuid;
        this.fileName = fileName;
        this.contentType = contentType;
        System.out.println(contentType);
    }

    //== dto -> entity ==//
    public File toEntity() {
        return File.builder()
                .uuid(uuid)
                .fileName(fileName)
                .contentType(contentType)
                .build();
    }
}
