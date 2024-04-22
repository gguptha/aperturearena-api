package io.cloudledger.aa.domain.contest;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Relation(collectionRelation = "contests")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Contest {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;

    private String name;

    private Boolean internationalContest;
    private Boolean pastAcceptedImagesAllowed;

    private LocalDate registrationStartDate;
    private LocalDate registrationEndDate;

    private String timeZone;

    private Integer maxImageWidthAccepted;
    private Integer maxImageHeightAccepted;
    private Integer maxFileSize;

    private Boolean hasExhibition;
    private LocalDate exhibitionStartDate;
    private LocalDate exhibitionEndDate;

    private ContestStatus status;

    @OneToOne(cascade = CascadeType.ALL)
    private Judging judging;
}
