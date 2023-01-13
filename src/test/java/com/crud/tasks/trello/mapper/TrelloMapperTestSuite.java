package com.crud.tasks.trello.mapper;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class TrelloMapperTestSuite {
    @Autowired
    TrelloMapper trelloMapper;

    @Test
    public void testMapToBoards() {
        //Given
        List<TrelloBoardDto> trelloBoardDto = new ArrayList<>();
        List<TrelloListDto> trelloListDto = new ArrayList<>();
        trelloListDto.add(new TrelloListDto("1", "TestList", false));
        trelloBoardDto.add(new TrelloBoardDto("1", "TestBoard", trelloListDto));
        //When
        List<TrelloBoard> trelloBoard = trelloMapper.mapToBoards(trelloBoardDto);
        //Then
        assertEquals("TestBoard", trelloBoard.get(0).getName());
        assertEquals(1, trelloBoard.get(0).getLists().size());
    }
    @Test
    public void testMapToBoardsDto() {
        //Given
        List<TrelloBoard> trelloBoard = new ArrayList<>();
        List<TrelloList> trelloList = new ArrayList<>();
        trelloList.add(new TrelloList("1", "TestList", false));
        trelloBoard.add(new TrelloBoard("1", "TestBoard", trelloList));
        //When
        List<TrelloBoardDto> trelloBoardDto = trelloMapper.mapToBoardsDto(trelloBoard);
        //Then
        assertEquals("TestBoard", trelloBoardDto.get(0).getName());
        assertEquals(1, trelloBoardDto.get(0).getLists().size());
    }
    @Test
    public void testMapToList() {
        //Given
        List<TrelloListDto> trelloListDto = new ArrayList<>();
        trelloListDto.add(new TrelloListDto("1", "TestList", false));
        //When
        List<TrelloList> trelloList = trelloMapper.mapToList(trelloListDto);
        //Then
        assertEquals("TestList", trelloList.get(0).getName());
        assertEquals(1, trelloList.size());
    }
    @Test
    public void testMapToListDto() {
        //Given
        List<TrelloList> trelloList = new ArrayList<>();
        trelloList.add(new TrelloList("1", "TestList", false));
        //When
        List<TrelloListDto> trelloListDto = trelloMapper.mapToListDto(trelloList);
        //Then
        assertEquals("TestList", trelloListDto.get(0).getName());
        assertEquals(1, trelloList.size());
    }
    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("TestCard", "NewCard", "pos", "1");
        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertEquals("TestCard", trelloCardDto.getName());
        assertEquals("NewCard", trelloCardDto.getDescription());
        assertEquals("pos", trelloCardDto.getPos());
        assertEquals("1", trelloCardDto.getListId());
    }
    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("TestCard", "NewCard", "pos", "1");
        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertEquals("TestCard", trelloCard.getName());
        assertEquals("NewCard", trelloCard.getDescription());
        assertEquals("pos", trelloCard.getPos());
        assertEquals("1", trelloCard.getListId());
    }
}
