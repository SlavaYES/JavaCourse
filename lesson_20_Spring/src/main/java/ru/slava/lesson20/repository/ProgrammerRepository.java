package ru.slava.lesson20.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import ru.slava.lesson20.entity.Programmer;

@Repository
public interface ProgrammerRepository extends CassandraRepository<Programmer, Long> {
}
