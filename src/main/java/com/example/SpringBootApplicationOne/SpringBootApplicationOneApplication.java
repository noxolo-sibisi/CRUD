package com.example.SpringBootApplicationOne;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RequestMapping("/spring-boot")
@RestController
public class SpringBootApplicationOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplicationOneApplication.class, args);
    }

private List<String> shoppingList = new ArrayList<>();

    @GetMapping("/add/{item}")
    public String addItem(@RequestParam String item){
        shoppingList.add(item);
        return "✅ added " + item + " List: " +shoppingList;
    }
    @GetMapping("/list")
    public List<String> view(){
        return shoppingList;
    }

    @GetMapping("/item/{position}")
    public String getItem(@PathVariable int position){
        if (position < shoppingList.size()){
            return "Item at position " +position+": "+shoppingList.get(position);
        }
        return "❌ No item at position "+ position;
    }

    @GetMapping("/remove/{position}")
    public String removeItem(@PathVariable int position){
        if(position < shoppingList.size()){
            String removed = shoppingList.remove(position);
            return " Removed: " + removed + "| New list"+ shoppingList;
        }
        return  "❌ cannot remove-no item at position "+ position;
    }

    @GetMapping("/count")
    public String countItem(){
        return  "You have "+ shoppingList.size() + "items in your list";
    }







}








