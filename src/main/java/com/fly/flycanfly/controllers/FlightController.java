package com.fly.flycanfly.controllers;

import com.fly.flycanfly.dto.*;
import com.fly.flycanfly.entities.*;
import com.fly.flycanfly.services.BookmarkService;
import com.fly.flycanfly.services.FlightService;
import com.fly.flycanfly.services.UserAccountService;
import com.fly.flycanfly.utils.JwtTokenUtil;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Getter
@Setter
@RequestMapping("/flycanfly/api/v1.0/flights")
public class FlightController {

    private final ModelMapper modelMapper;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    BookmarkService bookmarkService;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    PropertyMap<FlightEntity, FlightDto> companyFieldMapping = new PropertyMap<>() {
        @Override
        protected void configure() {
            map().setCompanyName(source.getCompanyEntity().getCompanyName());
            map().setCabinDetailSet(source.getCompanyEntity().getCabinDetailSet());
            map().setInflightInfoSet(source.getCompanyEntity().getInflightInfoSet());
        }
    };
    @Autowired
    FlightService flightService;
    @Autowired
    UserAccountService userAccountService;

    @Autowired
    public FlightController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.addMappings(companyFieldMapping);

    }

    @PostMapping("/addFlight")
    public ResponseEntity<FlightEntity> addFlight(@RequestBody FlightEntity flight) {
        FlightEntity addedFlight = flightService.addFlight(flight);
        return new ResponseEntity<>(addedFlight, new HttpHeaders(), HttpStatus.CREATED);
    }


    @GetMapping("/allFlights")
    public ResponseEntity<List<FlightDto>> getAllFlights() {
        List<FlightDto> flights = flightService.getAllFlights().stream()
                .map(flight -> modelMapper.map(flight, FlightDto.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(flights, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<FlightDto>> searchFlights(@RequestBody FlightCriteria flightCriteria) {
        List<FlightDto> flights = flightService.searchFlight(flightCriteria).stream()
                .map(flight -> modelMapper.map(flight, FlightDto.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(flights, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/getFlight/{id}")
    public ResponseEntity<?> getFlight(@PathVariable(value = "id") Long idFlight) {
        FlightEntity flight = flightService.getFlights(idFlight);
        if (flight == null) {
            return new ResponseEntity<String>("Flight number " + idFlight + " doesn't exist", HttpStatus.NOT_FOUND);
        } else {
            FlightDto flightDto = modelMapper.map(flight, FlightDto.class);

            return new ResponseEntity<>(flightDto, new HttpHeaders(), HttpStatus.OK);
        }

    }

    @PostMapping("/numberOfFlights")
    public ResponseEntity<Long> getNumberOfFlights(@RequestBody SynthesisCriteria synthesisCriteria) {
        Long numberOfFlights = flightService.getNumberOfFlights(synthesisCriteria);

        return new ResponseEntity<>(numberOfFlights, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/synthesisCompany")
    public ResponseEntity<List<SynthesisCompanyDto>> getNumberOfFlightsByCompany(@RequestBody SynthesisCriteria synthesisCriteria) {
        List<SynthesisCompanyDto> synthesisCompanyDtos = flightService.getNumberOfFlightsByCompany(synthesisCriteria);

        return new ResponseEntity<List<SynthesisCompanyDto>>(synthesisCompanyDtos, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserAccountEntity> addUserAccount(@Valid @RequestBody UserAccountDto userAccountDto) {
        UserAccountEntity userAccountEntity = userAccountService.addUserAccountEntity(userAccountDto);
        return new ResponseEntity<>(userAccountEntity, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<LoginResponseDto> authenticate(@RequestBody LoginDto loginDto) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        UserDetails userDetails = userAccountService.loadUserByUsername(loginDto.getUsername());
        String jwt = jwtTokenUtil.generateJWT(userDetails);
        return new ResponseEntity<>(new LoginResponseDto(jwt), new HttpHeaders(), HttpStatus.OK);

    }


    @PostMapping("/addBookmark")
    public ResponseEntity<Bookmark> addBookmark(@RequestBody BookmarkDto bookmarkDto, Principal principal) {
        UserAccountEntity userAccountEntity = userAccountService.findByUsername(principal.getName());
        Bookmark bookmark = modelMapper.map(bookmarkDto, Bookmark.class);
        bookmark.setUserAccountEntity(userAccountEntity);
        Bookmark addedBookmark = bookmarkService.addBookmark(bookmark);
        return new ResponseEntity<>(addedBookmark, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/bookmarks")
    public ResponseEntity<List<Bookmark>> getBookmarkList(Principal principal) {
        List<Bookmark> bookmarks = bookmarkService.getBookmarkList(principal.getName());
        return new ResponseEntity<>(bookmarks, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/bookmarks/{id}")
    public ResponseEntity<String> deleteBookmark(@PathVariable(value = "id") Long idBookmark) {
        try {
            bookmarkService.deleteBookmark(idBookmark);
            return new ResponseEntity<String>("Bookmark number " + idBookmark + "has been successfully deleted", new HttpHeaders(), HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<String>("Bookmark number " + idBookmark + "doesn't exist", HttpStatus.NOT_FOUND);
        }


    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String field = "";
            if (error.getClass().getSimpleName().equals("ViolationObjectError")) {
                field = error.getObjectName();
            } else if (error.getClass().getSimpleName().equals("ViolationFieldError")) {
                field = ((FieldError) error).getField();
            }

            String message = error.getDefaultMessage();
            errors.put(field, message);
        });

        return errors;

    }
}
