package io.cloudledger.aa.domain.contest;

import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/contests")
@RequiredArgsConstructor
public class ContestController {

    private final ContestService contestService;

    @GetMapping("")
    @Secured({ "ROLE_Administrator" })
    public CollectionModel<EntityModel<Contest>> findAll() {
        List<Contest> contests = contestService.findAll();
        return CollectionModel.of(entityListToEntityModelList(contests),
                linkTo(methodOn(ContestController.class).findAll()).withSelfRel());
    }

    @GetMapping("/{id}")
    @Secured({ "ROLE_Administrator" })
    public EntityModel<Contest> findById(@PathVariable UUID id) {
        return entityToEntityModel(contestService.findById(id));
    }

    @PostMapping("")
    @Secured({ "ROLE_Administrator" })
    public EntityModel<Contest> create(@RequestBody Contest contest) {
        return entityToEntityModel(contestService.create(contest));
    }

    @PutMapping("/{id}")
    @Secured({ "ROLE_Administrator" })
    public EntityModel<Contest> update(@PathVariable UUID id, @RequestBody Contest contest) {
        return entityToEntityModel(contestService.update(id, contest));
    }

    public EntityModel<Contest> entityToEntityModel(Contest contest) {
        return EntityModel.of(contest,
                linkTo(methodOn(ContestController.class).findById(contest.getId())).withSelfRel());
    }

    public List<EntityModel<Contest>> entityListToEntityModelList(List<Contest> contests) {
        return contests.stream()
                .map(this::entityToEntityModel)
                .toList();
    }
}
