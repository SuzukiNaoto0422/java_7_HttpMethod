package Java.Http.Methodexample.HttpMethod;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EntryController {
    /*���͉�ʂ�\��*/
    @GetMapping("entry")
    public String showView() {
        return "entry";//entry.html�ƕR�Â����Ă���HTML���\�������
        //http://localhost:8080/entry�ŃA�N�Z�X
    }
}
