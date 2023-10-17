package bridge.validator;

public class InputValidator {

    private static final int BRIDGE_MIN_LENGTH = 3;
    private static final int BRIDGE_MAX_LENGTH = 20;
    private static final String BRIDGE_LENGTH_INPUT_EXCEPTION ="[ERROR]3 이상 20 이하의 숫자를 입력할 수 있습니다.";
    private static final String BRIDGE_INTEGER_INPUT_EXCEPTION ="[ERROR]숫자를 입력할 수 있습니다.";
    private static final String BRIDGE_MOVE_INPUT_EXCEPTION ="[ERROR]U와 D만 입력 가능합니다.";
    private static final String BRIDGE_REGAME_INPUT_EXCEPTION ="[ERROR]R와 Q만 입력 가능합니다.";


    public void bridgeLengthValidator(String length) {
        InputIntegerValidator(length);
        InputLengthValidator(length);
    }
    public void moveUpAndDownValidator(String move){
        if(!move.equals("U") && !move.equals("D")){
            throw new IllegalArgumentException(BRIDGE_MOVE_INPUT_EXCEPTION);
        }
    }
    public void reGameValidator(String move){
        if(!move.equals("R") && !move.equals("Q")){
            throw new IllegalArgumentException(BRIDGE_REGAME_INPUT_EXCEPTION);
        }
    }
    public void InputLengthValidator(String bridgeLength){
        if(BRIDGE_MIN_LENGTH > Integer.parseInt(bridgeLength) || BRIDGE_MAX_LENGTH < Integer.parseInt(bridgeLength)){
            throw new IllegalArgumentException(BRIDGE_LENGTH_INPUT_EXCEPTION);
        }
    }
    public void InputIntegerValidator(String bridge) throws IllegalArgumentException{
        try {
           int a=  Integer.parseInt(bridge);
        } catch (NumberFormatException e) {
            System.out.print("예외처리: ");
            throw new IllegalArgumentException(BRIDGE_INTEGER_INPUT_EXCEPTION,e);
        }

    }

}
