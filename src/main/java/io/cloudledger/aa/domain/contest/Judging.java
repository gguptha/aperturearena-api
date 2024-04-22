package io.cloudledger.aa.domain.contest;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Judging {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;

    private LocalDate judgingStartDate;
    private LocalDate judgingEndDate;
    private LocalDate resultsDate;

    private boolean isThereAJudgingEvent;

    private JudgingType judgingType;

    private String venue;
    private String venueAddress;
    private String venueLocationAddress;
}
