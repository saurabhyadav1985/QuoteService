package org.acme.quotehub.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@Table(name = "quote", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"author", "content"})
})
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String author;

    String content;

    String createdBy;
}
