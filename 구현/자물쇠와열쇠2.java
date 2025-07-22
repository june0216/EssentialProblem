import java.util.*;

class 자물쇠와열쇠2 {
    
    int[][] map;
    int keySize;
    int lockSize;
    int mapSize;
    int[][] Key;
    int[][] Lock;

    public boolean solution(int[][] key, int[][] lock) {
        Key = key;
        Lock = lock;
        
        keySize = key.length;
        lockSize = lock.length;
        
        // 키 상자가 lock 부분에 넘어갈 수 있으므로 충분히 키워준다. 
        mapSize = lockSize + keySize * 2 - 1;

        map = new int[mapSize][mapSize];

        // 가운데 Lock 배치
        for (int i = 0; i < lockSize; i++) {
            for (int j = 0; j < lockSize; j++) {
                // 가운데 부분에 넣기 
                map[i + keySize - 1][j + keySize - 1] = lock[i][j];
            }
        }

        // key 위치 및 회전 시도
        for (int i = 0; i <= mapSize - keySize; i++) {
            for (int j = 0; j <= mapSize - keySize; j++) {
                for (int d = 0; d < 4; d++) {
                    if (check(i, j)) {
                        return true;
                    }
                    spin90();
                }
            }
        }

        return false;
    }

    public boolean check(int y, int x) {
        boolean res = true;

        // key 값을 map에 더함
        for (int i = 0; i < keySize; i++) {
            for (int j = 0; j < keySize; j++) {
                map[i + y][j + x] += Key[i][j];
            }
        }

        // lock 부분이 모두 1인지 확인
        for (int i = 0; i < lockSize; i++) {
            for (int j = 0; j < lockSize; j++) {
                if (map[i + keySize - 1][j + keySize - 1] != 1) {
                    res = false;
                    break;
                }
            }
            if (!res) break;
        }

        // map 복구
        for (int i = 0; i < keySize; i++) {
            for (int j = 0; j < keySize; j++) {
                map[i + y][j + x] -= Key[i][j];
            }
        }

        return res;
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

    class Node {
        int x;
        int y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
