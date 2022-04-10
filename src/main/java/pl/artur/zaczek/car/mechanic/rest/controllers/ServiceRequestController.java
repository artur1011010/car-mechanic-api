package pl.artur.zaczek.car.mechanic.rest.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.artur.zaczek.car.mechanic.rest.error.ApiErrorResponse;
import pl.artur.zaczek.car.mechanic.rest.model.CreateServiceRequest;
import pl.artur.zaczek.car.mechanic.rest.model.ServiceRequestResponse;
import pl.artur.zaczek.car.mechanic.service.ServiceRequestService;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class ServiceRequestController {

    private final ServiceRequestService serviceRequestService;

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/service-request",
            produces = "application/json; charset=UTF-8")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Return list service requests by customer id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ServiceRequestResponse[].class),
            @ApiResponse(code = 400, message = "Bad Request", response = ApiErrorResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = ApiErrorResponse.class)})
    public ResponseEntity<List<ServiceRequestResponse>> findServiceRequestsByUserId(@RequestParam Long customerId) {
        log.info("GET api/service-request with customerId={}", customerId);
        return ResponseEntity.ok(serviceRequestService.findSRByCustomerId(customerId));
    }

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/service-request",
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Create new Service Request for specific customer id",
            notes = "Service creates new Service Request, takes body:\n- CreateServiceRequest \nand 2 query params: \n- userId\n- vehicleId")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Long.class),
            @ApiResponse(code = 400, message = "Bad Request", response = ApiErrorResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = ApiErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal server error", response = ApiErrorResponse.class)})
    public ResponseEntity<Long> saveNewServiceRequest(@RequestBody @Valid CreateServiceRequest request, @RequestParam Long customerId, @RequestParam Long vehicleId) {
        log.info("POST api/service-request with body={}\ncustomerId={}\nvehicleId={}", request, customerId, vehicleId);
        return ResponseEntity.ok(serviceRequestService.createSR(request, customerId, vehicleId));
    }

}