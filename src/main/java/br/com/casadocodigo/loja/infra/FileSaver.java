package br.com.casadocodigo.loja.infra;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileSaver {
    @Autowired
    private HttpServletRequest request;

    public String write(String baseFolder, MultipartFile file) {
        try {
            String realPath = request.getServletContext().getRealPath("/"+baseFolder);
            String path = realPath + "/" + file.getOriginalFilename();
            file.transferTo(new File(path));
            return baseFolder + "/" + file.getOriginalFilename();

        } catch (IllegalStateException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}

/**


O caminho do arquivo agora é diferente do que fizemos antes, ele não é mais uma simples junção do baseFolder 
com o nome do arquivo. Este caminho agora precisa ser concatenado com o caminho absoluto que acabamos de implementar
 através do request. Sendo assim, guardaremos o retorno do request.getServletContext().getRealPath("/"+baseFolder); 
 em uma nova String que chamaremos de realPath e usaremos esta String para concatenar ao path do arquivo que geramos 
 anteriormente. Observe o código:


*/