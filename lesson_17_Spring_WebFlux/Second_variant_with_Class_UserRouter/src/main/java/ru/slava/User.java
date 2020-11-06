package ru.slava;

import io.vertx.core.json.JsonObject;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    public Integer id;
    public String domain;
    public String name;

    public User(JsonObject jsonObject) {
        this.id = (Integer) jsonObject.getValue("id");
        this.name = (String) jsonObject.getValue("first_name");
        this.domain = (String) jsonObject.getValue("domain");
    }
}
