package ru.slava.lesson20.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;
import ru.slava.lesson20.entity.Question;

@Repository
public interface QuestionRepository extends CassandraRepository<Question, Long> {
}
