package ru.slava.lesson20.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

import java.util.*;

@AllArgsConstructor
@ToString
@Table("programmers")
public class Programmer {

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
    private String name;

    @CassandraType(type = CassandraType.Name.LIST, typeArguments = {CassandraType.Name.UDT}, userTypeName = "languages")
    @Getter @Setter
    private List<Language> languageList;

    @CassandraType(type = CassandraType.Name.LIST, typeArguments = {CassandraType.Name.UDT}, userTypeName = "skills")
    @Getter @Setter
    private List<Skill> skillList;
}

//{
//        "id": 1,
//        "name": "Slava",
//        "languageList": [
//        {
//        "id": 1,
//        "title": "C",
//        "description": "Good",
//        "skillList": [
//        {
//        "id": 2,
//        "title": "Why",
//        "description": "Bad"
//        }
//        ]
//        }
//        ],
//        "skillList": [
//        {
//        "id": 1,
//        "title": "how",
//        "description": "Good"
//        }
//        ]
//        }
