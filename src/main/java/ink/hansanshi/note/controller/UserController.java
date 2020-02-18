package ink.hansanshi.note.controller;

import ink.hansanshi.note.dto.ServerResponse;
import ink.hansanshi.note.dto.UserRequest;
import ink.hansanshi.note.service.IUserService;
import ink.hansanshi.note.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author hansanshi
 * @date 2019/12/26
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("login")
    public ServerResponse<UserVo> login(@RequestBody UserRequest request){
        return userService.login(request.getUsername(), request.getPassword());
    }

    @PostMapping("validate")
    public ServerResponse validate(){
        // Validate by interceptor
        return ServerResponse.buildSuccessResponse();
    }

    @PostMapping("")
    public ServerResponse<UserVo> register(@RequestBody UserRequest request){
        return userService.register(request.getUsername(), request.getPassword());
    }

    @PostMapping("/changePass")
    public ServerResponse changePassword(@RequestBody UserRequest request){
        return userService.changePassword(request.getPassword(), request.getNewPassword());
    }


    @PostMapping("/logout")
    public ServerResponse logout(){
        return userService.logout();
    }
}
