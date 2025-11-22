package resumebuilder.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import resumebuilder.model.Resume;

public interface ResumeRepository extends MongoRepository<Resume, String> {
}
