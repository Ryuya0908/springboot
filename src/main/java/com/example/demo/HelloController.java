//package com.example.demo;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model; // ← ①これを追加
//import org.springframework.web.bind.annotation.GetMapping;
//
//@Controller
//public class HelloController {
//
//    @GetMapping("/hello")
//    public String hello(Model model) { // ← ②カッコの中に Model model を追加
//        
//        // ③HTMLに渡したいデータをセットする
//        // "message" という名札をつけて、右側の文章を箱に入れます
//        model.addAttribute("message", "JavaからHTMLへ、データのお届け物です！AAAAAAAAAAAA");
//        
//        return "hello"; 
//    }
//}

package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping; // ←追加
import org.springframework.web.bind.annotation.RequestParam; // ←追加

@Controller
public class HelloController {

    // ①最初の画面を表示するメソッド
    @GetMapping("/hello")
    public String hello() {
        return "hello"; 
    }

    // ②フォームから送られてきたデータを受け取るメソッド
    @PostMapping("/greet")
    public String greet(@RequestParam("userName") String name,@RequestParam(required = false) Integer userage, Model model) {
       if (name==null || name.isEmpty() || userage==null) {
		model.addAttribute("errorMessage","空白はやめてくださいな。名前と年齢を両方入力してください");
		return "hello";
	}
    	String message;
    	if (userage >= 20) {
			message=name+"さん("+userage+"歳)は、お酒が飲めますね";
		}else {
			message=name+"さんは("+userage+"歳)のためお酒は飲めません。";
		}
    	
        // 受け取った名前を使ってメッセージを作り、Model（箱）に入れる
        model.addAttribute("greetingMessage",message);
        
        // 「greet.html」という新しい画面を表示する
        return "greet"; 
    }
}