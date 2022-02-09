package test.concord.service;

import org.springframework.stereotype.Service;
import test.concord.dto.request.DataRequest;
import test.concord.dto.response.DataResponse;

@Service
public class DataService {
    public DataResponse processing(DataRequest dataRequest) {
        if (dataRequest != null && dataRequest.getId() != null &&dataRequest.getId() == 1) {
            DataResponse dataResponse = new DataResponse();
            dataResponse.setFio("Test Testov");
            return dataResponse;
        }
        return null;
    }
}
