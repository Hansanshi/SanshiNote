package ink.hansanshi.note.controller;

import ink.hansanshi.note.dto.RemoteRepoRequest;
import ink.hansanshi.note.dto.ServerResponse;
import ink.hansanshi.note.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author hansanshi
 * @date 2020/1/26
 */
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private IAdminService adminService;

    @PutMapping("remote")
    public ServerResponse setRemoteRepoUrl(@RequestBody RemoteRepoRequest request){
        return adminService.setRemoteRepoUrl(request.getRemoteRepoUrl());
    }

    @GetMapping("remote")
    public ServerResponse getRemoteRepoUrl(){
        return adminService.getRemoteRepoUrl();
    }

    @PostMapping("sshkey")
    public ServerResponse<String> genSshKeys(){
        return adminService.generateSshKey();
    }

    @DeleteMapping("push")
    public ServerResponse stopPushToRemote(){
        return adminService.stopPushToRemoteRepo();
    }

    @GetMapping("push")
    public ServerResponse checkPushStatus(){
        return adminService.checkPushTaskStatus();
    }

    @PostMapping("push")
    public ServerResponse startPushToRemote(){
        return adminService.startPushToRemoteRepo();
    }
}
