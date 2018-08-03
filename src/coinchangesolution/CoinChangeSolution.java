
package coinchangesolution;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/*
Recursive memoization solution to
https://www.hackerrank.com/challenges/ctci-coin-change/problem
Passes all tests.
Given a number of dollars and an array of denominations of coins, determine how many ways you can make change. 
For example, making change for n=12  dollars from coin denominations [1,2,5,10] there are 15  ways to make change.

Array based solution may perform (slightly) faster but is harder to understand.
*/

public class CoinChangeSolution { //name changed from Solution
    
    static String createKey(int goal,int index) {
        return goal + "_" + index;
        
    }
            
    static Map<String,Long> solved = new HashMap<>();    
        
    static Long ways(int n,List<Integer> coins,int minIndex) {
    String key =createKey(n,minIndex);
    if (solved.containsKey(key)) {       
        return solved.get(key);
    }
    
    Long sol=0L;
    for(int i=minIndex;i<coins.size();i++) {
        int coin = coins.get(i);
        if (coin==n) {            
            sol++;
        }
        else if (coin<n) {                        
            sol+=(ways(n-coin,coins,i));
        }
    }
        solved.put(key,sol);               
        return sol;        
    }
        
    // Complete the ways function below.
    static Long ways(int n, int[] coins) {        
        List<Integer> coinList = new ArrayList<>();
        //remove useless coins
        for(int i=0;i<coins.length;i++) {
            if (coins[i]<=n){
                coinList.add(coins[i]);
            }
        }        
        return ways(n,coinList,0);
    }

    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        // BufferedWriter changed to enable command line use
        // BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        int[] coins = new int[m];

        String[] coinsItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < m; i++) {
            int coinsItem = Integer.parseInt(coinsItems[i]);
            coins[i] = coinsItem;
        }

        Long res = ways(n, coins);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
