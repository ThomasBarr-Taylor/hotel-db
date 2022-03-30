
package comp74.hoteldb.model.repos;

import org.springframework.data.repository.PagingAndSortingRepository;

import comp74.hoteldb.model.entities.Room;

/**
 * changed to from crud to pagingandsorting which will expand what our repo is capable of
 */
public interface RoomRepo  extends PagingAndSortingRepository<Room,Long>{
   
}