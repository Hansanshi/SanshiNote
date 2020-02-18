package ink.hansanshi.note.service;

import ink.hansanshi.note.dto.ServerResponse;

/**
 * 后台管理服务
 * @author hansanshi
 * @date 2020/1/27
 * @description TODO
 */
public interface IAdminService {

    ServerResponse setRemoteRepoUrl(String remoteRepoUrl);

    ServerResponse stopPushToRemoteRepo();

    ServerResponse<String> generateSshKey();

    ServerResponse checkPushTaskStatus();

    ServerResponse startPushToRemoteRepo();

    ServerResponse getRemoteRepoUrl();
}
