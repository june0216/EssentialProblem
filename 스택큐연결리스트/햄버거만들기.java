import java.util.*;
public class 햄버거만들기 {
    public int solution(int[] ingredient) {
        int result = 0;
        List<Integer> buffer = new LinkedList<>();
        for (int ing : ingredient) {
            buffer.add(ing); // 연결리스트에 담기
            while(buffer.size() >= 4){ // 연결리스트가 햄버거를 만들 크기 정도이면 검사하기
                int n = buffer.size();
                if(buffer.get(n-1) == 1 && buffer.get(n-2) == 3 && buffer.get(n-3) == 2 && buffer.get(n-4) == 1){
                    // 햄버거 순서와 일치하면 결과를 증가
                    result++;
                    for(int i = 1; i <5; i++){
                        buffer.remove(n-i); //햄버거가 완성되었으므로 연결리스트 내에서 햄버거 재료 삭제하기
                    }

                }else{ // 햄버거 순서와 일치하지 않는다면 햄버거 만들기 조건 확인하는 반복문 종료
                    break;
                }
            }


        }
        return result;
    }

}
