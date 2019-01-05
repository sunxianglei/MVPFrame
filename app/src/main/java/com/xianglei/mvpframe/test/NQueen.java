package com.xianglei.mvpframe.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sunxianglei
 * @date 2019/1/4
 */

public class NQueen {

    List<List<String>> list = new ArrayList<>();
    List<String> item = new ArrayList<>();

    public void printResult(int n){
        solveNQueens(n);

        for(List<String> item: list){
            for(String temp: item){
                System.out.print(temp);
            }
        }
    }

    public List<List<String>> solveNQueens(int n) {
        list.clear();
        int[][] status = new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++)
                status[i][j] = 0;
        }
        trackQueen(0, n, status);
        return list;
    }

    private void trackQueen(int nowRow, int n, int[][] status){
        if(nowRow == n){
            addItem(n, status);
            return ;
        }

        for(int col=0;col<n;col++){
            if(isOk(nowRow, col, n, status) && status[nowRow][col] == 0){
                status[nowRow][col] = 1;
                trackQueen(nowRow + 1, n, status);
                status[nowRow][col] = 0;
            }
        }
    }

    private boolean isOk(int nowRow, int col, int n, int[][] status){
        for(int i=0;i<nowRow;i++){
            for(int j=0;j<n;j++){
                if(status[i][j] == 1){
                    if( i == nowRow || j == col || i+j == nowRow+col
                            || i-j == nowRow-col){
                        return false;
                    }
                    break;
                }
            }
        }
        return true;
    }

    private void addItem(int n, int[][] status){
        item.clear();
        for(int i=0;i<n;i++){
            StringBuilder temp = new StringBuilder();
            if(i == 0){
                temp.append('[');
            }
            temp.append("\"");
            for(int j=0;j<n;j++)
                if(status[i][j] == 1){
                    temp.append("Q");
                }else{
                    temp.append(".");
                }
            if(i == n-1){
                temp.append("],");
            }else{
                temp.append("\",");
            }
            item.add(temp.toString());
        }
        list.add(item);
    }

}
