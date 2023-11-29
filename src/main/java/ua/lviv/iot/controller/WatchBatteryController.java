package ua.lviv.iot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.dto.WatchBatteryDTO;
import ua.lviv.iot.dto.assembler.WatchBatteryDTOAssembler;
import ua.lviv.iot.model.WatchBattery;
import ua.lviv.iot.service.WatchBatteryService;

import java.util.List;

@RestController
@RequestMapping("/api/watchBattery")
public class WatchBatteryController {

    private WatchBatteryService watchBatteryService;
    private WatchBatteryDTOAssembler batteryDTOAssembler;

    @Autowired
    public WatchBatteryController(WatchBatteryService watchBatteryService,
                                  WatchBatteryDTOAssembler batteryDTOAssembler) {
        this.watchBatteryService = watchBatteryService;
        this.batteryDTOAssembler = batteryDTOAssembler;
    }

    @GetMapping("/")
    public ResponseEntity<CollectionModel<WatchBatteryDTO>> findAll() {
        List<WatchBattery> batteries = watchBatteryService.findAll();
        CollectionModel<WatchBatteryDTO> collectionModel = batteryDTOAssembler
                .toCollectionModel(batteries);
        return new ResponseEntity<CollectionModel<WatchBatteryDTO>>(collectionModel, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WatchBatteryDTO> findById(@PathVariable("id") Long id) {
        WatchBattery battery = watchBatteryService.findById(id);

        WatchBatteryDTO batteryDTO = batteryDTOAssembler.toModel(battery);
        return new ResponseEntity<WatchBatteryDTO>(batteryDTO, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<WatchBatteryDTO> addWatchBattery(@RequestBody WatchBattery watchBattery) {

        WatchBattery battery = watchBatteryService.save(watchBattery);
        WatchBatteryDTO batteryDTO = batteryDTOAssembler.toModel(battery);
        return new ResponseEntity<WatchBatteryDTO>(batteryDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WatchBatteryDTO> updateWatchBattery(@PathVariable("id") Long id,
                                                              @RequestBody WatchBattery watchBattery) {
        WatchBattery battery = watchBatteryService.update(watchBattery, id);
        WatchBatteryDTO batteryDTO = batteryDTOAssembler.toModel(battery);
        return new ResponseEntity<WatchBatteryDTO>(batteryDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<WatchBatteryDTO> deleteWatchBattery(@PathVariable("id") Long id) {
        WatchBattery battery = watchBatteryService.deleteById(id);
        WatchBatteryDTO batteryDTO = batteryDTOAssembler.toModel(battery);
        return new ResponseEntity<WatchBatteryDTO>(batteryDTO, HttpStatus.OK);
    }

}
