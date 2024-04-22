package io.cloudledger.aa.domain.contest;

import java.util.List;
import java.util.UUID;

public interface ContestService {

    List<Contest> findAll();

    Contest findById(UUID id);

    Contest create(Contest contest);
    Contest update(UUID id, Contest contest);
}
