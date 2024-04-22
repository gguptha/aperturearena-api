package io.cloudledger.aa.domain.contest;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ContestRepository extends JpaRepository<Contest, UUID> {

    List<Contest> findContestByStatusNotOrderByRegistrationStartDateDesc(ContestStatus status);
}
