package miu.edu.attendance.controller;

import miu.edu.attendance.domain.TimeSlot;
import miu.edu.attendance.dto.LocationDTO;
import miu.edu.attendance.dto.TimeSlotDTO;
import miu.edu.attendance.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {

    @Autowired
    LocationService locationService;

    @GetMapping("location")
    public ResponseEntity<LocationDTO> getAllLocations() {

        return new ResponseEntity(locationService.getAllLocations(), HttpStatus.OK);
    }
@PostMapping("/location")
    public ResponseEntity addLocation(@RequestBody LocationDTO locationDTO){
             if( locationService.addLocation(locationDTO)){
                 return new ResponseEntity(HttpStatus.OK);
             }
             return new ResponseEntity(HttpStatus.NOT_FOUND);
    }


    @PutMapping("/location")
    public ResponseEntity updateLocation(@RequestBody LocationDTO locationDTO) {
        if (locationDTO.getId() != null) {
            if (locationService.addLocation(locationDTO)) {
                return new ResponseEntity(HttpStatus.ACCEPTED);
            }
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/id")
    public ResponseEntity<LocationDTO> findLocationID (@RequestParam Long id){

       return locationService.findLocationById(id);
    }

    @DeleteMapping("/id")
    public boolean deleteLocation(@RequestParam Long id){
      return locationService.deleteLocation(id);
    }
}
