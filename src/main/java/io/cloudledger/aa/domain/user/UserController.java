package io.cloudledger.aa.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("")
    @Secured({ "ROLE_Administrator" })
    public CollectionModel<EntityModel<User>> findAll() {
        List<User> users = userService.findAll();
        return CollectionModel.of(entityListToEntityModelList(users),
                linkTo(methodOn(UserController.class).findAll()).withSelfRel());
    }

    @GetMapping("/{id}")
    @Secured({ "ROLE_Administrator" })
    public EntityModel<User> findById(@PathVariable UUID id) {
        return entityToEntityModel(userService.findById(id));
    }

    public EntityModel<User> entityToEntityModel(User user) {
        return EntityModel.of(user,
                linkTo(methodOn(UserController.class).findById(user.getId())).withSelfRel());
    }

    public List<EntityModel<User>> entityListToEntityModelList(List<User> users) {
         return users.stream()
                 .map(this::entityToEntityModel)
                 .toList();
    }
}
