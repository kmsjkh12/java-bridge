package bridge.view;

import java.util.List;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 */
public class OutputView {
    private static final String GAME_START ="다리 건너기 게임을 시작합니다. ";
    private static final String PRINT_START="[";

    private static final String PRINT_END="]";

    private static final String RESULT_GAME = "게임 성공 여부: ";
    private static final String COMPLETE_GAME = "성공";
    private static final String FAIL_GAME = "실패";

    private static final String RESULT_COUNT ="총 시도한 횟수: ";


    public static void gameStart(){
        System.out.println(GAME_START);
    }
    /**
     * 현재까지 이동한 다리의 상태를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */

    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public static void printResult(boolean result, int count,List<String> upBridge, List<String> downBridge) {
        System.out.println("최종 게임 결과");
        drowiMap(upBridge,upBridge.size());
        System.out.println("");
        drowiMap(downBridge,upBridge.size());

        System.out.println("");

        if(result){
            System.out.print(RESULT_GAME);
            System.out.println(COMPLETE_GAME);
        }

        if(!result){
            System.out.print(RESULT_GAME);
            System.out.println(FAIL_GAME);
        }
        System.out.print(RESULT_COUNT);
        System.out.print(count);

    }

    public static void printMap(List<String> upBridge, List<String> downBridge, int index) {
        drowiMap(upBridge, index+1);
        drowiMap(downBridge, index+1);

    }

    public static void drowiMap(List<String> bridge, int index){
        System.out.print(PRINT_START);

        for(int i = 0 ; i < index; i ++){
            System.out.print(bridge.get(i));

            if(index-1 != i) {
                System.out.print("|");
            }
        }
        System.out.print(PRINT_END);

        System.out.println("");
    }

}
