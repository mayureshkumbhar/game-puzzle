package game.devilnhuman.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import game.devilnhuman.app.common.Direction;
import game.devilnhuman.app.service.GameService;
import game.devilnhuman.app.vo.GameBoard;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/game")
public class GameController {

	@Autowired
	private GameService gameService;
	
	
	@ApiOperation(value = "Creates new game",response = GameBoard.class)
	@GetMapping(value = "/create", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<GameBoard> create(){
		GameBoard gameBoard = gameService.createGame();
		return new ResponseEntity<>(gameBoard,HttpStatus.OK);
	}
	
	@ApiOperation(value = "Performs moves in game if they are valid",response = GameBoard.class)	
	@PutMapping(value = "/move", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<GameBoard> move(
			@ApiParam(value = "Count of Human going into boat", required = true)
			@RequestParam( value = "humans", required = true) int humans,
			@ApiParam(value = "Count of Devils going into boat", required = true)
			@RequestParam( value = "devils", required = true) int devils,
			@ApiParam(value = "Direction of boat going to travel", required = true)
			@RequestParam( value = "direction", required = true) Direction direction,
			@ApiParam(value = "Current Game status", required = true)
			@RequestBody GameBoard currentBoard){
		GameBoard gameBoard = gameService.move(humans,devils,direction,currentBoard);
		return new ResponseEntity<>(gameBoard,HttpStatus.OK);
	}
}
