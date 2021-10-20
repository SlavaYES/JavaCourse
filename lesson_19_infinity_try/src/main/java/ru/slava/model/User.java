package ru.slava.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class User {
    @Id
//    @GeneratedValue()
    private Integer id;

    private String name;

    private String descriptor;

    private String date_first;

    private String date_last;

    private String status;

    private String comment;

    /**
     * @param name Name field
     * @param value value for field
     */
    public void setValueByName(String name, String value) {
        switch (name) {
            case "id":
                setId(Integer.parseInt(value));
                break;
            case "name":
                setName(value);
                break;
            case "descriptor":
                setDescriptor(value);
                break;
            case "date_first":
                setDate_first(value);
                break;
            case "date_last":
                setDate_last(value);
                break;
            case "status":
                setStatus(value);
                break;
            case "comment":
                setComment(value);
                break;
            default:
        }
    }
}
