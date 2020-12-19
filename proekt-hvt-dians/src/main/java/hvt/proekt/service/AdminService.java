package hvt.proekt.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface AdminService {
    void store(MultipartFile file);
    void updateDB();
    boolean auth(String password);
}
