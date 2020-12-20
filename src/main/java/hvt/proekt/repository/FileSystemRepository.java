package hvt.proekt.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Repository
public class FileSystemRepository {
    public void saveFile(MultipartFile file)  {
        File f = new File("./src/main/java/hvt/proekt/model/bootstrap/db/myFile.xml");
        try {
            if(!f.exists())
            {
                f.createNewFile();
            }
            OutputStream os = new FileOutputStream(f);
            os.write(file.getBytes());
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
