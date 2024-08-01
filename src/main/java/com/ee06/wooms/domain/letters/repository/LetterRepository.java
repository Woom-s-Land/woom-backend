package com.ee06.wooms.domain.letters.repository;

import com.ee06.wooms.domain.letters.entity.Letter;
import com.ee06.wooms.domain.users.entity.Mail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LetterRepository extends CrudRepository<Letter, Long> {
}
