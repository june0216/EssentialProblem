import java.util.*;

class 자물쇠와열쇠 {
    int[][] Key;
    int[][] Lock;
    int keySize;
    int lockSize;
    int expandSize;
    int[][] expandMap;

    public boolean solution(int[][] key, int[][] lock) {
        Key = key;
        Lock = lock;
        keySize = key.length;
        lockSize = lock.length;
        expandSize = keySize * 2 + lockSize - 1;

        expandMap = new int[expandSize][expandSize];

        // lock을 가운데에 위치시킴
        for (int i = 0; i < lockSize; i++) {
            for (int j = 0; j < lockSize; j++) {
                expandMap[i + keySize - 1][j + keySize - 1] = lock[i][j];
            }
        }
        
        
        // 이 범위때문에 틀림

        for (int y = 0; y <= expandSize - keySize; y++) {
            for (int x = 0; x <= expandSize - keySize; x++) {
                for (int r = 0; r < 4; r++) {
                    spin90();
                    if (check(x, y)) {
                        return true;
                    }
                    
                }
            }
        }

        return false;
    }

    public boolean check(int startX, int startY) {
        // key를 더함
        for (int i = 0; i < keySize; i++) {
            for (int j = 0; j < keySize; j++) {
                expandMap[i + startY][j + startX] += Key[i][j];
            }
        }

        // lock 영역 체크
        boolean result = true;
        for (int i = 0; i < lockSize; i++) {
            for (int j = 0; j < lockSize; j++) {
                if (expandMap[i + keySize - 1][j + keySize - 1] != 1) {
                    result = false;
                    break;
                }
            }
            if (!result) break;
        }

        // key를 제거
        for (int i = 0; i < keySize; i++) {
            for (int j = 0; j < keySize; j++) {
                expandMap[i + startY][j + startX] -= Key[i][j];
            }
        }

        return result;
    }

    public void spin90() {
        int[][] tmp = new int[keySize][keySize];
        for (int i = 0; i < keySize; i++) {
            for (int j = 0; j < keySize; j++) {
                tmp[j][keySize - i - 1] = Key[i][j];
            }
        }
        Key = tmp;
    }
}
