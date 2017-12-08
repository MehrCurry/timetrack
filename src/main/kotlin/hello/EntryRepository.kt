package hello

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(collectionResourceRel = "entry", path = "entry")
interface EntryRepository : PagingAndSortingRepository<Entry,Long> {
}