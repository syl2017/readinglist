package com.manning.readinglist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author:syl
 * @Date： 2020/12/6
 * @Description:
 *
 */

@Controller //自动注册Spring的一个bean
@RequestMapping("/readingList") //将所有处理器方法映射到“/”这个url路径
public class ReadingListController {
    private static final String reader = "craig";
    private ReadingListRepository readingListRepository;

    @Autowired
    public ReadingListController(ReadingListRepository readingListRepository) {
        this.readingListRepository = readingListRepository;
    }

    @RequestMapping( method = RequestMethod.GET)
    public String readersBooks(Model model) {//处理/{reader}的get请求
        List<Book> readingList = readingListRepository.findByReader(reader);
        if (readingList != null) {
            model.addAttribute("books", readingList);
        }
        return "readingList";
    }
    @RequestMapping(method = RequestMethod.POST)
    public  String addToReadingList(Book book) {//处理/{reader}的post请求
        book.setReader(reader);
        readingListRepository.save(book);
        return "redirect:/readingList";

    }
}
