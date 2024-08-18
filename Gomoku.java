/* 
Stanley Zhang
Dec 17 2022
This code will run a Gomoku game
 */

import javax.swing.*; //Importing Libarys
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class Gomoku{
    static ImageIcon bcircle = new ImageIcon(new ImageIcon("C:/Users/zhang/Downloads/bcircle.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)); //Decaring variables
    static ImageIcon wcircle = new ImageIcon(new ImageIcon("C:/Users/zhang/Downloads/wcircle.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)); //(If needed changed \ to /)
    static JFrame gameFrame;
    static JLabel nameLabel1, nameLabel2,nameLabel1str, nameLabel2str,player1Score,player2Score,hello;
    static JPanel gamePanelStart,gamePanel,turnPanel;
    static Border bordergrid = BorderFactory.createLineBorder(Color.black,1);
    static JTextField player1, player2;
    static JButton [][] buttons = new JButton [15][15];
    static JButton loop,gridB,loopSame;
    static int [][] grid = new int [15][15];
    static boolean playerTurn = false;
    static String player2str,player1str;
    static int win, count =15, winner=0, player1Stones, player2Stones,p1,p2;
    public static void main(String[]args){
        gameFrame = new JFrame("Gomoku"); //Creating Frame for enitre game
        gameFrame.setSize(900,900);
        gamePanelStart = new JPanel(); //Creating panel for starting the game(entering names)
        gameFrame.add(gamePanelStart);
        mainPage();
        gameFrame.setVisible(true);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       
    }
   
      public static void mainPage(){ //Method that creates all the labels and textfields for startpanel
         gamePanelStart.setLayout(new GridLayout(6,1));   

         hello = new JLabel(); //Label that displays a welcome
         hello.setText("WELCOME");
         gamePanelStart.add(hello);


        nameLabel1 = new JLabel(); //Label and textfield that prompts user number 1 to enter their name
        nameLabel1.setText("ENTER PLAYER 1 NAME");
        gamePanelStart.add(nameLabel1);
        player1 = new JTextField(10);
        gamePanelStart.add(player1);

        nameLabel2 = new JLabel();//Label and textfield that prompts user number 2 to enter their name
        nameLabel2.setText("ENTER PLAYER 2 NAME");
        gamePanelStart.add(nameLabel2);
        player2 = new JTextField(10);
        gamePanelStart.add(player2);

        gridB = new JButton("START!"); //Button that will run the game if clicked
        gamePanelStart.add(gridB);
        gridB.addActionListener(new Grid());

        gamePanelStart.setVisible(true);
    }
   
    public static void clearStart(){ //Clears the start panels 
    gamePanelStart.removeAll();
    gamePanelStart.revalidate();
    }
    public static void clearPanel(){//Clears the game panels
    turnPanel.removeAll();
    gamePanel.removeAll();
    turnPanel.revalidate();
    gamePanel.revalidate();
    }

    public static void Grid(){ //Method that creates gomoku grid 
        clearStart();
        gamePanel = new JPanel();
        turnPanel = new JPanel();
        gameFrame.add(turnPanel, BorderLayout.SOUTH);
        gamePanel.setLayout(new GridLayout(16,15)); //creates grid
        gameFrame.add(gamePanel);  
       
    for (int row = 0; row < 15; row++){ //initialize grid
        for(int col = 0; col < 15; col++){
            grid[row][col] = 0;
        }
    }
   

    for (int row = 0; row < 15; row++){ //creates buttons for board
        for(int col = 0; col < 15; col++){
        buttons[row][col] = new JButton();
        buttons[row][col].setBorder(bordergrid); //Sets the board colour to monochromatic 
        buttons[row][col].setBackground(Color.white);
        buttons[row][col].addActionListener(new playerMove());
        gamePanel.add(buttons[row][col]);
        }
     
    }
    player1str = player1.getText(); //prompts whos turn it is
    player2str = player2.getText();
    nameLabel1str = new JLabel();
    nameLabel1str.setText(player1str+"'s turn |");
    nameLabel2str = new JLabel();
    nameLabel2str.setText(player2str+"'s turn |");
    turnPanel.add(nameLabel1str);
    turnPanel.add(nameLabel2str);
        
    player1Score = new JLabel(); //prompts the score
    player1Score.setText(player1str+"'s Score:"+ p1+" |");
    turnPanel.add(player1Score);
    player2Score = new JLabel();
    player2Score.setText(player2str+"'s Score:"+ p2 +" |");
    turnPanel.add(player2Score);


    loop = new JButton(); //Adds buttons to play again with same players for different players 
    loop.setText("Play again w/ new players");
    loop.addActionListener(new playAgain());
    turnPanel.add(loop);
    loopSame = new JButton();
    loopSame.setText("Play again");
    loopSame.addActionListener(new playAgainSame());
    turnPanel.add(loopSame);

gamePanel.setVisible(true);  
turnPanel.setVisible(true);
nameLabel2str.setVisible(false);

    }
   
   
    static class Grid implements ActionListener{ //Runs if start button is clicked on start panel
        public void actionPerformed(ActionEvent event){
            Grid();
        }
    }

    public static class playAgainSame implements ActionListener{ //Runs if play again is clicked on game panel, will rerun the game panel
        public void actionPerformed(ActionEvent event){
            clearPanel();
            playerTurn = false; //resets values
            player1Stones = 0;
            player2Stones = 0;
            winner = 0;
            Grid();

        }
    }

   
    public static class playAgain implements ActionListener{//Runs if play again w/Diffeernt players is clicked on game panel. will rerun the start panel
        public void actionPerformed(ActionEvent event){
    clearPanel();
    playerTurn = false;//reset values
    winner = 0;
    player1Stones = 0;
    player2Stones = 0;
    p1 =0;
    p2 =0;
    mainPage();
    }
    }


    static class playerMove implements ActionListener{ //Runs if button on grid/board is clicked
        public void actionPerformed(ActionEvent event){
                for (int row=0;row<15;row++){ //runs for the whole board
                    for(int col = 0; col<15; col++){
                        if(winner == 0){ //checks if there is a winner 
                            if (event.getSource() == buttons[row][col]){ //gets position where button is clicked
                                    if(playerTurn){ //checks who's turn it is 
                                    nameLabel2str.setVisible(false); //Shows who's turn it is
                                    nameLabel1str.setVisible(true);
                                        if(grid[row][col] == 0 ){ //check if someone already clicked on it
                                        player1Stones+= 1; // adds 1 to the number of stones player 1 placed
                                            buttons[row][col].setIcon(bcircle); //add circle image to button 
                                            grid[row][col] = 1; //sets the value of button to 1 
                                            winnervertical(); //checks if there is a winner
                                            winnerhorizontal();
                                            winnerdiagonal();   
                                            playerTurn = false; //Switches player turn
                                        }
                                    }
                                    else {//player 2 turn
                                    nameLabel1str.setVisible(false);
                                    nameLabel2str.setVisible(true);
                                        if(grid[row][col] == 0 ){
                                        player2Stones += 1;
                                            buttons[row][col].setIcon(wcircle);
                                            grid[row][col] = 2;
                                            winnervertical();
                                            winnerhorizontal();
                                            winnerdiagonal();
                                            playerTurn = true;
                                        }
                                    }
                                }
                            }
                            else if (winner == 1||winner==2){ //if there is a winner run the winner method and set the winner value to 3 
                                winner();
                                winner=3;
                            }
                        }  
                    }
                }
         }

    public static void winner(){
    if(winner == 1 || winner ==2){ //checks if there is a winner
        nameLabel1str.setVisible(false);//hides the turn
        nameLabel2str.setVisible(false);

             if (winner == 1){//prompts the winner and number of stones placed
             JLabel winnername = new JLabel(player2str + " has won the game with a total number of  " +(player1Stones+player2Stones)+ " stones placed!");
             turnPanel.add(winnername);
             p2 +=1; //adds one to the winners score
             }
             else if (winner == 2){
             JLabel winnername = new JLabel(player1str + " has won the game with a total number of "+(player1Stones+player2Stones)+ " stones placed!");
             turnPanel.add(winnername);
             p1 +=1;
             }
         }
    }

    public static int winnerhorizontal(){ //checks for horizontal 5 in a row
        for (int row=0;row<15;row++){ //row check player 1
            for(int col = 0; col<15; col++){
                if(grid[row][col]==1){//checks if there is a piece
                    win+=1; //adds one to win
                    if(win ==5){ //if win is 5 then there is winner
                        winner = 1; 
                    }
                }
                else{
                    win =0;
                }
            }
        }
        for (int row=0;row<15;row++){ //row check player 2
            for(int col = 0; col<15; col++){
                if(grid[row][col]==2){
                    win+=1;
                    if(win ==5){
                        winner = 2;
                    }
                }
                else{
                    win =0;
                }
            }
        }
        return winner;
    }
    public static int winnervertical(){ // checks for vertical 5 in a row
        for (int col=0;col<15;col++){ //col check player1
            for(int row = 0; row<15; row++){
                if(grid[row][col]==1){
                    win+=1;
                    if(win ==5){
                        winner = 1;
                    }
                }
                else{
                    win =0;
                }
            }
        }
        for (int col=0;col<15;col++){ //col check player2
            for(int row = 0; row<15; row++){
                if(grid[row][col]==2){
                    win+=1;
                    if(win ==5){
                        winner = 2;
                    }
                }
                else{
                    win =0;
                }
 
            }
        }
        return winner;
    }

    public static int winnerdiagonal(){ //checks for diagonal 5 in a row
        count =15;
        for (int row=0;row<15;row++){ //diagonal check top left (ascending) of board player1
            for(int col = 0; col<count; col++){
                if(grid[col][col+row]==1){
                    win+=1;
                    if(win ==5){
                        winner = 1;
                    }
                }
                else{
                    win =0;
                }
            }
            count -= 1;
        }
        count =15;
        for (int row=0;row<15;row++){ //diagonal check bottom right(descending) of board player1
            for(int col = 0; col<count; col++){
                if(grid[col+row][col]==1){
                    win+=1;
                    if(win ==5){
                        winner = 1;
                    }
                }
                else{
                    win =0;
                }
            }
            count -= 1;
        }  
        count =15;
        for (int row=0;row<count;row++){ //diagonal check top left(ascending) to right player1
            for(int col = 0; col<count; col++){
                if(grid[14-col][col]==1){
                    win+=1;
                    if(win == 5){
                        winner = 1;
                    }
                }
                else{
                    win =0;
                }
            }
            count -= 1;
        }
        count =15;
         for (int row=0;row<count;row++){ //diagonal check bottom left(descending) player1
            for(int col = 0; col<(count-1); col++){
                if(grid[14-col][col+row]==1){
                    win+=1;
                    if(win ==5){
                        winner = 1;
                    }
                }
                else{
                    win =0;
                }
            }
            count -= 1;
        }
        count =15;
        for (int row=0;row<15;row++){ //diagonal check top left (ascending) of board player2
            for(int col = 0; col<count; col++){
                if(grid[col][col+row]==2){
                    win+=1;
                    if(win ==5){
                        winner = 2;
                    }
                }
                else{
                    win =0;
                }
            }
            count -= 1;
        }
        count =15;
        for (int row=0;row<15;row++){ //diagonal check bottom right(descending) of board player2
            for(int col = 0; col<count; col++){
                if(grid[col+row][col]==2){
                    win+=1;
                    if(win ==5){
                        winner = 2;
                    }
                }
                else{
                    win =0;
                }
            }
            count -= 1;
        }  
        count =15;
        for (int row=0;row<count;row++){ //diagonal check top left(ascending) to right player2
            for(int col = 0; col<count; col++){
                if(grid[14-col][col]==2){
                    win+=1;
                    if(win == 5){
                        winner = 2;
                    }
                }
                else{
                    win =0;
                }
            }
            count -= 1;
        }

        count =15;
         for (int row=0;row<count;row++){//diagonal check bottom left(descending) player2
            for(int col = 0; col<(count-1); col++){
                if(grid[14-col][col+row]==2){
                    win+=1;
                    if(win ==5){
                        winner = 2;
                    }
                }
                else{
                    win =0;
                }
            }
            count -= 1;
        }
        return winner; //returns the values of winner (if 1, player 1 wins, if 2 player 2 wins, if 0, no winner yet)
    }
}


