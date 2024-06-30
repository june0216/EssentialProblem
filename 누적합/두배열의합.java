import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 두배열의합 {
    public static void main(String[] args) throws Exception{
        new 두배열의합().solution();

    }

    int totalA;
    int totalB;

    List<Long> subA;
    List<Long> subB;

    int T;


    int A[];
    int B[];

    long answer;

    public void solution() throws Exception {

        preprocessing(); // 입력 받기
        calSubSum(); // 합 배열 만들기
        calCnt(); // T와 같은 합이 있는 경우의 수 세기

        System.out.println(answer);


    }

    public void preprocessing() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        // A의 값에 대한
        totalA = Integer.parseInt(br.readLine());
        A = new int[totalA];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < totalA; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        subA = new ArrayList<>();

        // B 값에 대한
        totalB = Integer.parseInt(br.readLine());
        B = new int[totalB];
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < totalB; i++){
            B[i] = Integer.parseInt(st.nextToken());
        }

        subB = new ArrayList<>();
    }

    public void calSubSum(){
        // A에서 만들 수 있는 모든 가능한 합들을 배열에 저장한다.

        for(int i = 0; i < totalA; i++){
            long sum = A[i];
            subA.add(sum);
            for(int j = i+1 ; j < totalA; j++){
                sum+=A[j];
                subA.add(sum);
            }

        }


        // B에서 만들 수 있는 모든 가능한 합들을 배열에 저장한다.
        for(int i = 0; i < totalB; i++){
            long sum = B[i];
            subB.add(sum);
            for(int j = i+1 ; j < totalB; j++){ // 개수
                sum+=B[j];
                subB.add(sum);
            }

        }

        // 투 포인터를 이용하여 값을 찾기 위해 합 배열을 정렬한다.

        Collections.sort(subA);
        Collections.sort(subB);

    }

    public void calCnt(){

        int sizeA = subA.size();
        int sizeB = subB.size();

        int left = 0;
        int right = sizeB-1;

        while(left <= sizeA-1 && right >= 0){ // 왼쪽 포인터는 배열의 끝에 가기 전까지 + 오른쪽 포인터는 0보다 클 때까지
            long sum = subA.get(left) + subB.get(right);
            if(sum == T){
                // 같은 원소가 있을 수 있으니까 반복문을 돌며 확인한다.
                long currentLeft = subA.get(left);
                long currentRight= subB.get(right);


                // 먼저 A 부분에서 같은 원소가 있는지 검사
                long leftCnt = 0;
                while(left <= sizeA-1 && subA.get(left) == currentLeft){
                    leftCnt++;
                    left++; // 같은 원소가 있으면 뒤로 이동
                }


                // 그 다음 B 부분에서 같은 원소가 있는지 검사
                long rightCnt = 0;
                while(right >= 0 && subB.get(right) == currentRight){
                    rightCnt++;
                    right--; // 같은 원소가 있으면 앞으로 이동
                }

                answer += leftCnt * rightCnt; // 경우의 수를 합계에 업데이트

            }else if(sum > T){ // 크다면 크기를 줄여야 한다.
                right--;

            }else{ // 합이 작다면 왼쪽 부분의 크기를 키워야 한다.
                left++;

            }

        }


    }


}
