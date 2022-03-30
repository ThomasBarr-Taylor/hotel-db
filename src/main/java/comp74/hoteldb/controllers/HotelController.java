package comp74.hoteldb.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import comp74.hoteldb.model.entities.Room;
import comp74.hoteldb.model.entities.Guest;
import comp74.hoteldb.model.entities.Reservation;
import comp74.hoteldb.model.repos.GuestRepo;
import comp74.hoteldb.model.repos.RoomRepo;

@RestController
@RequestMapping("/api")
public class HotelController {

    RoomRepo roomRepo;

    @Autowired
    GuestRepo guestRepo;

    @Autowired
    public HotelController(RoomRepo roomRepo) {
        super();
        this.roomRepo = roomRepo;
    }
    /**
     * sets up our page as well as returning a page, it will give something
     * simular to the list but with more information
     * we also change which results will show by using the PageRequest.of
     * @return
     */
    @GetMapping("/rooms")
    public Page<Room> getRoomsPage(
        @RequestParam(defaultValue = "0") Integer page
    ) {
        PageRequest pageInfo;
        pageInfo = PageRequest.of(page,10, Sort.by("roomNumber"));
        return (Page<Room>) roomRepo.findAll(pageInfo);
    }

    /**
     * localhost:8080/api/rooms/N 
     * 
     * @param id
     * @return
     */
    @GetMapping("/rooms/{id}")
    public Room findRoomById(@PathVariable Long id)
    {
        Optional<Room> opt =  roomRepo.findById(id);
        if (!opt.isEmpty())
            return opt.get();
        return null;
    }
    @GetMapping("/guests")
    public Page<Guest> findAllGuests(@RequestParam(defaultValue = "0") Integer page){
        PageRequest pageInfo;
        pageInfo = PageRequest.of(page,12);
        return (Page<Guest>) guestRepo.findAll(pageInfo);
    }

/**
 * * localhost:8080/api/rooms/8/res
 * 
 * @param id
 * @return
 */
    @RequestMapping ("/rooms/{id}/res")
    public List<Reservation>
    findReservationByRoomId(@PathVariable Long id)
    {
        Room room = roomRepo.findById(id).get();
        return room.getReservations();
    }




}
