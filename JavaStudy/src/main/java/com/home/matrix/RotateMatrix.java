package com.home.matrix;

public class RotateMatrix {
    public static void main(String a[]){
        char[][] matrix=new char[4][4];
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                matrix[i][j]='0';
            }
            matrix[0][1]=matrix[1][1]=matrix[2][1]=matrix[3][1]=matrix[3][2]=matrix[3][3]='X';
            printMatrix(matrix);
            //rotate
            System.out.println("after rotation it is");
           printMatrix(rotateMatrix(matrix));
        }
    }
    private static char[][] rotateMatrix(char[][] input){
        char[][] rotatedM = new char[4][4];
        for(int i=0;i<4;i++) {
            for (int j = 0; j < 4; j++) {
                rotatedM[j][3-i]=input[i][j];
            }
        }

        return rotatedM;
    }
    private static void printMatrix(char[][] matrix) {
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println("");
        }
    }
}
