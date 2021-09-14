package uz.pdp.task_11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task_11.entity.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment,Integer> {
}
