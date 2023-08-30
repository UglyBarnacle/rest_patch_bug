package rest.patch.bug;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "models")
public interface BugModelRepository extends MongoRepository<BugModel, String>
{}
