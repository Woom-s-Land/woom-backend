package com.ee06.wooms.domain.users.repository;

import com.ee06.wooms.domain.users.entity.Mail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailRepository extends CrudRepository<Mail, String> {
}
