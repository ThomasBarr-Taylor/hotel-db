package comp74.hoteldb.model.repos;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import comp74.hoteldb.model.entities.Guest;

@CrossOrigin
public interface GuestRepo extends PagingAndSortingRepository<Guest,Long> {
    
}
