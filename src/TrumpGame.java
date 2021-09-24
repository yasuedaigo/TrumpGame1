import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TrumpGame {
    private static int inputNumber;
    private static final Random RANDOM = new Random();
    private static final Scanner STDIN = new Scanner(System.in);
    private static String targetMark;
    private static String targetNumber;
    private static int targetMarkInt;
    private static int targetNumberInt;
    private static int selectMarkInt;
    private static int selectNumberInt;
    private static String selectNumber = "初期値";
    private static String selectMark;
    private static int selectMarkIntInitializer = -1;
    private static int selectNumberIntInitializer = -1;
    private static int minSelectMarkInt = 0;
    private static int offSetSelectNumberInt = -1;
    private static int startcount = 0;
    private static String  inputStrInitializer = "あ";
    private static int minSelectNumber = 0;
    private static final List<String> MARKLIST = new ArrayList<String>() {
        {
            add("ダイヤ");
            add("スペード");
            add("クローバー");
            add("ハート");
        }
    };
    private static final List<String> NUMBERLIST = new ArrayList<String>() {
        {
            add("A");
            add("2");
            add("3");
            add("4");
            add("5");
            add("6");
            add("7");
            add("8");
            add("9");
            add("10");
            add("J");
            add("Q");
            add("K");
        }
    };

    private static final String INTRODUCTION_MESSAGE = "トランプを選んだよ";
    private static final String QUESTION_MESSAGE = "どれだと思う？ : ";
    private static final String MARK_QUESTION_MESSAGE = "トランプの図柄を当ててね";
    private static final String NUMBER_QUESTION_MESSAGE = "次は数字を当ててね";

    public static void main(String[] args) {
        decisionTargetMark();
        decisionTargetNumber();
        showFirstMessage();
        showMarkQuestionMessage();
        showNumberOfMark();
        while (true) {
            showQuestionMessage();
            receiveinputMarkInt();
            showMarkResultMessage();
            if (isCorrectMark()) {
                break;
            }
        }
        showNumberQuestionMessage();
        while (true) {
            showQuestionMessage();
            receiveinputNumberInt();
            showNumberResultMessage();
            if (isCorrectNumber()) {
                break;
            }
        }
        STDIN.close();
    }

    private static void receiveinputMarkInt() {
        selectMarkInt = selectMarkIntInitializer;
        while(!isMarkAppropriate()){
        selectMarkInt = receiveinputNumber();
        }
        selectMark = MARKLIST.get(selectMarkInt);
    }

    private static boolean isMarkAppropriate(){
        if(selectMarkInt < minSelectMarkInt){
            return false;
        }
        if(selectMarkInt >= MARKLIST.size()){
            return false;
        }
        return true;
    }

    private static boolean isNumberAppropriate(){
        if(selectNumberInt <= minSelectNumber){
            return false;
        }
        if(selectNumberInt > NUMBERLIST.size()){
            return false;
        }
        return true;
    }

    private static void receiveinputNumberInt() {
        selectNumberInt = selectNumberIntInitializer;
        while(!isNumberAppropriate()){
            selectNumberInt = receiveinputNumber();
        }
        selectNumber = NUMBERLIST.get(selectNumberInt + offSetSelectNumberInt);
    }

    private static void showNumberOfMark() {
        int count = startcount;
        for (String mark : MARKLIST) {
            System.out.println(String.format("%s:%s", count, mark));
            count++;
        }
    }

    private static void decisionTargetMark() {
        targetMarkInt = RANDOM.nextInt(MARKLIST.size());
        targetMark = MARKLIST.get(targetMarkInt);
    }

    private static void decisionTargetNumber() {
        targetNumberInt = RANDOM.nextInt(NUMBERLIST.size());
        targetNumber = NUMBERLIST.get(targetNumberInt);
    }

    private static void showMarkResultMessage() {
        if (isCorrectMark()) {
            System.out.println(String.format("正解！図柄は%sだよ", targetMark));
            return;
        }
        System.out.println(String.format("残念！%sじゃないよ", selectMark));
    }

    private static void showNumberResultMessage() {
        if (isCorrectNumber()) {
            System.out.println(String.format("正解！%sの%sだよ", targetMark, targetNumber));
            return;
        }
        System.out.println(String.format("残念！%sじゃないよ", selectNumber));
    }

    private static void showFirstMessage() {
        System.out.println(INTRODUCTION_MESSAGE);
    }

    private static void showQuestionMessage() {
        System.out.print(QUESTION_MESSAGE);
    }

    private static void showMarkQuestionMessage() {
        System.out.println(MARK_QUESTION_MESSAGE);
    }

    private static void showNumberQuestionMessage() {
        System.out.println(NUMBER_QUESTION_MESSAGE);
    }

    private static int receiveinputNumber() {
        String inputStr = inputStrInitializer;
        while (!isNumber(inputStr)){
            inputStr = STDIN.nextLine();
        }
        inputNumber = Integer.parseInt(inputStr);
        return inputNumber;
    }

    private static boolean isNumber(String inputStr) {
        try {
            Integer.parseInt(inputStr);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isCorrectMark() {
        if (targetMarkInt == selectMarkInt) {
            return true;
        }
        return false;
    }

    private static boolean isCorrectNumber() {
        if (targetNumber == selectNumber) {
            return true;
        }
        return false;
    }
}
