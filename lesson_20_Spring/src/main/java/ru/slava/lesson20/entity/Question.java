package ru.slava.lesson20.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

@AllArgsConstructor
@UserDefinedType("questions")
@Table("questions")
public class Question {
    @Id
    @PrimaryKeyColumn(
            name = "id",
            ordinal = 2,
            type = PrimaryKeyType.PARTITIONED,
            ordering = Ordering.DESCENDING
    )
    @Getter @Setter
    private Long id;

    @Column
    @Getter @Setter
    private String question;

    @Column
    @Getter @Setter
    private String answer;
}
