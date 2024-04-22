package io.cloudledger.aa.util;

import org.springframework.hateoas.EntityModel;

public interface HateoasUtil {

    <T> EntityModel<T> entityToEntityModel(T entity);
}
