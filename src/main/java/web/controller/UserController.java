package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.models.Car;
import web.models.User;
import web.service.UserService;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showAllUsers(ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/add_user")
    public String showAddUserForm() {
        return "addUser";
    }

    @PostMapping("/save_user")
    public String saveUser(@RequestParam("firstName") String firstName,
                            @RequestParam("secondName") String secondName,
                            @RequestParam("age") byte age,
                            @RequestParam("model") String model,
                            @RequestParam("number") int number) {
        Car car = new Car(model, number);
        User user = new User(firstName,secondName,car,age);
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/edit_user")
    public String showEditUserForm(@RequestParam("id") int id, ModelMap model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "editUser";
    }

    @PostMapping("/update_user")
    public String updateUser(@RequestParam("id") int id,
                             @RequestParam("firstName") String firstName,
                             @RequestParam("secondName") String secondName,
                             @RequestParam("age") byte age,
                             @RequestParam("model") String model,
                             @RequestParam("number") int number) {
        User updatedUser = userService.getUserById(id);
        updatedUser.setFirstName(firstName);
        updatedUser.setSecondName(secondName);
        updatedUser.setAge(age);
        Car updatedUserCar = updatedUser.getCar();
        updatedUserCar.setModel(model);
        updatedUserCar.setNumber(number);
        userService.updateUser(updatedUser);
        return "redirect:/";
    }

    @PostMapping("/delete_user")
    public String deleteUser(@RequestParam("id") int id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}
