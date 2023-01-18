package Java.Http.Methodexample.HttpMethod;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EntryController {
    /*入力画面を表示*/
    @GetMapping("entry")
    public String showView() {
        return "entry";//entry.htmlと紐づけられていてHTMLが表示される
        //http://localhost:8080/entryでアクセス
    }
}
