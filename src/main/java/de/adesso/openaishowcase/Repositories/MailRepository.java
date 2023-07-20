package de.adesso.openaishowcase.Repositories;

import de.adesso.openaishowcase.Models.Mail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MailRepository extends CrudRepository<Mail, Long>{


    @Query(value = "select * from mails m where m.category = '' OR m.category is null",nativeQuery = true)
    List<Mail> findAllUncategorized();


}
