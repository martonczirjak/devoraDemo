package com.devoralime.filemanagger.upload.service;

import com.devoralime.filemanagger.exception.ObjectsNotFoundException;
import com.devoralime.filemanagger.exception.ServiceUnavailableException;
import com.devoralime.filemanagger.storage.enums.SecurityTypeEnum;
import com.devoralime.filemanagger.upload.model.UploadedFileEntity;
import com.devoralime.filemanagger.upload.repository.UploadedFileRepository;
import com.devoralime.filemanagger.user.model.UserStorageCredentialEntity;
import com.devoralime.filemanagger.user.repository.UserStorageCredentialRepository;
import com.devoralime.filemanagger.user.service.IUserService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static com.devoralime.filemanagger.CONSTANTS.URL;

@Service
@Transactional
public class UploadService implements IUploadService {
    private final IUserService userService;
    private final UserStorageCredentialRepository credentialRepository;
    private final RestTemplate restTemplate;
    private final UploadedFileRepository uploadedFileRepository;

    @Autowired
    public UploadService(IUserService userService, UserStorageCredentialRepository credentialRepository, RestTemplate restTemplate, UploadedFileRepository uploadedFileRepository) {
        this.userService = userService;
        this.credentialRepository = credentialRepository;
        this.restTemplate = restTemplate;
        this.uploadedFileRepository = uploadedFileRepository;
    }

    @Override
    public void upload(MultipartFile file, List<Long> ids) {

        List<UserStorageCredentialEntity> allById = credentialRepository.findAllByUserAndIds(ids, userService.getCurrentUser());
        if (allById.size() == ids.size()) {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            allById.forEach(it -> {
                if (it.getStorage().getSecurityType() == SecurityTypeEnum.BASIC_AUTH) {
                    headers.setBasicAuth(it.getUserName(), it.getPassword());
                } else if (it.getStorage().getSecurityType() == SecurityTypeEnum.TOKEN) {
                    headers.setBearerAuth(it.getToken());
                }
                HttpEntity<byte[]> entity = null;
                try {
                    entity = new HttpEntity<>(file.getBytes(), headers);
                    ResponseEntity<String> exchange = restTemplate.exchange(URL, HttpMethod.POST, entity, String.class);
                    if (!exchange.getStatusCode().is2xxSuccessful()) {
                        throw new ServiceUnavailableException("SERVICE_IS_UNAVAIBLE");
                    }
                    UploadedFileEntity uploadedFileEntity = new UploadedFileEntity();
                    uploadedFileEntity.setUploader(userService.getCurrentUser());
                    uploadedFileEntity.setUploadDate(LocalDate.now());
                    uploadedFileEntity.setFileName(file.getName());
                    uploadedFileEntity.setStorage(it.getStorage());
                    uploadedFileRepository.save(uploadedFileEntity);
                } catch (IOException e) {
                    throw new ServiceUnavailableException("SERVICE_IS_UNAVAIBLE");
                }
            });
        } else {
            throw new ObjectsNotFoundException("Credentials not found");
        }

    }

    HttpHeaders createBasicAuthHeaders(String username, String password) {
        return new HttpHeaders() {{
            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(Charset.forName("US-ASCII")));
            String authHeader = "Basic " + new String(encodedAuth);
            set("Authorization", authHeader);
        }};
    }

    HttpHeaders createTokenHeaders(String token) {
        return new HttpHeaders() {{
            String authHeader = "Bearer " + token;
            set("Authorization", authHeader);
        }};
    }
}
