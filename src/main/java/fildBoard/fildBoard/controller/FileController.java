package fildBoard.fildBoard.controller;

import fildBoard.fildBoard.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import fildBoard.fildBoard.dto.FileDto;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.MalformedInputException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class FileController {

    private final FileRepository fileRepository;

    //== 홈 ==//
    @GetMapping("/")
    public String home() {
        return "home";
    }

    //== 업로드 로직(테스트 해보기위해 서비스로직을 작성하지 않고 모두 컨트롤러에 넣었다. ==//
    @PostMapping("/upload")
    // 업로드하는 파일들을 MultipartFile 형태의 파라미터로 전달된다.
    public String upload(
            @RequestParam MultipartFile[] uploadfile,  //멀티파트 파일은 여러개 즉 다중 파일형태이다.
            Model model,
            FileDto fileDto)
            throws IllegalStateException, IOException {
        List<FileDto> list = new ArrayList<>();
        //다중 파일을 넣기 위해선 리스트가 필요하다.
        for (MultipartFile file : uploadfile) {
            if (!file.isEmpty()) {
                fileDto.setUuid(UUID.randomUUID());  //for문으로 dto에 저장해주고
                fileDto.setFileName(file.getOriginalFilename());
                fileDto.setContentType(file.getContentType());
                FileDto dto = new FileDto(UUID.randomUUID(),  //dto에 넣은 비슷한 방식으로 객체를 생성하고 파일을 넣어준다.
                        file.getOriginalFilename(),
                        file.getContentType());
                list.add(dto);  //dto 파일 객체를 리스트에 넣는다.
                File newFileName = new File(dto.getUuid() + "_" + dto.getFileName());
                fileRepository.save(fileDto.toEntity());  //db에 저장해준다.
                file.transferTo(newFileName);  //transferTo는 로컬 스토리지에 저장하는 것이다.
            }
        }
        model.addAttribute("files", list);  //해당 리스트를(다중파일) 뿌려준다.
        return "result";
    }

    //yml에 저장된 로컬 스토리지 파일위치이다.(저장위치)
    @Value("${spring.servlet.multipart.location}")
    String filePath;

    @GetMapping("/download")
    public ResponseEntity<Resource> download(@ModelAttribute FileDto dto) throws IOException {
        Path path = Paths.get(filePath + "/" + dto.getUuid() + "_" + dto.getFileName());
        String contentType = Files.probeContentType(path);
        // header를 통해서 다운로드 되는 파일의 정보를 설정한다.
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(ContentDisposition.builder("attachment")
                .filename(dto.getFileName(), StandardCharsets.UTF_8)
                .build());
        headers.add(HttpHeaders.CONTENT_TYPE, contentType);

        Resource resource = new InputStreamResource(Files.newInputStream(path));
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/image/{fileName}")  //fileName = file.uuid + _ + file.fileName
    public Resource showImage(@PathVariable String fileName) throws MalformedURLException {
        return new UrlResource("file:C:\\Temp\\upload\\" + fileName);
    }
}
