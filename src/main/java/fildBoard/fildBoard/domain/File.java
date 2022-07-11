package fildBoard.fildBoard.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class File {

    /*
    UUID를 사용하면 MySql사용시 에러가 발생하고
    조회가 되지 않는 문제가 발생한다.
    generatedvalue의 generator 설정과
    genericgenerator 설정
    컬럼의 바이너리 설정으로 오류를 해결할 수 있다.
    더 정확히 말하면 mysql은 기본타입이 255이고
    UUID는 바이너리 16을 사용하기 때문에 매핑을 시켜주어야만 정상 작동하는 것이다.
     */
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID uuid;

    @Column
    private String fileName;

    @Column
    private String contentType;

    @Builder
    public File(UUID uuid, String fileName, String contentType) {
        this.uuid = uuid;
        this.fileName = fileName;
        this.contentType = contentType;
    }
}
