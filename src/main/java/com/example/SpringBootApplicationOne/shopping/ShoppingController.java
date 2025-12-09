package com.example.SpringBootApplicationOne.shopping;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/shopping")
public class ShoppingController {
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
