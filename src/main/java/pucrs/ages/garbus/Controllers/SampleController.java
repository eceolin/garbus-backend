package pucrs.ages.garbus.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pucrs.ages.garbus.Services.SampleService;
import pucrs.ages.garbus.Utils.Constants;


@RestController
@AllArgsConstructor
public class SampleController {

    private final SampleService sampleService;
    private final Constants consts;

    @RequestMapping(value = {"/samplepost"}, method = RequestMethod.GET)
    public String testMethod(){
        return consts.dbUrl;
    }

}