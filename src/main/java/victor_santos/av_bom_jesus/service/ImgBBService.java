package victor_santos.av_bom_jesus.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.Map;

@Service
public class ImgBBService {

    @Value("${imgbb.api.key}")
    private String apiKey;

    public String uploadImage(MultipartFile file) {
        if (file == null || file.isEmpty()) return null;

        try {
            RestTemplate restTemplate = new RestTemplate();
            var url = "https://api.imgbb.com/1/upload?key=" + apiKey;
            var base64Image = Base64.getEncoder().encodeToString(file.getBytes());
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

            body.add("image", base64Image);

            var headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
            Map<String, Object> response = restTemplate.postForObject(url, requestEntity, Map.class);

            if (response != null && response.containsKey("data")) {
                Map<String, Object> data = (Map<String, Object>) response.get("data");
                return (String) data.get("url");
            }

            throw new RuntimeException("Resposta inválida do ImgBB");
        } catch (Exception e) {
            throw new RuntimeException("Erro ao fazer upload para ImgBB: " + e.getMessage(), e);
        }
    }
}