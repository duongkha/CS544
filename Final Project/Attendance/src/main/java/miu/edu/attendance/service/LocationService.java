package miu.edu.attendance.service;

import miu.edu.attendance.domain.TimeSlot;
import miu.edu.attendance.dto.LocationDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LocationService {

    public boolean addLocation(LocationDTO locationDTO);
    public boolean updateLocation(LocationDTO locationDTO);
    public boolean deleteLocation(Long id);
    public ResponseEntity<LocationDTO> findLocationById(Long id);
    public List<LocationDTO> getAllLocations();
}
