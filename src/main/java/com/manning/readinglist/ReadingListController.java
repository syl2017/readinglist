package com.manning.readinglist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author:syl
 * @Date： 2020/12/6
 * @Description:
 *
 */

@Controller //自动注册Spring的一个bean
@RequestMapping("/") //将所有处理器方法映射到“/”这个url路径
public class ReadingListController {
    private ReadingListRepository readingListRepository;

    @Autowired
    public ReadingListController(ReadingListRepository readingListRepository) {
        this.readingListRepository = readingListRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/fail")

    public void fail() {
        throw new RuntimeException();
    }

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(value = HttpStatus.BANDWIDTH_LIMIT_EXCEEDED)
    public String error() {
        return "error";
    }
    @RequestMapping(method = RequestMethod.GET)
    public String readersBooks(Reader reader,Model model) {//处理/{reader}的get请求
        List<Book> readingList = readingListRepository.findByReader(reader);
        if (readingList != null) {
            model.addAttribute("books", readingList);
            model.addAttribute("reader", reader);
        }
        return "readingList";
    }
    @RequestMapping(method = RequestMethod.POST)
    public  String addToReadingList(Reader reader,Book book) {//处理/{reader}的post请求
        book.setReader(reader);
        readingListRepository.save(book);
        return "redirect:/";

    }
}
