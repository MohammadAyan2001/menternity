package resumebuilder.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import resumebuilder.model.Resume;
import resumebuilder.repository.ResumeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ResumeService {

    @Autowired
    private ResumeRepository resumeRepository;

    // Save or update a resume
    public Resume save(Resume resume) {
        return resumeRepository.save(resume);
    }

    // Fetch a resume by ID
    public Optional<Resume> getById(String id) {
        return resumeRepository.findById(id);
    }

    // Fetch all resumes (optional, useful for admin or debugging)
    public List<Resume> getAll() {
        return resumeRepository.findAll();
    }


    public Resume getResumeById(String id) {
    Optional<Resume> resumeOpt = resumeRepository.findById(id);
    return resumeOpt.orElse(null);
}
    // Delete a resume by ID (optional)
    public void deleteById(String id) {
        resumeRepository.deleteById(id);
    }
}
