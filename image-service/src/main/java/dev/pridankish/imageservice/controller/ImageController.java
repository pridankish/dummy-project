package dev.pridankish.imageservice;

import dev.pridankish.imageservice.dto.ImageUploadResponse;
import dev.pridankish.imageservice.service.MinioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class ImageController {

    private final MinioService minioService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ImageUploadResponse> uploadImage(
            @RequestParam("image") MultipartFile file) {
        try {
            String fileUrl = minioService.uploadImage(file);
            String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);

            var response = new ImageUploadResponse(
                    fileName,
                    fileUrl,
                    "images",
                    "Image uploaded successfully"
            );

            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            log.error("Validation error: {}", e.getMessage());
            ImageUploadResponse errorResponse = new ImageUploadResponse(
                    null, null, null, e.getMessage()
            );
            return ResponseEntity.badRequest().body(errorResponse);

        } catch (Exception e) {
            log.error("Error uploading image", e);
            ImageUploadResponse errorResponse = new ImageUploadResponse(
                    null, null, null, "Error uploading image"
            );
            return ResponseEntity.internalServerError().body(errorResponse);
        }
    }

    @GetMapping("/{filename}")
    public ResponseEntity<Resource> getImage(@PathVariable String fileName) {
        try {
            var resource = new InputStreamResource(
                    minioService.downloadImage(fileName)
            );

            String contentType = determineContentType(fileName);

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "inline; filename=\"" + fileName + "\"")
                    .body(resource);
        } catch (Exception e) {
            log.error("Error reading image file: {}", fileName, e);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{fileName}")
    public ResponseEntity<Void> deleteImage(@PathVariable String fileName) {
        try {
            if (!minioService.imageExists(fileName)) {
                return ResponseEntity.notFound().build();
            }

            minioService.deleteImage(fileName);
            return ResponseEntity.noContent().build();

        } catch (Exception e) {
            log.error("Ошибка при удалении изображения: {}", fileName, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    private String determineContentType(String fileName) {
        if (fileName.endsWith(".png")) {
            return "image/png";
        } else if (fileName.endsWith(".gif")) {
            return "image/gif";
        } else if (fileName.endsWith(".webp")) {
            return "image/webp";
        } else {
            return "image/jpeg";
        }
    }
}
