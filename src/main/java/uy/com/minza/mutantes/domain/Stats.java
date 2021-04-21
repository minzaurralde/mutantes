package uy.com.minza.mutantes.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("stats")
public class Stats {

    @Id
    private String id;

    private Integer humanCount;
    private Integer mutantCount;

    @Version
    private Integer version;
}
