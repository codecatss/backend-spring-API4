package Oracle.Partner.Tracker.controllers;

import Oracle.Partner.Tracker.dto.UserDTO;
import Oracle.Partner.Tracker.entities.User;
import Oracle.Partner.Tracker.services.CsvService;
import Oracle.Partner.Tracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private List<CsvService<?>> csvServices;

    @Autowired
    private CsvService<UserDTO> csvService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findbyId(@PathVariable Long id){
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @GetMapping
    public ResponseEntity<List<User>> findAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAllUsers());
    }

    @PostMapping
    public ResponseEntity<User> registerNewUser(@RequestBody UserDTO user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerNewUser(user));
    }

//    @PostMapping("/api/import-csv")
//    public ResponseEntity<Object> importCsv(@RequestParam("file") MultipartFile file){
//        if (file == null || file.isEmpty()) {
//            return ResponseEntity.badRequest().body(null);
//        }
//
//        List<UserDTO> users = csvService.processCsvUser(file, userService);
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("users", users);
//
//        return ResponseEntity.ok(response);
//    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable Long id,
                                             @RequestBody UserDTO userDTO){

        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(id, userDTO));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
