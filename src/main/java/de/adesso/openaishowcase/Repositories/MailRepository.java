package de.adesso.openaishowcase.Repositories;

import de.adesso.openaishowcase.Models.Mail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface MailRepository extends JpaRepository<Mail, Long> {


    @Query(value = "select * from mails m where m.category = '' OR m.category is null",nativeQuery = true)
    List<Mail> findAllUncategorized();


    List<Mail> findAllByOrderByTimestampDesc();

    Optional<Mail> findByTimestamp(Date timestamp);

}
