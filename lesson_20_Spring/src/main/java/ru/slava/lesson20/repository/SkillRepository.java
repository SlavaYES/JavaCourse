package ru.slava.lesson20.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;
import ru.slava.lesson20.entity.Skill;

import java.util.Optional;

@Repository
public interface SkillRepository extends CassandraRepository<Skill, Long> {
    @Query("Select * from dorchester.skills where title = ?0 allow filtering")
    Optional<Skill> findByTitle(String title);
}
