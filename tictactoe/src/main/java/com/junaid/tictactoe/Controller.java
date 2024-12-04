package com.junaid.tictactoe;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin(origins = "https://127.0.0.1:5500")

@RestController
@RequestMapping("/TicTacToe")
public class Controller {

    @PostMapping("/getMove")    
    public int getBestMove(@RequestBody Game game){
        Computer computer = new Computer();
        computer.userMove = game.getComputerMove();
        computer.computerMove = game.getUserMove();

        return computer.analyze(game.getBoard());
    }
}

class Game{
    char[][] board;
    char userMove;
    char computerMove;
    public char[][] getBoard() {
        return board;
    }
    public void setBoard(char[][] board) {
        this.board = board;
    }
    public char getUserMove() {
        return userMove;
    }
    public void setUserMove(char userMove) {
        this.userMove = userMove;
    }
    public char getComputerMove() {
        return computerMove;
    }
    public void setComputerMove(char computerMove) {
        this.computerMove = computerMove;
    }
    
    
}
