package ink.hansanshi.note.service.impl;

import ink.hansanshi.note.dao.DraftNoteRepository;
import ink.hansanshi.note.dto.ServerResponse;
import ink.hansanshi.note.entity.DraftNoteDo;
import ink.hansanshi.note.service.IDraftNoteService;
import ink.hansanshi.note.util.DateTimeUtil;
import ink.hansanshi.note.util.ThreadLocalUtil;
import ink.hansanshi.note.vo.DraftNoteVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author hansanshi
 * @date 2020/2/23
 * @description TODO
 */
@Service
public class DraftNoteServiceImpl implements IDraftNoteService {

    @Autowired
    private DraftNoteRepository draftNoteRepository;

    @Override
    public ServerResponse<List<DraftNoteVo>> getDraftNotes(){
        List<DraftNoteDo> draftNoteDoList = draftNoteRepository.findByUsername(getUsername());
        List<DraftNoteVo> draftNoteVoList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(draftNoteDoList)){
            draftNoteDoList.forEach( draftNoteDo -> {
                DraftNoteVo draftNoteVo = new DraftNoteVo();
                draftNoteVo.setId(draftNoteDo.getId())
                        .setNotebookName(draftNoteDo.getNotebookName())
                        .setTitle(draftNoteDo.getTitle())
                        .setContent(draftNoteDo.getContent())
                        .setUpdateTime(DateTimeUtil.dateToStr(draftNoteDo.getUpdateTime()));
                draftNoteVoList.add(draftNoteVo);
            });
        }
        return ServerResponse.buildSuccessResponse(draftNoteVoList);
    }

    @Override
    public ServerResponse saveDraftNote(String notebookName, String title, String content) {
        draftNoteRepository.save(new DraftNoteDo()
                .setUsername(getUsername())
                .setContent(content)
                .setNotebookName(notebookName)
                .setUpdateTime(new Date())
                .setTitle(title));
        return ServerResponse.buildSuccessResponse();
    }


    @Override
    public ServerResponse deleteDraftNote(String notebookName, String title) {
        draftNoteRepository.deleteByUsernameAndNotebookNameAndTitle(getUsername(),notebookName, title);
        return ServerResponse.buildSuccessResponse();
    }

    @Override
    public ServerResponse deleteDraftNotes() {
        draftNoteRepository.deleteByUsername(getUsername());
        return ServerResponse.buildSuccessResponse();
    }


    private String getUsername(){
        return ThreadLocalUtil.getUsername();
    }
}
