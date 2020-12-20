package hvt.proekt.service.impl;


import hvt.proekt.repository.FileSystemRepository;
import hvt.proekt.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AdminServiceImpl implements AdminService {

    private final FileSystemRepository fileSystemRepository;

    public AdminServiceImpl(FileSystemRepository fileSystemRepository){
        this.fileSystemRepository = fileSystemRepository;
    }

    @Override
    public void store(MultipartFile file) {
        fileSystemRepository.saveFile(file);
    }

    @Override
    public void updateDB() {

    }

    @Override
    public boolean auth(String password) {
        return password.equals("admin");
    }
}
