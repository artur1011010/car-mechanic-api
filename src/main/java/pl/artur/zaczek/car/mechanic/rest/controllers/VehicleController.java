package pl.artur.zaczek.car.mechanic.rest.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.artur.zaczek.car.mechanic.rest.model.VehicleResponse;
import pl.artur.zaczek.car.mechanic.service.VehicleService;
import pl.artur.zaczek.car.mechanic.rest.error.ApiErrorResponse;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class VehicleController {

    private final VehicleService vehicleService;

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/vehicle",
            produces = "application/json; charset=UTF-8")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Return list of all vehicles")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = VehicleResponse[].class),
            @ApiResponse(code = 404, message = "Not found", response = ApiErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal server error", response = ApiErrorResponse.class)})
    public ResponseEntity<List<VehicleResponse>> getVehicles() {
        log.info("GET api/vehicle");
        return ResponseEntity.ok(vehicleService.getVehicles());
    }
}