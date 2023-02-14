package com.sparta.finalproject.common.file;

import com.sparta.finalproject.common.exception.BadRequestException;
import com.sparta.finalproject.user.entity.User;
import com.sparta.finalproject.user.repository.UserRepository;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class FileServiceImpl implements FileService {
    @Value("${file.path}")
    private String imageFolder;

    private final UserRepository userRepository;

    @Override
    @Transactional
    public void upload(MultipartFile file, User user) {
        //동일한 사진을 업로드 하였을 때 사진이 덮어씌워지는 것을 방지하기 위함
        UUID uuid = UUID.randomUUID();
        String imageFileName = uuid + "_" + file.getOriginalFilename();

        Path imageFilePath = Paths.get(imageFolder + imageFileName);

        try {
            Files.write(imageFilePath, file.getBytes());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public void updateProfileImage(MultipartFile file, User user) {

//        user.updateProfileImage(file);
//        userRepository.save(user);

    }

    @Override
    @Transactional
    public void deleteProfileImage(User user){

        userRepository.delete(user);
    }

}
