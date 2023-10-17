package bridge.service;

import java.util.List;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {
    private static final String UP_STRING ="U";
    private static final String DOWN_STRING ="D";
    private static final String UP_INTEGER = "1";
    private static final String DOWN_INTEGER = "0";
    private static final String GAME_RESTART = "R";


    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public boolean move(List<String> upBridge, List<String> downBridge, List<String> bridge, int index, String input) {
        if(stateBridge(bridge, index, input)){ //true
            return stateTrueBridge(upBridge, downBridge, index, input);
        }
        return stateFalseBridge(upBridge, downBridge, index, input);
    }

    public boolean stateTrueBridge(List<String> upBridge, List<String> downBridge, int index, String input){
        if(input.equals(UP_STRING)){
            upBridge.set(index, " O ");
        }
        if(input.equals(DOWN_STRING)){
            downBridge.set(index, " O ");
        }
        return true;
    }
    public boolean stateFalseBridge(List<String> upBridge, List<String> downBridge, int index, String input){
        if(input.equals(UP_STRING)){
            upBridge.set(index, " X ");
        }
        if(input.equals(DOWN_STRING)){
            downBridge.set(index, " X ");
        }
        return false;
    }

    public boolean stateBridge(List<String> bridge, int index, String input){
        if(bridge.get(index).equals(UP_INTEGER) && input.equals(UP_STRING)){  //위
            return true;
        }
        if(bridge.get(index).equals(DOWN_INTEGER) && input.equals(DOWN_STRING)){  //위
            return true;
        }
        return false;
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public boolean retry(String line) {
        if(line.equals(GAME_RESTART)){
            return true;
        }
        return false;


    }


}
