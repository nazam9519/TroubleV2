import java.util.Scanner;

/**
 * Created by nabil on 5/2/15.
 */

/**
 * @author Nabil Azam
 * Created Game Environment
 */
public class Game {
        Map map;  //Map
        int playerPos1; //Location of Player 1
        int playerPos2; //Location of Player 2
        String[] goAndStop = new String[2];   //Move or Halt
        String[] playerName = new String[2];  //Game Role

        /**
         * Game initilization
         */
        public void init() {
            map = new Map();
            map.createMap();  //Generate A Board
            playerPos1 = 0;   //Starting Position P1
            playerPos2 = 0;   //P2 Starting Position
            goAndStop[0] = "on";  //Go Next or Stop Player 1
            goAndStop[1] = "on";  //Go Next or stop Player 2
        }


        /**
         * Play
         */
        public void start() {
            //Initialize
            init();
            System.out.println("※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※");
            System.out.println("//                                                //");
            System.out.println("//                                                //");
            System.out.println("//             Trouble                            //");
            System.out.println("//                                                //");
            System.out.println("//                                                //");
            System.out.println("※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※\n\n\n");


            System.out.println("\n~~~~~~~~~~~~~~~~~~~Two Man Competition~~~~~~~~~~~~~~~~~~~");
            System.out.println("\nSelect: 1. Fire 2. Eisenhower 3. Phil 4. John");
            Scanner input = new Scanner(System.in);
            System.out.print("Please Select A Role Player 1:  ");
            int role1 = input.nextInt();
            int role2;
            do {
                System.out.print("Please Select A Role Player 2： ");
                role2 = input.nextInt();  //Choose Roles
            } while (role2 == role1);  //Make Sure to not Repeat Roles
            setRole(1, role1);   //Set 1 Represents Role Players
            setRole(2, role2);   //Set 2 Representatives of Roles
            play();   //Begin
        }

        /**
         * Setting Characteristics
         *
         * @param no   Player Order 1：Player2 Order
         * @param role Character Code
         */
        public void setRole(int no, int role) {
            switch (role) {
                case 1:
                    playerName[no - 1] = "Fire";
                    break;
                case 2:
                    playerName[no - 1] = "Eisenhower";
                    break;
                case 3:
                    playerName[no - 1] = "Phil";
                    break;
                case 4:
                    playerName[no - 1] = "John";
                    break;
                default:
                    break;
            }
        }


        /**
         * Both Games Are Played
         */
        public void play() {
            System.out.println("\n\n\n\n");

            System.out.print("\n\n****************************************************\n");
            System.out.print("                     Game  Start                    \n");
            System.out.print("****************************************************\n\n");

            //Randomly Move
            System.out.println("^_^" + playerName[0] + "GO：　Ａ");
            System.out.println("^_^" + playerName[1] + "GO：  Ｂ\n");

            //Display Current Status of Map
            System.out.println("\nLegend： " + "■ Timeout  ¤ Chance   ★ Mine   〓 Time tunnel   ∷ General\n");

            map.showMap(playerPos1, playerPos2);


            //Game Starts
            int step;  //Number Storage
            while (playerPos1 < 99 && playerPos2 < 99) {

                //Dice Roll
                if (goAndStop[0].equals("on")) {
                    //Dice For Player 1
                    step = throwShifter(1);   //Dice
                    System.out.println("\n-----------------");  //Results
                    System.out.println("Dice Number： " + step);
                    playerPos1 = getCurPos(1, playerPos1, step);   //Calculate the current position after the movement this time
                    System.out.println("\nCurrent Position：  " + playerPos1);
                    System.out.println("Other Location：" + playerPos2);
                    System.out.println("-----------------\n");
                    map.showMap(playerPos1, playerPos2); //Display the current map
                    if (playerPos1 == 99) {  //End
                        break;   //Drop out
                    }
                } else {
                    System.out.println("\n" + playerName[0] + "STOP！\n");   //Pause Information Display
                    goAndStop[0] = "on";   //Move to the Next State
                }

                System.out.println("\n\n\n\n");

                if (goAndStop[1].equals("on")) {
                    //Player 2 Dice
                    step = throwShifter(2); //Dice
                    System.out.println("\n-----------------"); //Results
                    System.out.println("Dice Number： " + step);
                    playerPos2 = getCurPos(2, playerPos2, step);   //Calculate the current position after the movement this time
                    System.out.println("\nCurrent Position：  " + playerPos2);
                    System.out.println("Other Location：" + playerPos1);
                    System.out.println("-----------------\n");
                    map.showMap(playerPos1, playerPos2);
                    if (playerPos2 == 99) {  //End
                        break;   //Retire
                    }
                } else {
                    System.out.println("\n" + playerName[1] + "Stop Throwing Time！\n");  //Display Information During GamePause
                    goAndStop[1] = "on";  //Set The Next State Can Throw
                }
                System.out.println("\n\n\n\n");
            }

            //Game Over
            System.out.println("\n\n\n\n");
            System.out.print("****************************************************\n");
            System.out.print("                      Game  Over                    \n");
            System.out.print("****************************************************\n\n");
            judge();
        }


        /**
         * Dice
         *
         * @param no Player Orders
         * @return step The number or throws of the dice
         */
        public int throwShifter(int no) {
            int step = 0;
            System.out.print("\n\n" + playerName[no - 1] + ", Please Press the Enter Key to start any letter dice： ");
            Scanner input = new Scanner(System.in);
            String answer = input.next();
            step = (int) (Math.random() * 10) % 6 + 1;   //Random Number
            return step;
        }


        /**
         * The calculation of the current position of the player after the movement
         *
         * @param no       PlayerOrder
         * @param position Moving Forward
         * @param step     The Number OF the Dice Throw
         * @return position Position After The Movement
         */
        public int getCurPos(int no, int position, int step) {
            position = position + step;  //The first Position after movement
            if (position >= 99) {
                return 99;
            }
            Scanner input = new Scanner(System.in);
            switch (map.map[position]) {   //Be judged According to the map of the level code
                case 0:    //Normal Cells Go
                    if (no == 1 && playerPos2 == position) {   //Player1 Encounter with the other Player
                        playerPos2 = 0;    //You have been overtaken
                        System.out.println(":-D  HAHAHA Return to the Beginning！");
                    }
                    if (no == 2 && playerPos1 == position) { //Player 2 Encounter with the other Player
                        playerPos1 = 0;    //You have been overtake, to the beginning
                        System.out.println(":-D  HAHAHA Back to The Beggining！");
                    }
                    break;
                case 1:   //Get Lucky
                    System.out.println("\n◆◇◆◇◆Welcome to Chance◆◇◆◇◆");
                    System.out.println("   Please Select Your Luck：");
                    System.out.println("   1. Exchange Position  2. Kaboom");
                    System.out.println("=============================\n");
                    int choice = input.nextInt();
                    int temp;
                    switch (choice) {
                        case 1:
                            if (no == 1) {
                                temp = position;
                                position = playerPos2;
                                playerPos2 = temp;
                            } else if (no == 2) {
                                temp = position;
                                position = playerPos1;
                                playerPos1 = temp;
                            }
                            break;
                        case 2:
                            if (no == 1 && playerPos2 < 6) {
                                playerPos2 = 0;
                            } else {
                                playerPos2 = playerPos2 - 6;
                            }
                            if (no == 2 && playerPos2 < 6) {
                                playerPos1 = 0;
                            } else {
                                playerPos1 = playerPos1 - 6;
                            }
                            break;
                    }
                    System.out.println(":~)  " + "I have to Cry of Joy");
                    break;
                case 2:   //Out Of Bounds
                    position = position - 6; //Move Back
                    System.out.println("~:-(  " + "Sending You Back");
                    break;
                case 3:  //Pause
                    goAndStop[no - 1] = "off";  //Set Next Pause DIce
                    System.out.println("~~>_<~~  For One Round");
                    break;
                case 4:   //Time Lapse
                    position = position + 10;  //Easy Move Forward
                    System.out.println("|-P  " + "Restart！");
                    break;
            }

            //Return Coordinates
            if (position < 0) {
                return 0;
            } else if (position > 99) {
                return 99;
            } else {
                return position;
            }
        }

        /**
         * Results
         */
        public void judge() {
            if (playerPos1 > playerPos2) {
                System.out.println("\nCongratulations" + playerName[0] + "General, You Win！");
            } else {
                System.out.println("\nCongratulations" + playerName[1] + "General, You Win！");
            }
        }
    }

