import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.*;

public class 극장좌석 {
    public static void main(String[] args) throws Exception{
        new 극장좌석().solution();
    }

    int total;
    int[] fixList;
    int fixedNum;
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        total = Integer.parseInt(br.readLine());
        fixedNum = Integer.parseInt(br.readLine());
        fixList = new int[fixedNum+1];

        int[] caseList = new int[40+1];
        caseList[0] = 1;
        caseList[1] =1;
        caseList[2] = 2;
        caseList[3] = 3;
        for(int i = 4 ; i <= total; i++){
            caseList[i] = caseList[i-1] + caseList[i-2];
        }

        long res = 1;
        for(int i  =1; i <= fixedNum; i++){
            int num = Integer.parseInt(br.readLine());
            fixList[i] = num;
        }

        for(int i  =1; i <= fixedNum; i++){
           int index = fixList[i] -fixList[i-1] -1;
           res = res *caseList[index];
        }

        res = res *caseList[total - fixList[fixedNum]];
        System.out.println(res);

    }
}
