package bridge.controller;

import bridge.BridgeMaker;
import bridge.BridgeNumberGenerator;
import bridge.BridgeRandomNumberGenerator;
import bridge.service.BridgeGame;
import bridge.validator.InputValidator;
import bridge.view.InputView;
import bridge.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;


//최종 예외처리는 이곳에서 해야한다.

//구조 재할ㄴ
public class BridgeController {
    static boolean gameState =true;
    static int index = 0;
    static int count = 0;
    List<String> upBridge= new ArrayList<>();
    List<String> downBridge= new ArrayList<>();

    private static final String WIN_GAME ="성공" ;
    private static final String FAIL_GAME ="실패";

    public void run(){
        startGame();
    }

    public void startGame(){
        OutputView.gameStart();
        List<String> bridge = createBridge();
        initialBridge(bridge.size());
        programBridge(bridge);
    }
    //여기서 종료하면 결과를 찍어주면 된다.

    private void programBridge(List<String> bridge){

        do {
            if (!gameBridge(bridge)) {
                gameState = gameRetry();
            }
        }
        while(gameState);

    }
    private boolean gameBridge(List<String> bridge) {
        // 출력을 위해 두개의 리스트를 만들어줌

        initialReGameBridge(bridge.size());

        //게임 횟수 체크
        count++;
        while(gameState && index<bridge.size()) {
           gameState = moveBridge(upBridge,downBridge,bridge);
        }

       if(gameState && index== bridge.size()){
           OutputView.printResult(true,count,upBridge,downBridge); //끝이 난다.
           gameState =false;
            return true;
       }
        return false;
    }


    //실질적으로 이동하는 메소드
    private boolean moveBridge(List<String> upBridge, List<String> downBridge, List<String> bridge){
        BridgeGame bridgeGame = new BridgeGame();
        InputValidator inputValidator = new InputValidator();
        try {
            String moveLine = InputView.readMoving();
            inputValidator.moveUpAndDownValidator(moveLine);
            boolean moveResult = bridgeGame.move(upBridge, downBridge, bridge, index, moveLine);
            OutputView.printMap(upBridge, downBridge, index);
            index++;
            return moveResult;
            }

        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            moveBridge(upBridge, downBridge, bridge);
        }
        return true;
    }

    /** 게임 재시작, 종료 **/
    private boolean gameRetry(){
        InputValidator inputValidator = new InputValidator();
        try {
            BridgeGame bridgeGame = new BridgeGame();
            String retryLine =InputView.readGameCommand();

            inputValidator.reGameValidator(retryLine);
            boolean retry = bridgeGame.retry(retryLine);

            if (retry) {
                return true;
            }
            OutputView.printResult(false, count,upBridge, downBridge); //끝이 난다.
            return false;
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            gameRetry();
        }

        return true;
    }
    /**  게임 시작, 재시작을 위한 초기화  */
    private void initialBridge(int size) {
        IntStream.range(0,size).forEach(i->{
            upBridge.add("   ");
            downBridge.add("   ");
        });
        gameState =true;
        index =0;
    }

    private void initialReGameBridge(int size) {
        IntStream.range(0,size).forEach(i->{
            upBridge.set(i,"   ");
            downBridge.set(i,"   ");
        });
        gameState =true;
        index =0;
    }
    /** 다리 길이 입력 **/
    private Integer inputBridgeSize(){
        InputValidator inputValidator =new InputValidator();
        try {
            String length = InputView.readBridgeSize();
            inputValidator.bridgeLengthValidator(length);
            return Integer.parseInt(length);
        }
        catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
            return inputBridgeSize();
        }
    }
    /** 다리 길이 입력**/
    private List<String> createBridge(){
        BridgeNumberGenerator bridgeNumberGenerator = new BridgeRandomNumberGenerator();
        BridgeMaker bridgeMaker = new BridgeMaker(bridgeNumberGenerator);

       return bridgeMaker.makeBridge(inputBridgeSize());
    }



}
