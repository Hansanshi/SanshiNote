package ink.hansanshi.note.service;

import ink.hansanshi.note.dto.ServerResponse;
import ink.hansanshi.note.vo.DraftNoteVo;

import java.util.List;

/**
 * @author hansanshi
 * @date 2020/2/23
 */
public interface IDraftNoteService {
    ServerResponse<List<DraftNoteVo>> getDraftNotes();

    ServerResponse saveDraftNote(String notebookName, String title, String content);

    ServerResponse deleteDraftNote(String notebookName, String title);

    ServerResponse deleteDraftNotes();
}
