package resumebuilder.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import resumebuilder.model.Resume;
import resumebuilder.service.PdfService;
import resumebuilder.service.ResumeService;

@RestController
@RequestMapping("/api") // Better to have a base URI
public class ResumeController {

    @Autowired
    private ResumeService resumeService;
    @Autowired
    private PdfService pdfService;

    @PostMapping("/resume")
    public ResponseEntity<Resume> createResume(@RequestBody Resume resume) {
        Resume saved = resumeService.save(resume);
         Map<String, String> response = new HashMap<>();
    response.put("id", saved.getId()); // Return ID
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/resume/{id}/pdf")
    public ResponseEntity<?> downloadPdf(@PathVariable String id) {
    try {
        Resume resume = resumeService.getResumeById(id);
        if (resume == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resume with id " + id + " not found");
        }

        ByteArrayInputStream pdfStream = pdfService.generatePdf(resume);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.builder("inline")
                .filename("resume_" + resume.getFullName().replaceAll("\\s+", "_") + ".pdf")
                .build());

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfStream.readAllBytes());

    } catch (IOException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error generating PDF");
    }
}

}
