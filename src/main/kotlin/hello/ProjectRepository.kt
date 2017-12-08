package hello

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(collectionResourceRel = "project", path = "project")
interface ProjectRepository: PagingAndSortingRepository<Project,Long> {
    fun save(project: Project)
}