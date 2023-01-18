package Java.Http.Methodexample.HttpMethod;

import io.micrometer.common.lang.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController

public class HttpMethodController {

    @GetMapping("/names")
    public List<String> getNames() {
        return List.of("suzuki","tanaka");
        //http://localhost:8080/namesでアクセス
    }

    @PostMapping("/names")
    public ResponseEntity<String> create(@RequestBody CreateForm form) {
        URI url = UriComponentsBuilder.fromUriString("http://localhost:8080")
                // form.getName(), form.getBirthdate()を使用する
                .path("/names/id")
                .build()
                .toUri();
        return ResponseEntity.created(url).body("name successfully created");
    /* curl -X POST -H "Content-Type: application/json"
    -d '{ "name": "John Doe", "birthdate": "2000-01-01" }' http://localhost:8080/namesのような入力 */
    }

    @PatchMapping("/names/{id}")
    public ResponseEntity<Map<String, String>> update(@PathVariable("id") int id, @RequestBody UpdateForm form) {
        return ResponseEntity.ok(Map.of("message", "name successfully updated"));
        /* curl -X PATCH 'http://localhost:8080/names/1' \
        --header 'Content-Type: application/json' \
        --data-raw '{
        "name": "suzuki"
        }' のようなコマンド*/
    }

    @DeleteMapping("/names/{id}")
    public ResponseEntity<Map<String,String>> delete(@PathVariable("id") int id) {//idに対応する名前の削除
        return ResponseEntity.ok(Map.of("message", "name successfully deleted"));
        /* curl -X DELETE 'http://localhost:8080/names/1' \
        --header 'Content-Type: application/json' \
        --data-raw '{
        "name": "suzuki"
        }' のようなコマンド*/
    }


    @GetMapping("/greeting")
    public String getGreeting(@NonNull @RequestParam(name = "name")String name,
                              @NonNull @RequestParam(name = "id", required = false) String id) {
        //Integer型に対して「空文字」が渡されると「0」が設定されるためString型に設定
        //@RequestParamアノテーションで使用されるオプションで、その引数が必須であるかどうかを示す
        //required = falseを設定することで、その引数が未入力の場合でもnull値を受け入れ、null値として処理
        //required = trueの場合には、その引数が未入力の場合、400 Bad Requestなどのエラーを返す
        if ((name == null || name.trim().isEmpty()) && (id == null || id.trim().isEmpty())) {
            return "名前とidが空欄です";
        }else if (name == null || name.trim().isEmpty()) {
            //.trim()は文字列の中にある空白文字を除去し、.isEmpty()はその文字列が空文字かどうかを判定するためのメソッド
            return "名前が空欄です";
        }else if (id == null || id.trim().isEmpty()) { //Integer型の場合は、nullを表すことができるが空の値を表すことはできない
            return "idが空欄です";
        }else if (name.length() >= 20 || id.length() >= 20) {
            return "名前またはidは20文字以内で入力してください";
        }

        return "ようこそ！ "+ "会員番号"+ id + "番の" + name +"さん！";
        //例.http://localhost:8080/greeting?name=John&id=1でのアクセスで表示

    }
}
