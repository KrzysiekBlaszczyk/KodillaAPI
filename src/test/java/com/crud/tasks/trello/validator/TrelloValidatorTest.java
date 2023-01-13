package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class TrelloValidatorTest {
    @Autowired
    private TrelloValidator trelloValidator;

    @Test
    void testValidateTrelloBoards(){
        //given
        TrelloBoard board1 = new TrelloBoard("1","test", new ArrayList<>());
        TrelloBoard board2 = new TrelloBoard("2","board", new ArrayList<>());
        List<TrelloBoard> trelloBoard = new ArrayList<>();
        trelloBoard.add(board1);
        trelloBoard.add(board2);
        //when
        List<TrelloBoard> validateBoards = trelloValidator.validateTrelloBoards(trelloBoard);
        //then
        assertEquals(1, validateBoards.size());

    }
}
