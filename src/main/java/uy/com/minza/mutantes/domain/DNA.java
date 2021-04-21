package uy.com.minza.mutantes.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("dnas")
public class DNA {

    @Id
    private String id;
    private String[] dna;
    private Boolean result;

    @Version
    private Integer version;
}
