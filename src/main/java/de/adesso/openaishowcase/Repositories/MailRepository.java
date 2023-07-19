package de.adesso.openaishowcase.Repositories;

import de.adesso.openaishowcase.Models.Mail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailRepository extends JpaRepository<Mail, Long> {


}
