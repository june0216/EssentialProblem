public class 문자열나누기 {
    public int solution(String s) {
    int total = 0;
    char[] sTochar = s.toCharArray();
    int index = 0;
    int same = 1;
    int diff = 0;
    if(sTochar.length == 1){
        return 1;
    }
    for(int i = 1 ; i < sTochar.length;i++){

        if(sTochar[index] == sTochar[i]){
            same++;
        }else{
            diff++;
        }
        if(same == diff && diff != 0){
            if(i == sTochar.length-1){
                return ++total;
            }
            index = i+1;
            i++;

            total++;
            same = 1;
            diff = 0;

        }
        if(i >= sTochar.length-1){
            if(same != diff){
                return ++total;
            }
            return total;

        }

    }
    return total;
}
}
