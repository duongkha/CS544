package miu.edu.attendance.service;

import miu.edu.attendance.domain.Location;
import miu.edu.attendance.dto.LocationDTO;
import miu.edu.attendance.repository.LocationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LocationServiceImpl implements LocationService{

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public boolean addLocation(LocationDTO locationDTO) {
        Location location= modelMapper.map(locationDTO, Location.class);
        if(locationRepository.save(location)!=null){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateLocation(LocationDTO locationDTO) {
        Location location=null;
        Location location1= modelMapper.map(locationDTO,Location.class);
         location=locationRepository.findById(location1.getId()).orElse(null);
        if(location!=null){
           location.setDescription(location1.getDescription());
           locationRepository.save(location);
           return true;
        }
        return false;
    }

    @Override
    public boolean deleteLocation(Long id) {
      locationRepository.deleteById(id);
      return true;

    }

    @Override
    public ResponseEntity<LocationDTO> findLocationById(Long id) {

        Location location=locationRepository.findById(id).orElse(null);
        LocationDTO locationDTO= modelMapper.map(location,LocationDTO.class);
        return new ResponseEntity<>(locationDTO, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @Override
    public List<LocationDTO> getAllLocations() {
      List<Location> locations= locationRepository.findAll();
     return locations.stream().map(location->modelMapper.map(location,LocationDTO.class)).collect(Collectors.toList());
    }
}
