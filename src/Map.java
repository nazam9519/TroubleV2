

/**
 * Created by nabil on 5/2/15.
 */

    public class Map {
        int[] map = new int[100];   //Game Board
        int[] luckyTurn = {6, 23, 40, 55, 69, 83};
        int[] landMine = {5, 13, 17, 33, 38, 50, 64, 80, 94};
        int[] pause = {9, 27, 60, 93};         //Pause Function
        int[] timeTunnel = {20, 25, 45, 63, 72, 88, 90};   //Time Lapse

        /**
         * Generate A Map:
         * Level Code:1 Chance 2Mine  3: TimeOut 4Lapse 0Average
         */
        public void createMap() {
            int i = 0;

            //Set Luck Based Input
            for (i = 0; i < luckyTurn.length; i++) {
                map[luckyTurn[i]] = 1;
            }

            //Boundaries
            for (i = 0; i < landMine.length; i++) {
                map[landMine[i]] = 2;
            }

            //Pause
            for (i = 0; i < pause.length; i++) {
                map[pause[i]] = 3;
            }

            //Time Lapse
            for (i = 0; i < timeTunnel.length; i++) {
                map[timeTunnel[i]] = 4;
            }
        }

        /**
         * Level
         *
         * @param i          Current Context
         * @param index      Position Index
         * @param playerPos1 Player 1 Position
         * @param playerPos2 Player2
         * @return
         */
        public String getGraph(int i, int index, int playerPos1, int playerPos2) {
            String graph = "";
            if (index == playerPos1 && index == playerPos2) {
                graph = "@@";
            } else if (index == playerPos1) {
                //graph = "♀";
                graph = "Ａ";
            } else if (index == playerPos2) {
                //graph = "♂";
                graph = "Ｂ";
            } else {
                switch (i) {
                    case 1:   //Luck
                        graph = "¤";
                        break;
                    case 2:   //Mine
                        graph = "★";
                        break;
                    case 3:   //Time Out
                        graph = "■";
                        break;
                    case 4:   //Time Lapse
                        graph = "〓";
                        break;
                    default:
                        graph = "∷";
                        break;
                }
            }
            return graph;
        }

        /**
         * Output Map Lines
         *
         * @param start      Output starting position on the board
         * @param end        The end point
         * @param playerPos1 Player 1 current position
         * @param playerPos2 Player 2 current Position
         */
        public void showLine1(int start, int end, int playerPos1, int playerPos2) {
            for (int i = start; i < end; i++) {
                System.out.print(getGraph(map[i], i, playerPos1, playerPos2));
            }
        }

        /**
         * Player 2
         *
         * @param start      Starting Position
         * @param end        The End Point
         * @param playerPos1 Current Position for Player
         * @param playerPos2
         */
        public void showLine2(int start, int end, int playerPos1, int playerPos2) {
            for (int i = end - 1; i >= start; i--) {
                System.out.print(getGraph(map[i], i, playerPos1, playerPos2));
            }
        }

        /**
         * Right Vertical Column
         *
         * @param start      Output Starting Pos
         * @param end        End Pos
         * @param playerPos1
         * @param playerPos2
         */
        public void showRLine(int start, int end, int playerPos1, int playerPos2) {
            for (int i = start; i < end; i++) {
                for (int j = 28; j > 0; j--) {  //Output all spaces
                    System.out.print("  ");
                }
                System.out.print(getGraph(map[i], i, playerPos1, playerPos2));
                System.out.println();
            }
        }

        /**
         *
         *
         * @param start
         * @param end
         * @param playerPos1
         * @param playerPos2
         */
        public void showLLine(int start, int end, int playerPos1, int playerPos2) {
            for (int i = start; i < end; i++) {
                System.out.println(getGraph(map[i], i, playerPos1, playerPos2));
            }
        }

        /**
         * Display main Board
         *
         * @param playerPos1
         * @param playerPos2
         */
        public void showMap(int playerPos1, int playerPos2) {
            showLine1(0, 31, playerPos1, playerPos2);   //First Line
            System.out.println();                     //Wrap
            showRLine(31, 35, playerPos1, playerPos2);  //Vertical
            showLine2(35, 66, playerPos1, playerPos2); //Second Line
            System.out.println();                     //Wrap
            showLLine(66, 69, playerPos1, playerPos2); //Left Vertical
            showLine2(69, 100, playerPos1, playerPos2); //3RD Line
        }
    }



