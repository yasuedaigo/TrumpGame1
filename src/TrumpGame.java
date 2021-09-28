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
    private static String selectNumber;
    private static String selectMark;
    private static int minSelectMarkInt = 0;
    private static int offSetSelectNumberInt = -1;
    private static int startcount = 0;
    private static int minSelectNumber = 0;
    private static final List<String> MARKLIST = new ArrayList<>() {
        {
            add("ダイヤ");
            add("スペード");
            add("クローバー");
            add("ハート");
        }
    };
    private static final List<String> NUMBERLIST = new ArrayList<>() {
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
        targetMark = decisionTargetMark(MARKLIST);
        targetNumber = decisionTargetNumber(NUMBERLIST);
        showFirstMessage();
        showMarkQuestionMessage();
        showMenuOfMark(MARKLIST);
        while (!isSameMark(targetMarkInt,selectMarkInt)) {
            showQuestionMessage();
            selectMark = receiveinputMarkInt();
            showMarkResultMessage();
        }
        showNumberQuestionMessage();
        while (!isSameNumber(targetNumber, selectNumber)) {
            showQuestionMessage();
            selectNumber = receiveinputNumberInt();
            showNumberResultMessage();
        }
        STDIN.close();
    }

    private static String receiveinputMarkInt() {
        do {
            selectMarkInt = receiveinputNumber();
        } while (!isInRangeOfMark(selectMarkInt,MARKLIST));
        return MARKLIST.get(selectMarkInt);
    }

    private static boolean isInRangeOfMark(int selectMarkInt,List<String> markLIST) {
        if (selectMarkInt < minSelectMarkInt) {
            return false;
        }
        if (selectMarkInt >= markLIST.size()) {
            return false;
        }
        return true;
    }

    private static boolean isInRangeOfNumber(int selectNumberInt,List<String> numberList) {
        if (selectNumberInt <= minSelectNumber) {
            return false;
        }
        if (selectNumberInt > numberList.size()) {
            return false;
        }
        return true;
    }

    private static String receiveinputNumberInt() {
        do {
            selectNumberInt = receiveinputNumber();
        } while (!isInRangeOfNumber(selectNumberInt,NUMBERLIST));
        return NUMBERLIST.get(selectNumberInt + offSetSelectNumberInt);
    }

    private static void showMenuOfMark(List<String> markList) {
        int count = startcount;
        for (String mark : markList) {
            System.out.println(String.format("%s:%s", count, mark));
            count++;
        }
    }

    private static String decisionTargetMark(List<String> markList) {
        targetMarkInt = RANDOM.nextInt(markList.size());
        return markList.get(targetMarkInt);
    }

    private static String decisionTargetNumber(List<String> numberList) {
        targetNumberInt = RANDOM.nextInt(numberList.size());
        return numberList.get(targetNumberInt);
    }

    private static void showMarkResultMessage() {
        if (isSameMark(targetMarkInt, selectMarkInt)) {
            System.out.println(String.format("正解！図柄は%sだよ", targetMark));
            return;
        }
        System.out.println(String.format("残念！%sじゃないよ", selectMark));
    }

    private static void showNumberResultMessage() {
        if (isSameNumber(targetNumber, selectNumber)) {
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
        String inputStr;
        do {
            inputStr = STDIN.nextLine();
        } while (!isNumber(inputStr));
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

    private static boolean isSameMark(int targetMarkInt, int selectMarkInt) {

        if (targetMarkInt == selectMarkInt) {
            return true;
        }
        return false;
    }

    private static boolean isSameNumber(String targetNumber, String selectNumber) {
        if (targetNumber == selectNumber) {
            return true;
        }
        return false;
    }
}
