package fildBoard.fildBoard.repository;

import fildBoard.fildBoard.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FileRepository extends JpaRepository<File, UUID> {
    /*
    jpa repository 또한 조회 타입을 UUID로 주었다.(당연한일 ㅋㅋ)
     */
}
