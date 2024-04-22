package io.cloudledger.aa.domain.contest;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultContestService implements ContestService {

    private final ContestRepository contestRepository;

    @Override
    @Secured({ "ROLE_Administrator" })
    public List<Contest> findAll() {
        return contestRepository.findContestByStatusNotOrderByRegistrationStartDateDesc(ContestStatus.Closed);
    }

    @Override
    @Secured({ "Role_Administrator" })
    public Contest findById(UUID id) {
        return contestRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id.toString()));
    }

    @Override
    @Secured({ "ROLE_Administrator"})
    public Contest create(Contest contest) {
        return contestRepository.save(contest);
    }

    @Override
    @Secured({ "ROLE_Administrator"})
    public Contest update(UUID id, Contest contest) {
        Contest obj = contestRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id.toString()));
        BeanUtils.copyProperties(contest, obj, "id");
        return contestRepository.save(obj);
    }
}
