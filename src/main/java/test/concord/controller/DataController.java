package test.concord.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import test.concord.dto.request.DataRequest;
import test.concord.dto.response.DataResponse;
import test.concord.service.DataService;

@RestController
public class DataController {
    private final DataService dataService;

    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    @PostMapping("/checkId")
    public DataResponse checkId(@RequestBody DataRequest dataRequest) {
        return dataService.processing(dataRequest);
    }
}
